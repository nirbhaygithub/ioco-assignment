package com.ioco.iocorest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ioco.iocorest.entity.LineItem;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
	
	List<LineItem> findByInvoiceId(int id);

}
