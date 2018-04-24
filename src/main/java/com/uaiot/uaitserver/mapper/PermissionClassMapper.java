package com.uaiot.uaitserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.PermissionClassDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.PermissionClass;

@Service
public class PermissionClassMapper extends Mapper<PermissionClass, PermissionClassDTO>{

	@Autowired
	private UaiotFacade uf;

	@Override
	public PermissionClass mapToObj(PermissionClassDTO dto) throws PermissionException {
		
		try {
			
			PermissionClass permission = uf.permissionClassService.findById(dto.getId());
			if (permission == null) {
				permission = uf.map.modelMapper.map(dto, PermissionClass.class);
			} else {
				uf.map.modelMapper.map(dto, permission);
			}
			
			return permission;
		} catch (DAOException ex) {
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	public PermissionClassDTO mapToDto(PermissionClass obj) throws PermissionException {
		
		PermissionClassDTO dto = uf.map.modelMapper.map(obj, PermissionClassDTO.class);
		
		return dto;
	}
	
}
