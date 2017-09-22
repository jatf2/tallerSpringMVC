package org.springframework.samples.petclinic.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.Bill;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BillController {
	@Autowired
	private BillService billService;
	
	@RequestMapping(value="/bills", method=RequestMethod.GET)
	public List<Bill> findAll() {
		return this.billService.findAll();
	}
	
	@RequestMapping(value="/bills/{id}", method=RequestMethod.GET)
	public ResponseEntity<Bill> findById(@PathVariable("id") Integer id) {
		Bill b = this.billService.findById(id);
		if(b!= null)
			return ResponseEntity.status(HttpStatus.OK).body(b);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	//Sin terminar
	@RequestMapping(value="/bills", method = RequestMethod.POST)
	ResponseEntity<Bill> add(@RequestBody Bill input) {
		
		Bill b = new Bill();
		b.setIdNumber(input.getIdNumber());
		b.setMoney(input.getMoney());
		b.setPaymentDate(input.getPaymentDate());
		this.billService.save(b);
		return ResponseEntity.status(HttpStatus.OK).body(b);
	}
	
	@RequestMapping(value="/bills/{idBill}", method=RequestMethod.PUT) 
	public Bill updateBill(@PathVariable("idBill") Integer id, @RequestBody Bill b) {
		Bill fromDB = this.billService.findById(id);
		if(fromDB != null) {
			b.setId(fromDB.getId());
			return this.billService.save(b);
		}
		return null;			
	} 
	
	@RequestMapping(value="/bills/{idBill}", method=RequestMethod.DELETE) 
	public ResponseEntity<Bill> deleteBill(@PathVariable("idBill") Integer id) {
		Bill fromDB = this.billService.findById(id);
		if(fromDB != null) {
			this.billService.deleteBill(fromDB);
			return ResponseEntity.status(HttpStatus.OK).body(fromDB);			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);	
	}

	@RequestMapping(value="/bills", method=RequestMethod.DELETE) 
	public void deleteAllBills() {
		this.billService.deleteAll();			
	}
	
	
	
	
}
