package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {

	int id;
	int customerId;
	List<String> itemModel = new ArrayList();

	public OrderModel(int id, int customerId, List<String> itemModel) {
		this.id = id;
		this.customerId = customerId;
		this.itemModel = itemModel;
	}

	public OrderModel(int customerId, List<String> itemModel) {
		this.customerId = customerId;
		this.itemModel = itemModel;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<String> getItemModel() {
		return itemModel;
	}

	public void setItemModel(List<String> itemModel) {
		this.itemModel = itemModel;
	}

	public Long getId() {
		return Long.valueOf(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "OrderModel{" +
				"id=" + id +
				", customerId=" + customerId +
				", itemModel=" + itemModel.toString() +
				'}';
	}
}
