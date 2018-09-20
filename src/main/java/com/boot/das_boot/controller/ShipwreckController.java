package com.boot.das_boot.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.das_boot.exception.ResourceNotFoundException;
import com.boot.das_boot.model.Shipwreck;
import com.boot.das_boot.repository.ShipwreckRepository;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

	@Autowired
	private ShipwreckRepository shipwreckRepository;

	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<Shipwreck> list() {
		return shipwreckRepository.findAll();
	}

	// The @RequestBody annotation is used to bind the request body with a method
	// parameter.
	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		return shipwreckRepository.saveAndFlush(shipwreck);
	}
	// The @PathVariable annotation, as the name suggests, is used to bind a path
	// variable with a method parameter.

	// In the above method, we are throwing a ResourceNotFoundException whenever a
	// Shipwreck with the given id is not found
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Shipwreck get(@PathVariable Long id) {
		return shipwreckRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shipwreck", "id", id));
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
		Shipwreck existingShipwreck = shipwreckRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Shipwreck", "id", id));
		BeanUtils.copyProperties(shipwreck, existingShipwreck);
		return shipwreckRepository.saveAndFlush(existingShipwreck);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable Long id) {
		Shipwreck existingShipwreck = shipwreckRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Shipwreck", "id", id));
		shipwreckRepository.delete(existingShipwreck);
		return existingShipwreck;
	}

}
