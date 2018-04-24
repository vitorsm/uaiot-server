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
import com.uaiot.uaitserver.dto.PermissionClassDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.PermissionClass;

@RestController
@RequestMapping(value = "serv/permission-class")
public class PermissionClassController {

	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionClassDTO> insert(@RequestBody PermissionClassDTO permissionClassDTO) throws DAOException {
		
		try {
			PermissionClass permissionClass = uf.map.permissionClassMapper.mapToObj(permissionClassDTO);
			uf.permissionClassService.insert(permissionClass);
			
			return new ResponseEntity<PermissionClassDTO>(uf.map.permissionClassMapper.mapToDto(permissionClass),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<PermissionClassDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionClassDTO> update(@RequestBody PermissionClassDTO permissionClassDTO) throws DAOException {
		
		try {
			PermissionClass permissionClass = uf.map.permissionClassMapper.mapToObj(permissionClassDTO);
			uf.permissionClassService.update(permissionClass);
			
			return new ResponseEntity<PermissionClassDTO>(uf.map.permissionClassMapper.mapToDto(permissionClass),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<PermissionClassDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PermissionClassDTO> delete(@PathVariable("id") int permissionClassId) throws DAOException {
		
		try {
			PermissionClass permissionClass = uf.permissionClassService.findById(permissionClassId);
			uf.permissionClassService.delete(permissionClass);
			
			return new ResponseEntity<PermissionClassDTO>(uf.map.permissionClassMapper.mapToDto(permissionClass),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<PermissionClassDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PermissionClassDTO>> get(@RequestParam(required = false) Integer permissionClassId) throws DAOException {
		
		try {
			List<PermissionClass> permissionClasses = new ArrayList<PermissionClass>();
			
			if (permissionClassId != null) {
				PermissionClass permissionClass = uf.permissionClassService.findById(permissionClassId);
				permissionClasses.add(permissionClass);
			} else {
				List<Filter> filters = new ArrayList<Filter>();
				permissionClasses = uf.permissionClassService.get(filters);
			}
			
			return new ResponseEntity<List<PermissionClassDTO>>(uf.map.permissionClassMapper.mapToDto(permissionClasses), HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<List<PermissionClassDTO>>(HttpStatus.FORBIDDEN);
		}
	}
}
