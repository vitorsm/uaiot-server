package com.uaiot.uaitserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.UserDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.User;

@Service
public class UserMapper extends Mapper<User, UserDTO>{

	@Autowired
	private UaiotFacade uf;

	@Override
	public User mapToObj(UserDTO dto) throws PermissionException {

		try {
			User user = uf.userService.findById(dto.getId());
			
			if (user == null) {
				user = uf.map.modelMapper.map(dto, User.class);
			} else {
				uf.map.modelMapper.map(dto, user);
			}
			
			if (dto.getUserGroup() != null)
				user.setUserGroup(uf.userGroupService.findById(dto.getUserGroup().getId()));
			
			return user;
		} catch (DAOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public UserDTO mapToDto(User obj) throws PermissionException {
		
		UserDTO dto = uf.map.modelMapper.map(obj, UserDTO.class);
		
		return dto;
	}
	
}
