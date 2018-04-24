package com.uaiot.uaitserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.LogDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Log;

@Service
public class LogMapper extends Mapper<Log, LogDTO>{

	@Autowired
	private UaiotFacade uf;

	@Override
	public Log mapToObj(LogDTO dto) throws PermissionException {
		
		try {
			Log log = uf.logService.findById(dto.getId());
			
			if (log == null) {
				log = uf.map.modelMapper.map(dto, Log.class);
			} else {
				uf.map.modelMapper.map(dto, log);
			}
			
			if (dto.getThing() != null)
				log.setThing(uf.thingService.findById(dto.getThing().getId()));
			
			return log;	
		} catch (DAOException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public LogDTO mapToDto(Log obj) throws PermissionException {
		
		LogDTO dto = uf.map.modelMapper.map(obj, LogDTO.class);
		return dto;
		
	}
	
}
