package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;

@Service
public class ProductService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
}
