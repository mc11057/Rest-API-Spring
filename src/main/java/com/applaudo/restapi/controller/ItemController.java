package com.applaudo.restapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.applaudo.restapi.model.Item;
import com.applaudo.restapi.service.IItemService;



@RestController
@RequestMapping("/app/item")
public class ItemController {	
	

	private IItemService itemService;

	@Autowired
	public ItemController(IItemService itemService ) {
		this.itemService = itemService;
	}
	@GetMapping()
	public ResponseEntity<List<Item>> findItems(@RequestParam("status") String status,
			@RequestParam("itemEnteredByUser") String user,@RequestParam("pageSize") String pageSize,
			@RequestParam("page") String page, @RequestParam("sortBy") String sortBy) {
		try {
			List<Item> items = new ArrayList<Item>();
			if(!StringUtils.isEmpty(status) && !StringUtils.isEmpty(user)) 
			{
				items = itemService.findItemsByStatusAndUser(status,user);

			}else if(!StringUtils.isEmpty(pageSize) && !StringUtils.isEmpty(page) && !StringUtils.isEmpty(sortBy)) 
			{
				items = itemService.findItemsByPaginationAndSorted(pageSize, page, sortBy);
			}else {
				items = itemService.findAllItems();
			}			
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	

	
	
	
	
	


}
