package com.uaiot.uaitserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.ThingDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Thing;

@Service
public class ThingMapper extends Mapper<Thing, ThingDTO> {

	@Autowired
	private UaiotFacade uf;

	@Override
	public Thing mapToObj(ThingDTO dto) throws PermissionException {
		
		try {
			Thing thing = uf.thingService.findById(dto.getImei());
			
			if (thing == null) {
				thing = uf.map.modelMapper.map(dto, Thing.class);
			} else {
				uf.map.modelMapper.map(dto, thing);
			}
			
			if (dto.getTypeThing() != null)
				thing.setTypeThing(uf.typeThingService.findById(dto.getTypeThing().getId()));
			
			return thing;
		} catch (DAOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ThingDTO mapToDto(Thing obj) throws PermissionException {
		
		ThingDTO dto = uf.map.modelMapper.map(obj, ThingDTO.class);
		return dto;
		
	}
}
