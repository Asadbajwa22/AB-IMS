package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.ItemModel;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemController implements CrudController<ItemModel> {

    public static final Logger LOGGER = LogManager.getLogger();

    private ItemDAO itemDAO;
    private Utils utils;

    public ItemController(ItemDAO itemDAO, Utils utils) {
        super();
        this.itemDAO = itemDAO;
        this.utils = utils;
    }

    @Override
    public List<ItemModel> readAll() {
        List<ItemModel> items = itemDAO.readAll();
        for (ItemModel item : items) {
            LOGGER.info(item);
        }
        return items;
    }

    @Override
    public ItemModel create() {
        LOGGER.info("Item name");
        String itemName = utils.getString();
        LOGGER.info("Item price");
        String itemPrice = utils.getString();
        ItemModel item = itemDAO.create(new ItemModel(itemName, itemPrice));
        LOGGER.info("Item created successfully");
        return item;
    }

    @Override
    public ItemModel update() {
        LOGGER.info("Enter id of item");
        int id = utils.getint();
        LOGGER.info("Enter item name");
        String name = utils.getString();
        LOGGER.info("Enter item price");
        String price = utils.getString();
        ItemModel item = itemDAO.update(new ItemModel(id, name, price));
        LOGGER.info("Item Updated successfully");
        return item;
    }

    @Override
    public int delete() {
        LOGGER.info("Enter id of item");
        Long id = utils.getLong();
        return itemDAO.delete(id);
    }

}
