package com.uaiot.uaitserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.ThingDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Thing;

@RestController
@RequestMapping(value = "serv/thing")
public class ThingController {

	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
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
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
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
}
