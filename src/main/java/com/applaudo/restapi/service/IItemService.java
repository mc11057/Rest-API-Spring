package com.applaudo.restapi.service;

import java.util.List;

import com.applaudo.restapi.model.Item;

public interface IItemService {

	List<Item> findItemsByPaginationAndSorted(String pageSize, String page, String sortByField) throws Exception;

	List<Item> findItemsByStatusAndUser(String status, String user) throws Exception;

	List<Item> findAllItems() throws Exception;

	Item create(Item item) throws Exception;

	void update(Item item, int id) throws Exception;

	void delete(int id) throws Exception;

	void delete() throws Exception;

	Item get(int id) throws Exception;

}
