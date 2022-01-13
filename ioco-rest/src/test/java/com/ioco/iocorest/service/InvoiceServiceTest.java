package com.ioco.iocorest.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ioco.iocorest.TestHelper;
import com.ioco.iocorest.model.InvoiceModel;
import com.ioco.iocorest.repository.InvoiceRepository;
import com.ioco.iocorest.response.InvoiceResponse;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest extends TestHelper {

	@InjectMocks
	private InvoiceService invoiceService;

	@Mock
	private InvoiceRepository invoiceRepository;
	
	@Mock
	InvoiceModel invoiceModel;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAll() throws Exception {
		Mockito.when(invoiceRepository.findAll()).thenReturn(returnAllInvoice());
		Mockito.when(invoiceModel.getSubTotal(Mockito.any())).thenReturn(new BigDecimal(76500));
		Mockito.when(invoiceModel.getVat(Mockito.any())).thenReturn(new BigDecimal(13770));
		Mockito.when(invoiceModel.getTotal(Mockito.any())).thenReturn(new BigDecimal(90270));
		List<InvoiceResponse> responseList = invoiceService.getAll();
		Assert.assertNotNull(responseList);
	}

}
