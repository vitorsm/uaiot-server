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
import com.uaiot.uaitserver.dto.UserGroupDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.UserGroup;

@RestController
@RequestMapping(value = "serv/user-group")
public class UserGroupController {
	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserGroupDTO> insert(@RequestBody UserGroupDTO userGroupDTO) throws DAOException {
		
		try {
			UserGroup userGroup = uf.map.userGroupMapper.mapToObj(userGroupDTO);
			uf.userGroupService.insert(userGroup);
			
			return new ResponseEntity<UserGroupDTO>(uf.map.userGroupMapper.mapToDto(userGroup),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<UserGroupDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserGroupDTO> update(@RequestBody UserGroupDTO userGroupDTO) throws DAOException {
		
		try {
			UserGroup userGroup = uf.map.userGroupMapper.mapToObj(userGroupDTO);
			uf.userGroupService.update(userGroup);
			
			return new ResponseEntity<UserGroupDTO>(uf.map.userGroupMapper.mapToDto(userGroup),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<UserGroupDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserGroupDTO> delete(@PathVariable("id") int userGroupId) throws DAOException {
		
		try {
			UserGroup userGroup = uf.userGroupService.findById(userGroupId);
			uf.userGroupService.delete(userGroup);
			
			return new ResponseEntity<UserGroupDTO>(uf.map.userGroupMapper.mapToDto(userGroup),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<UserGroupDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserGroupDTO>> get(@RequestParam(required = false) Integer userGroupId) throws DAOException {
		
		try {
			List<UserGroup> usersGroup = new ArrayList<UserGroup>();
			
			if (userGroupId != null) {
				UserGroup userGroup = uf.userGroupService.findById(userGroupId);
				usersGroup.add(userGroup);
			} else {
				List<Filter> filters = new ArrayList<Filter>();
				usersGroup = uf.userGroupService.get(filters);
			}
			
			return new ResponseEntity<List<UserGroupDTO>>(uf.map.userGroupMapper.mapToDto(usersGroup),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<List<UserGroupDTO>>(HttpStatus.FORBIDDEN);
		}
	}
}
