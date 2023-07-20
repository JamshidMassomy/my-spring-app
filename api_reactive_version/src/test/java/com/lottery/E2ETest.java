package com.lottery;

import com.lottery.ticket.TicketController;
import com.lottery.ticket.TicketServiceImpl;
import com.lottery.ticket.dto.TicketResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = TicketController.class)
public class E2ETest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TicketServiceImpl ticketService;

    @Test
    public void shouldReturn200OnSuccess() {
        final Long userId = 12L;
        webClient.post()
                .uri("/api/v1/ticket/user/{userID}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TicketResponseDto.class);
    }
}
