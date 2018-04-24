package com.uaiot.uaitserver.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.PermissionClass;
import com.uaiot.uaitserver.models.TypeThing;
import com.uaiot.uaitserver.models.User;
import com.uaiot.uaitserver.models.UserGroup;

@RestController
@RequestMapping(value = "test")
public class TestController {

	@Autowired
	private UaiotFacade uf;
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String test() throws DAOException, PermissionException {
		
		PermissionClass pc = new PermissionClass();
		pc.setName("Público");
		uf.permissionClassService.insert(pc);
		
		TypeThing tt = new TypeThing();
		tt.setName("Viatura");
		uf.typeThingService.insert(tt);
		
		UserGroup ug = new UserGroup();
		ug.setName("Funcionário prefeitura");
		ug.setPermissionClasses(Arrays.asList(pc));
		uf.userGroupService.insert(ug);
		
		User u = new User();
		u.setLogin("vitor");
		u.setName("Vítor Moreira");
		u.setPassword("senha");
		u.setUserGroup(ug);
		uf.userService.insert(u);
		
		return "Teste porra";
	}
}
