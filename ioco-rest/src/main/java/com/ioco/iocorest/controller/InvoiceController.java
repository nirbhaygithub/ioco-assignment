package com.ioco.iocorest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ioco.iocorest.dto.InvoiceDTO;
import com.ioco.iocorest.entity.Invoice;
import com.ioco.iocorest.response.InvoiceResponse;
import com.ioco.iocorest.response.IocoResponse;
import com.ioco.iocorest.service.InvoiceService;

@RestController
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@GetMapping(value = "/invoices")
	public ResponseEntity<IocoResponse> getInvoices() {
		List<InvoiceResponse> invoices = invoiceService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(buildIocoResponse(HttpStatus.OK.value() + "", "SUCCESS", invoices));
	}

	@GetMapping(value = "/invoices/{invoiceId}")
	public ResponseEntity<IocoResponse> getInvoice(@PathVariable("invoiceId") long invoiceId) {
		InvoiceResponse invoice = invoiceService.getById(invoiceId);
		return ResponseEntity.status(HttpStatus.OK).body(buildIocoResponse(HttpStatus.OK.value() + "", "SUCCESS", invoice));
	}

	@PostMapping(value = "/invoices")
	public ResponseEntity<IocoResponse> addInvoice(@RequestBody @Valid InvoiceDTO dto) {
		Invoice result = invoiceService.addInvoice(dto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(buildIocoResponse(HttpStatus.CREATED.value() + "", "SUCCESS", result));
	}

	private IocoResponse buildIocoResponse(String statusCode, String msg, Object obj) {
		IocoResponse iocoResp = new IocoResponse();
		iocoResp.setResponseCode(statusCode);
		iocoResp.setResponseMessage(msg);
		iocoResp.setResponseObject(obj);
		return iocoResp;

	}

}
