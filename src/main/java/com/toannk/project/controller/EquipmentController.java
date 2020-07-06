/**
 * 
 */
package com.toannk.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toannk.project.dataset.Equipment;
import com.toannk.project.service.EquipmentService;

/**
 * @author WIN
 *
 */
@RequestMapping(value = "/api")
@RestController
public class EquipmentController {
	@Autowired
	private EquipmentService service;

	
	public EquipmentController(EquipmentService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/equipment")
	public List<Equipment> getAllEquipment() {
		return service.getAllEquipment();
	}

	@DeleteMapping(value = "/equipment/{id}")
	public int deleteEquipment(@PathVariable("id") int id) {
		return service.deleteEquipment(id);
	}

	@PostMapping(value = "/equipment")
	public int insertEquipment(@RequestBody Equipment equipment) {
		return service.insertEquipment(equipment);
	}

	@PutMapping(value = "/equipment/{id}")
	public int updateEquipment(@PathVariable("id") int id,@RequestBody Equipment equipment) {
		return service.updateEquipment(equipment, id);
	}
}
