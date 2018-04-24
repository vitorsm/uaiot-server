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
import com.uaiot.uaitserver.dto.UserDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.User;
import com.uaiot.uaitserver.models.UserGroup;

@RestController
@RequestMapping(value = "serv/user")
public class UserController {
	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO) throws DAOException {
		
		try {
			User user = uf.map.userMapper.mapToObj(userDTO);
			uf.userService.insert(user);
			
			return new ResponseEntity<UserDTO>(uf.map.userMapper.mapToDto(user),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) throws DAOException {
		
		try {
			User user = uf.map.userMapper.mapToObj(userDTO);
			uf.userService.update(user);
			
			return new ResponseEntity<UserDTO>(uf.map.userMapper.mapToDto(user),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> delete(@PathVariable("id") int userId) throws DAOException {
		
		try {
			User user = uf.userService.findById(userId);
			uf.userService.delete(user);
			
			return new ResponseEntity<UserDTO>(uf.map.userMapper.mapToDto(user),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> get(@RequestParam(required = false) Integer userId,
			@RequestParam(required = false) Integer userGroupId) throws DAOException {
		
		try {
			List<User> users = new ArrayList<User>();
			
			if (userId != null) {
				User user = uf.userService.findById(userId);
				users.add(user);
			} else {
				List<Filter> filters = new ArrayList<Filter>();
				if (userGroupId != null) {
					Filter<UserGroup> fUserGroup = new Filter<UserGroup>("userGroup", new UserGroup(userId));
					filters.add(fUserGroup);
				}
				users = uf.userService.get(filters);
			}
			
			return new ResponseEntity<List<UserDTO>>(uf.map.userMapper.mapToDto(users),
					HttpStatus.OK);
		} catch (PermissionException ex) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.FORBIDDEN);
		}
	}
}
