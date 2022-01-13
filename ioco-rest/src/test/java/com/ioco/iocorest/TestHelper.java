package com.ioco.iocorest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioco.iocorest.entity.Invoice;
import com.ioco.iocorest.entity.LineItem;
import com.ioco.iocorest.response.InvoiceResponse;

public class TestHelper {

	protected List<InvoiceResponse> returnAllInvoiceResponse() throws ParseException {
		List<InvoiceResponse> invoiceList = new ArrayList<>();
		InvoiceResponse invoice = new InvoiceResponse();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		invoice.setId(1);
		invoice.setClient("Microsoft");
		invoice.setInvoiceDate(formatter.parse("2022-01-13"));
		invoice.setSubTotal(new BigDecimal(76500));
		invoice.setVat(new BigDecimal(13770));
		invoice.setTotal(new BigDecimal(90270));
		invoiceList.add(invoice);
		return invoiceList;
	}

	protected InvoiceResponse returnInvoiceResponse() throws ParseException {
		InvoiceResponse invoice = new InvoiceResponse();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		invoice.setId(1);
		invoice.setClient("Microsoft");
		invoice.setInvoiceDate(formatter.parse("2022-01-13"));
		invoice.setSubTotal(new BigDecimal(76500));
		invoice.setVat(new BigDecimal(13770));
		invoice.setTotal(new BigDecimal(90270));
		return invoice;
	}

	protected Invoice returnInvoice() throws ParseException {
		Invoice invoice = new Invoice();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		invoice.setId(1);
		invoice.setClient("Microsoft");
		invoice.setInvoiceDate(formatter.parse("2022-01-13"));
		invoice.setLineItem(new HashSet<>());
		return invoice;
	}

	protected List<Invoice> returnAllInvoice() throws ParseException {
		List<Invoice> invoiceList = new ArrayList<>();
		Set<LineItem> lineItems = new HashSet<>();
		Invoice invoice = new Invoice();
		LineItem lineIem = new LineItem();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		invoice.setId(1);
		invoice.setClient("Microsoft");
		invoice.setInvoiceDate(formatter.parse("2022-01-13"));
		lineIem.setDescription("first description");
		lineIem.setId(1);
		lineIem.setQuantity(12);
		lineIem.setUnitPrice(new BigDecimal(2000));
		lineItems.add(lineIem);
		invoice.setLineItem(lineItems);
		invoiceList.add(invoice);
		return invoiceList;
	}

	protected String jsonString(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
