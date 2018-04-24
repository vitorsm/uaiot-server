package com.uaiot.uaitserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dao.Filter;
import com.uaiot.uaitserver.dto.LogDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Coordinate;
import com.uaiot.uaitserver.models.Log;
import com.uaiot.uaitserver.models.Thing;
import com.uaiot.uaitserver.models.TypeThing;

@RestController
@RequestMapping(value = "serv/log")
public class LogController {
	
	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogDTO> insert(@RequestBody LogDTO logDTO) throws DAOException {
		
		try {
			Log log = uf.map.logMapper.mapToObj(logDTO);
			uf.logService.insert(log);
			
			return new ResponseEntity<LogDTO>(uf.map.logMapper.mapToDto(log),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<LogDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LogDTO>> get(@RequestParam(required = false) Integer thingImei,
			@RequestParam(required = false) Integer typeThingId) throws DAOException {
		
		try {
			List<Log> logs = new ArrayList<Log>();
			
			List<Filter> filters = new ArrayList<Filter>();
			if (thingImei != null) {
				Filter<Thing> fThing = new Filter<Thing>("thing", new Thing(thingImei));
				filters.add(fThing);
			} else {
				if (typeThingId != null) {
					Filter<TypeThing> fTypeThing = new Filter<TypeThing>("typeThing", new TypeThing(typeThingId));
					filters.add(fTypeThing);
				}
			}
			
			logs = uf.logService.get(filters);
			
			return new ResponseEntity<List<LogDTO>>(uf.map.logMapper.mapToDto(logs), HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<List<LogDTO>>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "current",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Coordinate>> currentPosition(@RequestParam(required = false) Integer thingImei,
			@RequestParam(required = false) Integer typeThingId) {
		
//		try {
//			return new ResponseEntity<List<Coordinate>>(uf.map.logMapper.mapToDto(logs), HttpStatus.OK);
//		} catch (PermissionException ex) {
//			return new ResponseEntity<List<Coordinate>>(HttpStatus.FORBIDDEN);
//		}
		
		return null;
	}
}
