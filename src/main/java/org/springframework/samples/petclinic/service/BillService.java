package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.samples.petclinic.owner.Bill;
import org.springframework.samples.petclinic.owner.BillRepository;

public interface BillService {

	List<Bill> findAll();
	Bill findById(Integer id);
	Bill save(Bill b);
	
	void deleteBill(Bill b);

	void deleteAll();

}
