package com.ioco.iocorest.model;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ioco.iocorest.entity.LineItem;
import com.ioco.iocorest.repository.LineItemRepository;

@Component
public class LineItemModel {

	@Autowired
	LineItemRepository lineItemRepository;

	private LineItem lineItem;

	public LineItem getLineItem() {
		return lineItem;
	}

	public void setLineItem(LineItem lineItem) {
		this.lineItem = lineItem;
	}

	public BigDecimal getLineItemTotal(LineItem li) {
		return li.getUnitPrice().multiply(new BigDecimal(li.getQuantity()));
	}
}
