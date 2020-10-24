package com.applaudo.restapi.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.applaudo.restapi.model.Item;
import com.applaudo.restapi.repository.IItemRepository;
import com.applaudo.restapi.service.IItemService;

@Service
public class ItemServiceImpl implements IItemService {
	
	private IItemRepository repository;
	
	@Override
	public List<Item> findAllItems() {
		return (List<Item>) repository.findAll();
	}
	
	@Override
	public List<Item> findItemsByStatusAndUser(String status, String user) {
		return repository.findItemsByStatusAndUser(status, user);
	}
	@Override
	public List<Item> findItemsByPaginationAndSorted(String pageSize, String page, String field) {
		Pageable pagination = PageRequest.of(Integer.valueOf(page), Integer.valueOf(pageSize),Sort.by(field));
		Page<Item> items = repository.findAll(pagination);
		return items.toList();
		
	}



}
