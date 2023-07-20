package com.lottery.service;

import com.lottery.dto.TicketResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ITicketService {
    TicketResponseDto issueTicket(Long userId);
}
