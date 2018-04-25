package com.uaiot.uaitserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.CoordinateDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Coordinate;

@Service
public class CoordinateMapper extends Mapper<Coordinate, CoordinateDTO>{

	@Autowired
	private UaiotFacade uf;
	
	@Override
	public Coordinate mapToObj(CoordinateDTO dto) throws PermissionException {
		
		try {
			Coordinate coordinate = uf.map.modelMapper.map(dto, Coordinate.class);
			
			if (dto.getThing() != null) {
				coordinate.setThing(uf.thingService.findById(dto.getThing().getId()));
			}
			
			return coordinate;
		} catch (DAOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public CoordinateDTO mapToDto(Coordinate obj) throws PermissionException {
		CoordinateDTO dto = uf.map.modelMapper.map(obj, CoordinateDTO.class);
		
		return dto;
	}

}
