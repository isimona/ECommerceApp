package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Min;

public class CartDTO {
	
	@NotNull
	private Integer cartId;
	
	private LocalDateTime date;
	@NotNull
	private Integer itemId;
	@Min(value=1, message="Must add at least one piece")
	private Integer quantity;
	
	
	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", date=" + date + ", itemId=" + itemId + ", quantity=" + quantity + "]";
	}
	
	
	public CartDTO(Integer cartId, LocalDateTime date, Integer itemId, Integer quantity) {
		super();
		this.cartId = cartId;
		this.date = date;
		this.itemId = itemId;
		this.quantity = quantity;
	}


	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	

}
