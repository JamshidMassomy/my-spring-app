package com.lottery.ticket;

import com.lottery.ticket.dto.TicketResponseDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public interface ILotteryService {
    Mono<TicketResponseDto> issueTicket(UUID userId);
}
