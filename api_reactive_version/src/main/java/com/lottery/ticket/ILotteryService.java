package com.lottery.ticket;

import com.lottery.ticket.dto.TicketResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ILotteryService {
    Mono<TicketResponseDto> issueTicket(Long userId);
}
