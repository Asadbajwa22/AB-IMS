package com.qa.ims.controller;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.OrderModel;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class OrderController implements CrudController<OrderModel> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<OrderModel> readAll() {
		List<OrderModel> orders = orderDAO.readAll();
		for (OrderModel order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}


	@Override
	public OrderModel create() {
		LOGGER.info("Enter id of customer");
		int customerId = utils.getint();

		List<String> items = new ArrayList<>();

		boolean xKeyPressed = false;
		while(!xKeyPressed) {

			LOGGER.info("Please Enter the id of Item...  Press X to exit...");

			String itemid = utils.getString();

			if (itemid.equalsIgnoreCase("x")) {
				xKeyPressed = true;
				break;
			}

			items.add(itemid);
		}

		OrderModel order = orderDAO.create(new OrderModel(customerId,items));
		LOGGER.info("Order created successfully");
		return order;
	}

	@Override
	public OrderModel update() {
		LOGGER.info("Enter order id");
		int id = utils.getint();
		LOGGER.info("Enter customer id");
		int customerId = utils.getint();
		LOGGER.info("Enter item id");
		String itemid = utils.getString();

		List<String> itemModelList = new ArrayList<>();
		itemModelList.add(itemid);

		OrderModel order = orderDAO.update(new OrderModel( id,  customerId,  itemModelList));
		LOGGER.info("Order Updated successfully");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Enter order id");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
