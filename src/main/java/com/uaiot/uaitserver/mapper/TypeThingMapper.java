package com.uaiot.uaitserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.TypeThingDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.TypeThing;

@Service
public class TypeThingMapper extends Mapper<TypeThing, TypeThingDTO> {

	@Autowired
	private UaiotFacade uf;

	@Override
	public TypeThing mapToObj(TypeThingDTO dto) throws PermissionException {
		
		try {
			
			TypeThing tThing = uf.typeThingService.findById(dto.getId());
			if (tThing == null) {
				tThing = uf.map.modelMapper.map(dto, TypeThing.class);
			} else {
				uf.map.modelMapper.map(dto, tThing);
			}
			
			return tThing;
			
		} catch (DAOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public TypeThingDTO mapToDto(TypeThing obj) throws PermissionException {
		TypeThingDTO dto = uf.map.modelMapper.map(obj, TypeThingDTO.class);
		
		return dto;
	}
}
