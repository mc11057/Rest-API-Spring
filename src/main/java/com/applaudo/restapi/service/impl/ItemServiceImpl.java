package com.applaudo.restapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.applaudo.restapi.model.Item;
import com.applaudo.restapi.model.ItemStatusEnum;
import com.applaudo.restapi.repository.IItemRepository;
import com.applaudo.restapi.service.IItemService;
import com.applaudo.restapi.utility.RegisterExistException;

@Service
public class ItemServiceImpl implements IItemService {

	private IItemRepository repository;

	public ItemServiceImpl(IItemRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Item> findAllItems() throws Exception {
		return (List<Item>) repository.findAll();
	}

	@Override
	public List<Item> findItemsByStatusAndUser(String status, String user) throws Exception {
		return repository.findItemsByStatusAndUser(ItemStatusEnum.valueOf(status), user);
	}

	@Override
	public List<Item> findItemsByPaginationAndSorted(String pageSize, String page, String field) throws Exception {
		Pageable pagination = PageRequest.of(Integer.valueOf(page), Integer.valueOf(pageSize), Sort.by(field));
		Page<Item> items = repository.findAll(pagination);
		return items.toList();
	}

	@Override
	public Item create(Item item) throws Exception {
		Optional<Item> itemDB = repository.findById(item.getItemId());
		if (itemDB.isPresent())
			throw new RegisterExistException("The id of the Item already exist in the DataBase");
		return repository.save(item);

	}

	@Override
	public void update(Item item, int id) throws Exception {
		Optional<Item> itemDB = repository.findById(id);
		if (itemDB.isPresent()) {
			item.setItemId(id);
			repository.save(item);
			return;
		}
		throw new RegisterExistException("The id of the Item no exist in the DataBase");

	}

	@Override
	public void delete(int id) throws Exception {
		Optional<Item> itemDB = repository.findById(id);
		if (itemDB.isPresent()) {
			repository.deleteById(id);
			return;
		}
		throw new RegisterExistException("The id of the Item no exist in the DataBase");

	}

	@Override
	public void delete() throws Exception {
		repository.deleteAll();
	}

	@Override
	public Item get(int id) throws Exception {
		Optional<Item> itemDB = repository.findById(id);
		if (itemDB.isPresent()) {
			return itemDB.get();
		}
		throw new RegisterExistException("The id of the Item no exist in the DataBase");
	}

}
