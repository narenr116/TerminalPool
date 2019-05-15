package com.terminal.validation.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.terminal.constants.TerminalConstants;
import com.terminal.dto.model.TerminalDTO;

@Service
public class ValidationService {
	
	private static final Logger LOGGER = Logger.getLogger(ValidationService.class);

	RestTemplate restTemplate = new RestTemplate();
	
	@Value("${terminalService.url}")
	private String terminalUrl;
	
	public TerminalDTO getTerminal() {

		TerminalDTO outData = null;
		try {
			String url=terminalUrl+"activeTerminal";
			LOGGER.debug(" getTerminal :: Start::");
			LOGGER.debug(" getTerminal :: url::" +url);
			outData= restTemplate.getForObject(url, TerminalDTO.class);
			
			if(outData!=null && outData.getStatus().equals(TerminalConstants.ACTIVE) && outData.getSequenceNum() == null) {
			
				url=terminalUrl+"lockTerminalId";
				LOGGER.debug(" getTerminal :: url::" +url);
				restTemplate.put(url, outData);
				Thread.sleep(10000);
				
				url=terminalUrl+"unlockTerminal";
				LOGGER.debug(" getTerminal :: url::" +url);
				restTemplate.put(url, outData);
			}
			
		} catch (Exception e) {
			LOGGER.error("Exception Occured :: "+ e.getMessage());
		}
		LOGGER.debug(" getTerminal :: End::");
		return outData;
	}
	
}
