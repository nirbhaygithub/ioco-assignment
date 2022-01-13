package com.ioco.iocorest.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class InvoiceDTO {

	@NotEmpty
	private String client;

	private long vatRate;

	private Date invoiceDate;

	List<LineItemDTO> lineItems;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public long getVatRate() {
		return vatRate;
	}

	public void setVatRate(long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public List<LineItemDTO> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItemDTO> lineItems) {
		this.lineItems = lineItems;
	}

}
