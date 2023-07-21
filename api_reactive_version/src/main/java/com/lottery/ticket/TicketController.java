package com.lottery.ticket;

import com.lottery.ticket.dto.TicketResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/ticket")
@Slf4j
public final class TicketController {
    private final TicketServiceImpl ticketService;

    public TicketController (final TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(path = "user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TicketResponseDto> issueTicket(@PathVariable() Long userId) {
        log.info("request ticket by user id - {}", userId);
        return ticketService.issueTicket(userId);
    }

}
