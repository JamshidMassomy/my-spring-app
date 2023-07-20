package com.lottery.service;

import com.lottery.dto.TicketResponseDto;
import com.lottery.entity.Ticket;
import com.lottery.entity.User;
import com.lottery.repository.TicketRepository;
import com.lottery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.Cacheable;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public final class TicketServiceImpl implements ITicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private static final int MAX_TICKET = 30;
    private static final String TICKET_EXISTS = "Ticket Exists";
    private static final String OUT_OF_TICKET = "Tickets reached max limit";


    private static int generateRandomLotteryNumber() {
        final Random random = new Random();
        return random.nextInt(30) + 1;
    }

    @Cacheable("ticketCounts")
    private int countExistingTickets() {
        return ticketRepository.countAllTickets();
    }

    private Ticket getUserTicket(final Long userId) {
        return ticketRepository.findTicketByUserId(userId);
    }

    @Override
    public TicketResponseDto issueTicket(final Long userId) {
        final TicketResponseDto ticketResponseDto = new TicketResponseDto();
        if (countExistingTickets() >= MAX_TICKET) {
            throw new ResponseStatusException(HttpStatus.GONE, OUT_OF_TICKET);
        }
        if (getUserTicket(userId) == null) {
            final Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                final Ticket ticket = Ticket.builder().
                        ticketNumber(generateRandomLotteryNumber()).
                        user(user.get())
                        .build();
                ticketResponseDto.setNow(new Date());
                ticketResponseDto.setConfirmationNumber(ticket.getTicketNumber());
                ticketRepository.save(ticket);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, TICKET_EXISTS);
        }

        return ticketResponseDto;
    }
}
