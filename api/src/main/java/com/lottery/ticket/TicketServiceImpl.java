package com.lottery.ticket;


import com.lottery.exception.TicketAlreadyExistsException;
import com.lottery.exception.TicketLimitException;
import com.lottery.ticket.dto.TicketResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
@Slf4j
public final class TicketServiceImpl implements ILotteryService {

    private static final int MAX_TICKET = 30;
    private static final String TICKET_ALREADY_EXISTS = "Ticket already exists for the user with ID: ";
    private static final String OUT_OF_TICKET = "Out of tickets";
    private final AtomicInteger ticketCount = new AtomicInteger(0);
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(final TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    private static int generateRandomNumber() {
        final Random random = new Random();
        return random.nextInt(30) + 1;
    }


    @Override
    public Mono<TicketResponseDto> issueTicket(final UUID userId) {
        Mono<Ticket> issuedTicket = ticketRepository.findByUserId(userId);
        return issuedTicket.flatMap(existingTicket ->
                Mono.error(new TicketAlreadyExistsException(TICKET_ALREADY_EXISTS + userId))
                                .switchIfEmpty(Mono.defer(() -> {
                                    if (ticketCount.incrementAndGet() <= MAX_TICKET) {
                                        final Ticket ticket = Ticket.builder()
                                                .userId(UUID.randomUUID())
                                                .confirmationCode(UUID.randomUUID().toString())
                                                .userId(userId)
                                                .build();
                                        log.info("saving ticket {}", ticket);
                                        return ticketRepository.save(ticket);
                                    } else {
                                        ticketCount.decrementAndGet();
                                        return Mono.error(new TicketLimitException(OUT_OF_TICKET));
                                    }
                                })).
                        map(ticket -> new TicketResponseDto(UUID.randomUUID().toString(), new Date())));

    }


}
