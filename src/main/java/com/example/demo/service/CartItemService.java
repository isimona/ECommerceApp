package com.example.demo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// The cart row has defined idUser in the  db table, but somehow I cannot access it here it says the idUser is null and cannot get the user
	public boolean addNewCartItem(CartDTO cartDto) {
		Cart cart;
		Item item;
		//User user;
		
		try {
			 cart = (cartRepository.findById(cartDto.getCartId())).get();
			 item = (itemRepository.findById(cartDto.getItemId())).get();
			// user = (userRepository.findById((cart.getUser().getIdUser())).get());
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		CartItem cartItem;
		if(item != null && cart != null) {
			int currStock = item.getStock();
			item.setStock(currStock - cartDto.getQuantity());
			
			int quantity = cartDto.getQuantity();
			BigDecimal quant = new BigDecimal(quantity);
			BigDecimal currTotal = cart.getTotal();
			cart.setTotal((item.getPrice().multiply(quant)).add(currTotal));
			
			cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setItem(item);
			cartItem.setQuantity(quantity);
			cartItem.setUser(cart.getUser());
			cartItemRepository.save(cartItem);
			
			cart.getCartItems().add(cartItem);
			//user.getCartItems().add(cartItem);
			
			cartRepository.save(cart);
			//userRepository.save(user);
			//cartItemRepository.save(cartItem);
			itemRepository.save(item);
			
			return true;
		}
		
		return false;
		
	}
	
	public List<ItemDTO> getAllCartItems(int idCart) {
		List<ItemDTO> items = new ArrayList<>();
		Cart cart;
		try {
			cart = cartRepository.findById(idCart).get();
			List<CartItem> cartItems = cart.getCartItems();
			if(cartItems.isEmpty()) {
				return new ArrayList<>();
			}
			for(CartItem c: cartItems) {
				if(itemRepository.findById(c.getItem().getIdItem()).isPresent()) {
					Item item = (itemRepository.findById(c.getItem().getIdItem())).get();
					System.out.println("Ima li te ovde");
					ItemDTO itemDto = new ItemDTO(item.getIdItem(), item.getName(), item.getType(), c.getQuantity());
					System.out.println("A ovde");
					items.add(itemDto);
				}
			
			}
			return items;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// The cart row has defined idUser in the  db table, but somehow I cannot access it here it says the idUser is null and cannot get the user
	public ItemDTO updateQuantity(int cartItemId, int newQuantity) {
		Cart cart;
		CartItem cartItem;
		Item item;
		//User user;
		
		
		try {
			cartItem = cartItemRepository.findById(cartItemId).get();
			item = itemRepository.findById(cartItem.getItem().getIdItem()).get();
			cart = cartRepository.findById(cartItem.getCart().getIdCart()).get();
			//user = (userRepository.findById((cart.getUser().getIdUser())).get());
			
			
			int currentStock = item.getStock();
			int currentQuantity = cartItem.getQuantity();
			int newStock = 0;
			if(newQuantity > currentStock) {
				return null;
			}else if(newQuantity < currentQuantity) {
				newStock = currentQuantity - newQuantity + currentStock;
			}else {
				newStock = currentStock - (newQuantity - currentQuantity);
			}
			item.setStock(newStock);
			cartItem.setQuantity(newQuantity);
			for(CartItem c: cart.getCartItems()) {
				if(c.getIdCartItem() == cartItem.getIdCartItem()) {
					c.setQuantity(newQuantity);
				}
			}
			BigDecimal currTotal = cart.getTotal();
			BigDecimal newTotal = currTotal.add((new BigDecimal(newQuantity-currentQuantity).multiply(item.getPrice())));
			cart.setTotal(newTotal);

			itemRepository.save(item);
			cartRepository.save(cart);
			cartItemRepository.save(cartItem);
			ItemDTO itemDto = new ItemDTO(item.getIdItem(), item.getName(), item.getType(), newQuantity);
			return itemDto;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Transactional
	public boolean deleteItemFromCart(int id) {
		Cart cart;
		CartItem cartItem;
		Item item;

		try {
			cartItem = cartItemRepository.findById(id).get();
			item = itemRepository.findById(cartItem.getItem().getIdItem()).get();
			cart = cartRepository.findById(cartItem.getCart().getIdCart()).get();
			
			int currStock = item.getStock();
			item.setStock(currStock + cartItem.getQuantity());
			
			BigDecimal currTotal = cart.getTotal();
			cart.setTotal(currTotal.subtract(((new BigDecimal(cartItem.getQuantity())).multiply(item.getPrice()))));
			
			System.out.println(id);
			cart.getCartItems().remove(cartItem);
			cartRepository.save(cart);
			itemRepository.save(item);
			cartItemRepository.delete(cartItem);
//			cartItemRepository.flush();
//			cartItemRepository.deleteById(cartItem.getIdCartItem());
//			cartItemRepository.flush();
			System.out.println(id);
			
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
		
	}
	
}
