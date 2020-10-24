package com.applaudo.restapi.service;

import java.util.List;

import com.applaudo.restapi.model.Item;

public interface IItemService {
	
	List<Item> findItemsByPaginationAndSorted(String pageSize,String page,String sortByField);
	List<Item> findItemsByStatusAndUser(String status, String user);
	List<Item> findAllItems();

}
