package com.applaudo.restapi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.applaudo.restapi.model.Item;
import com.applaudo.restapi.service.IItemService;
import com.applaudo.restapi.utility.RegisterExistException;

@RestController
@RequestMapping("/app/item")
public class ItemController {

	private IItemService itemService;

	@Autowired
	public ItemController(IItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> get(@PathVariable("id") int id) {
		try {
			Item item = itemService.get(id);
			return new ResponseEntity<Item>(item, HttpStatus.OK);

		} catch (RegisterExistException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RolesAllowed("ROLE_ADMIN")
	@PostMapping()
	public ResponseEntity<Object> create(@RequestBody Item item) {
		try {
			Item itemSaved = itemService.create(item);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(itemSaved.getItemId()).toUri();
			return ResponseEntity.created(location).build();
		} catch (RegisterExistException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@RequestBody Item item, @PathVariable int id) {
		try {
			itemService.update(item, id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (RegisterExistException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RolesAllowed("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		try {
			itemService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (RegisterExistException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@RolesAllowed("ROLE_ADMIN")
	@DeleteMapping()
	public ResponseEntity<Object> delete() {
		try {
			itemService.delete();
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@GetMapping()
	public ResponseEntity<List<Item>> find(@RequestParam("status") String status,
			@RequestParam("itemEnteredByUser") String user, @RequestParam("pageSize") String pageSize,
			@RequestParam("page") String page, @RequestParam("sortBy") String sortBy) {
		try {
			List<Item> items = new ArrayList<Item>();

			if (!StringUtils.isEmpty(status) && !StringUtils.isEmpty(user)) {
				items = itemService.findItemsByStatusAndUser(status, user);

			} else if (!StringUtils.isEmpty(pageSize) && !StringUtils.isEmpty(page) && !StringUtils.isEmpty(sortBy)) {
				items = itemService.findItemsByPaginationAndSorted(pageSize, page, sortBy);

			} else {
				items = itemService.findAllItems();
			}
			return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
