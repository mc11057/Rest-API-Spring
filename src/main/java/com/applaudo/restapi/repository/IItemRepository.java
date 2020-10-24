package com.applaudo.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applaudo.restapi.model.Item;



public interface IItemRepository extends JpaRepository<Item, Integer> {

}
