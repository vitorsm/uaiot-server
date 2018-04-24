package com.uaiot.uaitserver.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestController {

	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String test() {
		return "Teste porra";
	}
}
