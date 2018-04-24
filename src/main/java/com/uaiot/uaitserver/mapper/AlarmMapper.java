package com.uaiot.uaitserver.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaiot.uaitserver.dao.DAOException;
import com.uaiot.uaitserver.dto.AlarmDTO;
import com.uaiot.uaitserver.exceptions.PermissionException;
import com.uaiot.uaitserver.facade.UaiotFacade;
import com.uaiot.uaitserver.models.Alarm;

@Service
public class AlarmMapper extends Mapper<Alarm, AlarmDTO>{

	@Autowired
	private UaiotFacade uf;
	
	@Override
	public Alarm mapToObj(AlarmDTO dto) throws PermissionException {
		if (dto == null) return null;
		
		try {
			Alarm alarm = uf.alarmService.findById(dto.getId());
			
			if (alarm == null) {
				alarm = uf.map.modelMapper.map(dto, Alarm.class);
			} else {
				uf.map.modelMapper.map(dto, alarm);
			}
			
			if (dto.getThing() != null)
				alarm.setThing(uf.thingService.findById(dto.getThing().getId()));
			
			return alarm;
		} catch (DAOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public AlarmDTO mapToDto(Alarm obj) throws PermissionException {
		if (obj == null) return null;
		
		AlarmDTO dto = uf.map.modelMapper.map(obj, AlarmDTO.class);
		return dto;
		
	}

	
}
