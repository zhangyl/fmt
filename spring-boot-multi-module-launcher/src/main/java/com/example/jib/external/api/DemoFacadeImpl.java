package com.example.jib.external.api;

import org.springframework.stereotype.Component;

@Component
public class DemoFacadeImpl implements DemoFacade {

	@Override
	public String say(String words) {
		return "hi, " + words;
	}

}
