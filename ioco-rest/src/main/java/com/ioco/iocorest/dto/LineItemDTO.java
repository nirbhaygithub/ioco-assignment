package com.ioco.iocorest.dto;

import java.math.BigDecimal;

public class LineItemDTO {
	
	private long quantity;
	
	private String description;
	
	private BigDecimal unitPrice;

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
