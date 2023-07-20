package com.lottery.ticket;

import com.lottery.ticket.Ticket;
import com.lottery.user.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Repository
public interface TicketRepository extends ReactiveCrudRepository<Ticket, Long> {

    Mono<Ticket> findByUserId(Long userId);

    @Query("SELECT COUNT(t) FROM Ticket t")
    Mono<Integer> countTickets();
}
