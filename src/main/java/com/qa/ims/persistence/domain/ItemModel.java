package com.qa.ims.persistence.domain;


public class ItemModel {

	public int id;
	public String name;
	public String value;

	public ItemModel(int id, String name2, String value) {
		this.id=id;
		name=name2;
		this.value=value;

	}

	public ItemModel(String name2, String value) {
		name=name2;
		this.value=value;

	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ItemModel{" +
				"id=" + id +
				", name='" + name + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
