package com.uaiot.uaitserver.utils;

import java.io.IOException;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class JSONUtils {
private static ObjectMapper mapper;
	
	
	public static Object getObject(String json, Class c) throws IOException {
		mapper = new ObjectMapper();
		
		try {
			return mapper.readValue(json, c);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Falha ao converter json para objeto", e);
		}
	}
	
	public static Object getList(String json, Class c, boolean all) throws IOException {
		mapper = new ObjectMapper();
		
		if (!all) {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		}
		
		try {
			return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(LinkedList.class, c));
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Falha ao converter json para lista de objetos", e);
		}
	}
}
