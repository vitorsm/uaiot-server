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
import com.uaiot.uaitserver.dto.TypeThingDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.TypeThing;

@RestController
@RequestMapping(value = "serv/type-thing")
public class TypeThingController {
	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TypeThingDTO> insert(@RequestBody TypeThingDTO typeThingDTO) throws DAOException {
		
		try {
			TypeThing typeThing = uf.map.typeThingMapper.mapToObj(typeThingDTO);
			uf.typeThingService.insert(typeThing);
			
			return new ResponseEntity<TypeThingDTO>(uf.map.typeThingMapper.mapToDto(typeThing),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<TypeThingDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TypeThingDTO> update(@RequestBody TypeThingDTO typeThingDTO) throws DAOException {
		
		try {
			TypeThing typeThing = uf.map.typeThingMapper.mapToObj(typeThingDTO);
			uf.typeThingService.update(typeThing);
			
			return new ResponseEntity<TypeThingDTO>(uf.map.typeThingMapper.mapToDto(typeThing),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<TypeThingDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TypeThingDTO> delete(@PathVariable("id") int typeThingId) throws DAOException {
		
		try {
			TypeThing typeThing = uf.typeThingService.findById(typeThingId);
			uf.typeThingService.delete(typeThing);
			
			return new ResponseEntity<TypeThingDTO>(uf.map.typeThingMapper.mapToDto(typeThing),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<TypeThingDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TypeThingDTO>> get(@RequestParam(required = false) Integer typeThingId) throws DAOException {
		
		try {
			List<TypeThing> typesThing = new ArrayList<TypeThing>();
			
			if (typeThingId != null) {
				TypeThing typeThing = uf.typeThingService.findById(typeThingId);
				typesThing.add(typeThing);
			} else {
				List<Filter> filters = new ArrayList<Filter>();
				typesThing = uf.typeThingService.get(filters);
			}
			
			return new ResponseEntity<List<TypeThingDTO>>(uf.map.typeThingMapper.mapToDto(typesThing),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<List<TypeThingDTO>>(HttpStatus.FORBIDDEN);
		}
	}
}
