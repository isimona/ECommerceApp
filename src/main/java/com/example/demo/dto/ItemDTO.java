package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class ItemDTO {
	
	@NotNull
	private Integer itemId;
	private String name;
	private String type;
	private Integer quantity;
	
	
	public ItemDTO(Integer itemId, String name, String type, Integer quantity) {
		super();
		this.itemId = itemId;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
	}


	public Integer getItemId() {
		return itemId;
	}


	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "ItemDTO [itemId=" + itemId + ", name=" + name + ", type=" + type + ", quantity=" + quantity + "]";
	}
	
	
	
	

}
