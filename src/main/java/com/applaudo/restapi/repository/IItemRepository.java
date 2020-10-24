package com.applaudo.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.applaudo.restapi.model.Item;



public interface IItemRepository extends PagingAndSortingRepository<Item, Integer> {

	
	@Query("select a from Item a where a.status=:status and a.itemEnteredByUser=:user")
	List<Item> findItemsByStatusAndUser(@Param("status") String status,@Param("user")  String user);
}
