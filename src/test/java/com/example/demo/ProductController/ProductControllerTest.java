package com.example.demo.ProductController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.ProductService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductControllerTest {

	 @Mock
	 private ItemRepository itemRepository;

	 @InjectMocks
	 private ProductController productController;
	 
	 @Mock
	 ProductService productService;
	 
	 @Mock
	 private PageRequest pageRequest;
	 private List<Item> items;
	 private Page<Item> pageItem;
	 
	 
	 @BeforeEach
	 void setUp() {
	        pageRequest = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "name"));
	        Item  item1 = new Item(1,"Food","Tonus",new BigDecimal(300),4000,"Bread");
	        Item  item2 = new Item(1,"Food","Happy Cow",new BigDecimal(200),1234,"Milk");
	        items =  Arrays.asList(item1, item2);
	        pageItem = new PageImpl<>(items);
	    }
	
	
//	@Test
//	void getAllProductsTest(){
//		when(itemRepository.findAll(pageRequest)).thenReturn(pageItem);
//		assertEquals(pageItem, productController.getAllProducts(0, 2, "name", false).getBody());
//		
//		verify(itemRepository, times(1)).findAll(pageRequest);
//	}
	
	@Test
	void getSingleProductByIdTest() {
		Item  item1 = new Item(1,"Food","Tonus",new BigDecimal(300),4000,"Bread");
		
		when(itemRepository.findById(1)).thenReturn(Optional.of(item1));

        ResponseEntity<Item> result = productController.getItemById(1);

        assertEquals("Tonus", result.getBody().getName());
		
	}
}
