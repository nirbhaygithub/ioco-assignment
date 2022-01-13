package com.ioco.iocorest.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioco.iocorest.dto.InvoiceDTO;
import com.ioco.iocorest.dto.LineItemDTO;
import com.ioco.iocorest.entity.Invoice;
import com.ioco.iocorest.entity.LineItem;
import com.ioco.iocorest.exception.NoDataFoundException;
import com.ioco.iocorest.model.InvoiceModel;
import com.ioco.iocorest.repository.InvoiceRepository;
import com.ioco.iocorest.repository.LineItemRepository;
import com.ioco.iocorest.response.InvoiceResponse;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	InvoiceModel invoiceModel;

	@Autowired
	LineItemRepository lineItemRepository;

	public List<InvoiceResponse> getAll() {

		List<Invoice> invoiceList = invoiceRepository.findAll();
		List<InvoiceResponse> invoiceResp = invoiceList.stream().map(invoice -> {
			InvoiceResponse ir = new InvoiceResponse();
			BeanUtils.copyProperties(invoice, ir);
			ir.setSubTotal(invoiceModel.getSubTotal(invoice));
			ir.setVat(invoiceModel.getVat(invoice));
			ir.setTotal(invoiceModel.getTotal(invoice));
			return ir;
		}).collect(Collectors.toList());
		return invoiceResp;
	}

	public InvoiceResponse getById(long id) {

		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if (invoice.isPresent()) {
			InvoiceResponse ir = new InvoiceResponse();
			BeanUtils.copyProperties(invoice.get(), ir);
			ir.setSubTotal(invoiceModel.getSubTotal(invoice.get()));
			ir.setVat(invoiceModel.getVat(invoice.get()));
			ir.setTotal(invoiceModel.getTotal(invoice.get()));
			return ir;

		} else {
			throw new NoDataFoundException();
		}
	}

	@Transactional
	public Invoice addInvoice(InvoiceDTO dto) {

		Invoice invoice = new Invoice();
		invoice.setClient(dto.getClient());
		invoice.setInvoiceDate(dto.getInvoiceDate());
		invoice.setVatRate(dto.getVatRate());

		Set<LineItem> LineItems = new HashSet<>();
		for (LineItemDTO ltDto : dto.getLineItems()) {
			LineItem lt = new LineItem();
			lt.setDescription(ltDto.getDescription());
			lt.setQuantity(ltDto.getQuantity());
			lt.setUnitPrice(ltDto.getUnitPrice());
			lt.setInvoice(invoice);
			LineItems.add(lt);
		}
		invoice.setLineItem(LineItems);
		invoice = invoiceRepository.save(invoice);

		return invoice;
	}

}
