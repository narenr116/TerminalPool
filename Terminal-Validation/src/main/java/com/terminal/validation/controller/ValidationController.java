package com.terminal.validation.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.terminal.dto.model.TerminalDTO;
import com.terminal.validation.service.ValidationService;

@RestController
@RequestMapping("/validation")
public class ValidationController {
	
	private static final Logger LOGGER = Logger.getLogger(ValidationController.class);

	@Autowired
	ValidationService validationService;
	
	@RequestMapping(value = "/terminal", method = RequestMethod.GET, produces = "application/json")
	public TerminalDTO getTerminal() {

		TerminalDTO data = null;
		LOGGER.debug(" terminal :: Start::");
		data = validationService.getTerminal();
		LOGGER.debug(" lockTerminal :: data :: "+ data.toString());
		if (data == null) {
			LOGGER.error(HttpStatus.BAD_REQUEST);
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
		LOGGER.debug(" terminal :: End::");
		return data;
	}
}
