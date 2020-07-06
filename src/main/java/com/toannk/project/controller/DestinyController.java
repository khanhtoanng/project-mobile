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

import com.toannk.project.dataset.ActorRoleInDestiny;
import com.toannk.project.dataset.Destiny;
import com.toannk.project.dataset.EquipmentInDestiny;
import com.toannk.project.service.DestinyService;

/**
 * @author WIN
 *
 */
@RequestMapping(value = "/api")
@RestController
public class DestinyController {
	@Autowired
	private DestinyService service;

	public DestinyController(DestinyService service) {
		this.service = service;
	}

	@GetMapping(value = "/destiny")
	public List<Destiny> getAllDestiny() {
		return service.getAllDestiny();
	}

	@DeleteMapping(value = "/destiny/{id}")
	public int deleteDestiny(@PathVariable("id") int id) {
		return service.deleteDestiny(id);
	}

	@PostMapping(value = "/destiny")
	public int insertDestiny(@RequestBody Destiny destiny) {
		return service.insertDestiny(destiny);
	}

	@PutMapping(value = "/destiny/{id}")
	public int updateDestiny(@PathVariable("id") int id, @RequestBody Destiny destiny) {
		return service.updateDestiny(destiny, id);
	}

	@PostMapping(value = "/destiny/addListEquiment")
	public int addEquipmentToDestiny(@RequestBody List<EquipmentInDestiny> listEquipmentInDestiny) {
		return service.addListEquipmentToDestiny(listEquipmentInDestiny);
	}

	@PostMapping(value = "/destiny/addListActor")
	public int addActorToDestiny(@RequestBody List<ActorRoleInDestiny> listActorRoleInDestiny) {
		return service.addListActorToDestiny(listActorRoleInDestiny);
	}
}
