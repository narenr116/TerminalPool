/**
 * 
 */
package com.terminal.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.terminal.dto.model.TerminalDTO;
import com.terminal.service.TerminalService;

/**
 * @author Naren
 *
 */
@RestController
@RequestMapping("/terminal")
public class TerminalController {

	@Autowired
	TerminalService terminalService;

	private static final Logger LOGGER = Logger.getLogger(TerminalController.class);
	
	@RequestMapping(value = "/lockTerminal", method = RequestMethod.GET, produces = "application/json")
	public TerminalDTO getTerminal() {

		TerminalDTO data = null;
		LOGGER.debug(" lockTerminal :: Start::");
		data = terminalService.getTerminal();
		LOGGER.debug(" lockTerminal :: data :: "+ data.toString());
		if (data == null) {
			LOGGER.error(HttpStatus.SERVICE_UNAVAILABLE);
			throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE);
		}
		LOGGER.debug(" lockTerminal :: End::");
		return data;
	}

	@RequestMapping(value = "/unlockTerminal", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public TerminalDTO unlockTerminal(@RequestBody TerminalDTO terminal) {

		TerminalDTO data = null;
		LOGGER.debug(" unlockTerminal :: Start::");
		data = terminalService.unlockTerminal(terminal);
		LOGGER.debug(" lockTerminal :: data :: "+ data.toString());
		if (data == null) {
			LOGGER.error(HttpStatus.NOT_FOUND);
			throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
		}
		LOGGER.debug(" unlockTerminal :: End::");
		return data;
	}

	@RequestMapping(value = "/activeTerminal", method = RequestMethod.GET, produces = "application/json")
	public TerminalDTO getActiveTerminal() {

		TerminalDTO data = null;
		LOGGER.debug(" activeTerminal :: Start::");
		data = terminalService.getActiveTerminal();
		LOGGER.debug(" lockTerminal :: data :: "+ data.toString());
		LOGGER.debug(" activeTerminal :: End::");
		return data;
	}

	@RequestMapping(value = "/lockTerminalId", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public TerminalDTO lockTerminal(@RequestBody TerminalDTO terminal) {

		TerminalDTO data = null;
		LOGGER.debug(" lockTerminalId :: Start::");
		data = terminalService.lockTerminal(terminal);
		LOGGER.debug(" lockTerminal :: data :: "+ data.toString());
		LOGGER.debug(" lockTerminalId :: End::");
		return data;
	}

}
