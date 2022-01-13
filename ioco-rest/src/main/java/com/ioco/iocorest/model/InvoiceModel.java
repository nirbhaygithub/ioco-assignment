package com.ioco.iocorest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ioco.iocorest.entity.Invoice;
import com.ioco.iocorest.entity.LineItem;
import com.ioco.iocorest.repository.InvoiceRepository;

@Component
public class InvoiceModel {

	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	LineItemModel lineItemModel;

	private Invoice invoice;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public BigDecimal getSubTotal(Invoice invoice) {

		Set<LineItem> lineItems = invoice.getLineItem();
		BigDecimal subtotal = new BigDecimal(0);
		for (LineItem li : lineItems) {
			subtotal = subtotal.add(lineItemModel.getLineItemTotal(li));
		}
		return subtotal;

	}

	public BigDecimal getVat(Invoice invoice) {
		double vr = invoice.getVatRate();
		BigDecimal subtotal = getSubTotal(invoice);
		BigDecimal vat = new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN);
		vat = subtotal.multiply(new BigDecimal((vr / 100)).setScale(2, RoundingMode.HALF_EVEN));
		return vat;
	}

	public BigDecimal getTotal(Invoice invoice) {
		return getVat(invoice).add(getSubTotal(invoice));
	}
}
