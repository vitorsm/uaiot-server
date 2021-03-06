package com.uaiot.uaitserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.dto.ThingDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Thing;
import com.uaiot.uaitserver.models.TypeThing;

@RestController
@RequestMapping(value = "serv/thing")
public class ThingController {

	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ThingDTO> insert(@RequestBody ThingDTO thingDTO) throws DAOException {
		
		try {
			Thing thing = uf.map.thingMapper.mapToObj(thingDTO);
			uf.thingService.insert(thing);
			
			return new ResponseEntity<ThingDTO>(uf.map.thingMapper.mapToDto(thing),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<ThingDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ThingDTO> update(@RequestBody ThingDTO thingDTO) throws DAOException {
		
		try {
			Thing thing = uf.map.thingMapper.mapToObj(thingDTO);
			uf.thingService.update(thing);
			
			return new ResponseEntity<ThingDTO>(uf.map.thingMapper.mapToDto(thing),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<ThingDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ThingDTO> delete(@PathVariable("id") int thingImei) throws DAOException {
		
		try {
			Thing thing = uf.thingService.findById(thingImei);
			uf.thingService.delete(thing);
			
			return new ResponseEntity<ThingDTO>(uf.map.thingMapper.mapToDto(thing),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<ThingDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ThingDTO>> get(@RequestParam(required = false) Integer thingImei,
			@RequestParam(required = false) Integer typeThingId) throws DAOException {
		
		try {
			List<Thing> things = new ArrayList<Thing>();
			
			if (thingImei != null) {
				Thing thing = uf.thingService.findById(thingImei);
				things.add(thing);
			} else {
				List<Filter> filters = new ArrayList<Filter>();
				if (typeThingId != null) {
					Filter<TypeThing> fTThing = new Filter<TypeThing>("typeThing", new TypeThing(typeThingId));	
					filters.add(fTThing);
				}
				things = uf.thingService.get(filters);
			}
			
			return new ResponseEntity<List<ThingDTO>>(uf.map.thingMapper.mapToDto(things), HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<List<ThingDTO>>(HttpStatus.FORBIDDEN);
		}
	}
}
