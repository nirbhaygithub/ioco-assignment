package com.ioco.iocorest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ioco.iocorest.TestHelper;
import com.ioco.iocorest.service.InvoiceService;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceControllerTest extends TestHelper {

	@InjectMocks
	private InvoiceController invoiceController;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private InvoiceService invoiceService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
	}

	@Test
	public void getAll() throws Exception {
		Mockito.when(invoiceService.getAll()).thenReturn(returnAllInvoiceResponse());
		mockMvc.perform(MockMvcRequestBuilders.get("/invoices").content(jsonString(returnAllInvoiceResponse()))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getById() throws Exception {
		Mockito.when(invoiceService.getById(Mockito.anyLong())).thenReturn(returnInvoiceResponse());
		mockMvc.perform(MockMvcRequestBuilders.get("/invoices/1").content(jsonString(returnInvoiceResponse()))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void addInvoice() throws Exception {
		Mockito.when(invoiceService.addInvoice(Mockito.any())).thenReturn(returnInvoice());
		mockMvc.perform(MockMvcRequestBuilders.post("/invoices").content(jsonString(returnInvoice()))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
}
