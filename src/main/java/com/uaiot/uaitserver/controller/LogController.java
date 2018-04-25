package com.uaiot.uaitserver.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.uaiot.uaitserver.dto.CoordinateDTO;
import com.uaiot.uaitserver.dto.LogDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
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
			if (logDTO.getTime() == null) {
				logDTO.setTime(new Date(System.currentTimeMillis()));
			}
			
			Log log = uf.map.logMapper.mapToObj(logDTO);
			uf.logService.insert(log);
			
			log.getThing().setLatitude(logDTO.getLatitude());
			log.getThing().setLongitude(logDTO.getLongitude());
			
			uf.thingService.update(log.getThing());
			
			return new ResponseEntity<LogDTO>(uf.map.logMapper.mapToDto(log),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<LogDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LogDTO>> get(@RequestParam(required = false) Long thingImei,
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
	public ResponseEntity<List<CoordinateDTO>> currentPosition(@RequestParam(required = false) Long thingImei,
			@RequestParam(required = false) Integer typeThingId) throws DAOException, PermissionException {
		
		List<Thing> things = null;
		
		if (thingImei != null) {
			Thing t = uf.thingService.findById(thingImei);
			if (t != null)
				things = Arrays.asList(t);
		} else {
			List<Filter> filters = new ArrayList<Filter>();
			if (typeThingId != null) {
				Filter<TypeThing> fTThing = new Filter<TypeThing>("typeThing", new TypeThing(typeThingId));
				filters.add(fTThing);
			}
			things = uf.thingService.get(filters);
		}
		
		List<CoordinateDTO> coordinates = new ArrayList<CoordinateDTO>();
		for (Thing thing : things) {
			if (thing.getLatitude() != null && thing.getLongitude() != null) {
				CoordinateDTO coordinate = new CoordinateDTO();
				coordinate.setLatitude(thing.getLatitude());
				coordinate.setLongitude(thing.getLongitude());
				coordinate.setThing(uf.map.thingMapper.mapToDto(thing));
				coordinates.add(coordinate);
			}
		}
		
		return new ResponseEntity<List<CoordinateDTO>>(coordinates, HttpStatus.OK);
	}
}
