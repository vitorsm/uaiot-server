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
import com.uaiot.uaitserver.dto.AlarmDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Alarm;
import com.uaiot.uaitserver.models.Thing;

@RestController
@RequestMapping(value = "serv/alarm")
public class AlarmController {
	
	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlarmDTO> insert(@RequestBody AlarmDTO alarmDTO) throws DAOException {
		
		try {
			Alarm alarm = uf.map.alarmMapper.mapToObj(alarmDTO);
			uf.alarmService.insert(alarm);
			
			return new ResponseEntity<AlarmDTO>(uf.map.alarmMapper.mapToDto(alarm),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<AlarmDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlarmDTO> update(@RequestBody AlarmDTO alarmDTO) throws DAOException {
		
		try {
			Alarm alarm = uf.map.alarmMapper.mapToObj(alarmDTO);
			uf.alarmService.update(alarm);
			
			return new ResponseEntity<AlarmDTO>(uf.map.alarmMapper.mapToDto(alarm),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<AlarmDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlarmDTO> delete(@PathVariable("id") int alarmId) throws DAOException {
		
		try {
			Alarm alarm = uf.alarmService.findById(alarmId);
			uf.alarmService.delete(alarm);
			
			return new ResponseEntity<AlarmDTO>(uf.map.alarmMapper.mapToDto(alarm),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<AlarmDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlarmDTO>> get(@RequestParam(required = false) Integer alarmId,
			@RequestParam(required = false) Integer thingImei,
			@RequestParam(required = false) Boolean active) throws DAOException {
		
		try {
			List<Alarm> alarms = new ArrayList<Alarm>();
			
			if (alarmId != null) {
				Alarm alarm = uf.alarmService.findById(alarmId);
				alarms.add(alarm);
			} else {
				List<Filter> filters = new ArrayList<Filter>();
				if (thingImei != null) {
					Filter<Thing> fThing = new Filter<Thing>("thing", new Thing(thingImei));
					filters.add(fThing);
				}
				
				if (active != null) {
					Filter<Boolean> fActive = new Filter<Boolean>("active", active);
					filters.add(fActive);
				}
				
				alarms = uf.alarmService.get(filters);
			}
			
			return new ResponseEntity<List<AlarmDTO>>(uf.map.alarmMapper.mapToDto(alarms), HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<List<AlarmDTO>>(HttpStatus.FORBIDDEN);
		}
	}
}
