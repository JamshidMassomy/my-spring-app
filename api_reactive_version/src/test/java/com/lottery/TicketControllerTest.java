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
import java.util.UUID;

@WebFluxTest(controllers = TicketController.class)
public class TicketControllerTest {


    @Autowired
    private WebTestClient webClient;

    @MockBean
    private TicketServiceImpl ticketService;

    @Test
    public void testGetEndpoint() {
        webClient.get()
                .uri("/api/v1/ticket")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, World!");
    }

    @Test
    public void shouldReturn200OnSuccess() {
        UUID userId1 = UUID.fromString("246681bc-3107-45ca-87f2-6c196826be26");
        UUID userId = UUID.randomUUID();
        final TicketResponseDto ticketResponseDto = new TicketResponseDto();
        webClient.post()
                .uri("/api/v1/ticket/user/{userID}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TicketResponseDto.class)
                .isEqualTo(ticketResponseDto);
    }
    @Test
    public void shouldThrow410OnOutOfTicket() {

    }

    @Test
    public void shouldThrow403OnIssueTicketAgain() {

    }

    @Test
    public void shouldRespondCorrectly() {

    }

}
