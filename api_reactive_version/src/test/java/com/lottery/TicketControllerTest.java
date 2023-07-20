package com.lottery;


import com.lottery.ticket.Ticket;
import com.lottery.ticket.TicketRepository;
import com.lottery.ticket.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {


    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketRepository ticketRepository;


    @Test
    public void shouldThrow410OnOutOfTicket() {
        when(ticketService.findExistingTicket(anyLong()))
                .thenReturn(Mono.empty());
        Mockito.when(ticketService.getTicketCounts())
                .thenReturn(Mono.just(30));
        StepVerifier.create(ticketService.issueTicketInternal(1L))
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException
                        && ((ResponseStatusException) throwable).getStatus().equals(HttpStatus.GONE))
                .verify();
    }

    @Test
    public void shouldThrow403OnIssueTicketAgain() {
        when(ticketService.findExistingTicket(anyLong()))
                .thenReturn(Mono.just(new Ticket()));

        when(ticketService.getTicketCounts())
                .thenReturn(Mono.just(0));
        StepVerifier.create(ticketService.issueTicketInternal(1L))
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException
                        && ((ResponseStatusException) throwable).getStatus().equals(HttpStatus.FORBIDDEN))
                .verify();
    }

}
