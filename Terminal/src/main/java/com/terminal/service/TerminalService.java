package com.terminal.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terminal.constants.TerminalConstants;
import com.terminal.dao.model.Terminal;
import com.terminal.dao.repository.TerminalRepository;
import com.terminal.dto.model.TerminalDTO;

@Service
public class TerminalService {

	private static final Logger LOGGER = Logger.getLogger(TerminalService.class);
	
	@Autowired
	TerminalRepository terminalRepository;
	private static int seq = -1;

	public TerminalDTO getTerminal() {

		TerminalDTO outData = null;
		Terminal terminal = null;

		try {
			for (int i = 0; i <= 30; i++) {
				terminal = terminalRepository.findFirstByStatusOrderBySequenceNumDesc(TerminalConstants.ACTIVE);
				if (terminal != null) {
					seq = seq == 7 ? 0 : seq + 1;
					terminal.setSequenceNum(seq);
					terminal.setStatus(TerminalConstants.LOCKED);
					terminalRepository.save(terminal);
					break;
				}
				Thread.sleep(1000);
			}
			outData = mapTerminal(terminal);
		} catch (Exception e) {
			LOGGER.error("Exception Occured :: "+ e.getMessage());
		}
		return outData;
	}

	public TerminalDTO getActiveTerminal() {

		TerminalDTO outData = null;
		Terminal terminal = null;

		try {
			terminal = terminalRepository.findFirstByStatusOrderBySequenceNumDesc(TerminalConstants.ACTIVE);
			outData = mapTerminal(terminal);
		} catch (Exception e) {
			LOGGER.error("Exception Occured :: "+ e.getMessage());
		}
		return outData;
	}

	public TerminalDTO unlockTerminal(TerminalDTO data) {

		TerminalDTO outData = null;
		Terminal terminal = null;

		try {
			terminal = terminalRepository.findByterminalId(data.getTerminalId());
			if (terminal != null) {
				terminal.setSequenceNum(null);
				terminal.setStatus(TerminalConstants.ACTIVE);
				terminalRepository.save(terminal);
			}
			outData = mapTerminal(terminal);
		} catch (Exception e) {
			LOGGER.error("Exception Occured :: "+ e.getMessage());
		}
		return outData;
	}

	public TerminalDTO lockTerminal(TerminalDTO data) {

		TerminalDTO outData = null;
		Terminal terminal = null,terminal1=null;

		try {
			terminal = terminalRepository.findByterminalId(data.getTerminalId());
			terminal1 = terminalRepository.findFirstByStatusOrderBySequenceNumDesc(TerminalConstants.ACTIVE);
			if (terminal1 != null) {
				seq = seq == 7 ? 0 : seq + 1;
				terminal.setSequenceNum(seq);
				terminal.setStatus(TerminalConstants.LOCKED);
				terminalRepository.save(terminal);
			}
			outData = mapTerminal(terminal);
		} catch (Exception e) {
			LOGGER.error("Exception Occured :: "+ e.getMessage());
		}
		return outData;
	}

	private TerminalDTO mapTerminal(Terminal source) {
		TerminalDTO dest = null;
		if (source != null) {
			dest = new TerminalDTO();
			dest.setSequenceNum(source.getSequenceNum());
			dest.setTerminalId(source.getTerminalId());
			dest.setTimestamp(new Date());
			dest.setStatus(source.getStatus());
		}
		return dest;
	}

}
