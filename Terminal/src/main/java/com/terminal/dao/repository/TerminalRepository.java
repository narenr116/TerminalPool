package com.terminal.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terminal.dao.model.Terminal;
@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Integer> {
	
	Terminal findByStatus(String status);
	Terminal findFirstByStatusOrderBySequenceNumDesc(String status);
	Terminal findByterminalId(Integer id);
}
