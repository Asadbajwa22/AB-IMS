package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.OrderModel;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderDAO implements Dao<OrderModel> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderModel modelFromResultSet(ResultSet resultSet) throws SQLException {
		int id  = resultSet.getInt("id");
		int customerId = resultSet.getInt("customer_id");
		String itemId = resultSet.getString("item_id");

		List<String> itemsList = new ArrayList<>();
		itemsList.add(itemId);

		return new OrderModel(id,  customerId, itemsList);
	}


	@Override
	public List<OrderModel> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<OrderModel> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrderModel readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderModel create(OrderModel order) {

		int count=order.getItemModel().size();

		for(int x=0;x<count;x++) {
			try (Connection connection = DBUtils.getInstance().getConnection();
				 PreparedStatement statement = connection
						 .prepareStatement("INSERT INTO orders(customer_id ,item_id) VALUES (?, ?)");) {
				statement.setInt(1,order.getCustomerId()); ;
				statement.setInt(2, Integer.parseInt(order.getItemModel().get(x)));
				statement.executeUpdate();
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
		}
		return readLatest();
	}

	@Override
	public OrderModel read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	@Override
	public OrderModel update( OrderModel order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET customer_id = ?, item_id = ? WHERE id = ?");) {
			statement.setInt(1, order.getCustomerId());
			statement.setInt(2, Integer.parseInt(order.getItemModel().get(0)));
			statement.setLong(3, order.getId());
			statement.executeUpdate();
			return read(order.getId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
