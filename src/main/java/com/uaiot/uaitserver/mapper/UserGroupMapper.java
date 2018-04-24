package com.uaiot.uaitserver.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.PermissionClassDTO;
import com.uaiot.uaitserver.dto.UserGroupDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.PermissionClass;
import com.uaiot.uaitserver.models.UserGroup;

@Service
public class UserGroupMapper extends Mapper<UserGroup, UserGroupDTO>{
	
	@Autowired
	private UaiotFacade uf;
	
	
	@Override
	public UserGroup mapToObj(UserGroupDTO dto) throws PermissionException {
		
		try {
			UserGroup userGroup = uf.userGroupService.findById(dto.getId());
			
			if (userGroup == null) {
				userGroup = uf.map.modelMapper.map(dto, UserGroup.class);
			} else {
				uf.map.modelMapper.map(dto, userGroup);
			}
			
			if (dto.getPermissionClasses() != null) {
				List<PermissionClass> permissionClass = new ArrayList<PermissionClass>();	
				for (PermissionClassDTO pc : dto.getPermissionClasses()) {
					PermissionClass obj = uf.permissionClassService.findById(pc.getId());
					if (obj != null)
						permissionClass.add(obj);
				}
			}

			return userGroup;
		} catch (DAOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public UserGroupDTO mapToDto(UserGroup obj) throws PermissionException {
		
		UserGroupDTO userGroup = uf.map.modelMapper.map(obj, UserGroupDTO.class);
		return userGroup;
		
	}

}
