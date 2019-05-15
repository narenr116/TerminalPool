package com.terminal.dto.model;

import java.util.Date;

public class TerminalDTO {
	
    private Integer terminalId;
	
    private Integer sequenceNum;
	
    private Date timestamp;
    
    private String status;

	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	public Integer getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TerminalDTO [terminalId=" + terminalId + ", sequenceNum=" + sequenceNum + ", timestamp=" + timestamp
				+ ", status=" + status + "]";
	}
	
}
