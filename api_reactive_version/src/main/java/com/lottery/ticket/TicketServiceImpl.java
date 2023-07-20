package com.lottery.ticket;

import com.lottery.ticket.dto.TicketResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import java.util.*;


@Service
@Slf4j
public class TicketServiceImpl implements ILotteryService {

    private static final int MAX_TICKET = 2;
    private static final String TICKET_ALREADY_EXISTS = "Ticket already exists for the user with ID: ";
    private static final String OUT_OF_TICKET = "Out of tickets";
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(final TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public static int generateRandomLotteryNumber() {
        final Random random = new Random();
        return random.nextInt(30) + 1;
    }

    public Mono<Integer> getTicketCounts() {
        return ticketRepository.countTickets();
    }

    public Mono<Boolean> isTicketCountsExceeded() {
        return this.getTicketCounts()
                .map(ticketCount -> ticketCount > MAX_TICKET);
    }


    public Mono<Ticket> findExistingTicket(final Long userId) {
        return ticketRepository.findByUserId(userId);
    }



    private Mono<Ticket> saveNewTicket(final Long userId) {
        final Ticket ticket = Ticket.builder()
                .ticketNumber(generateRandomLotteryNumber())
                .userId(userId)
                .build();
        log.info("saving ticket {}", ticket);
        return ticketRepository.save(ticket);
    }

    public Mono<TicketResponseDto> issueTicketInternal(final Long userId) {
        final TicketResponseDto ticketResponseDto = new TicketResponseDto();
        return findExistingTicket(userId)
                .flatMap(existingTicket -> {
                    return Mono.error(new ResponseStatusException(HttpStatus.FORBIDDEN, TICKET_ALREADY_EXISTS));
                })
                .switchIfEmpty(isTicketCountsExceeded()
                    .flatMap(isCountExceeded -> {
                        if (!isCountExceeded) {
                            return saveNewTicket(userId)
                                    .flatMap(ticket -> {
                                        ticketResponseDto.setConfirmationCode(ticket.getTicketNumber());
                                        ticketResponseDto.setDate(new Date());
                                        return Mono.just(ticketResponseDto);
                                    });
                        } else {
                            return Mono.error(new ResponseStatusException(HttpStatus.GONE, OUT_OF_TICKET));
                        }
                    })).map(ticket -> ticketResponseDto);
    }


    @Override
    public Mono<TicketResponseDto> issueTicket(final Long userId) {
        return issueTicketInternal(userId);
    }

}
