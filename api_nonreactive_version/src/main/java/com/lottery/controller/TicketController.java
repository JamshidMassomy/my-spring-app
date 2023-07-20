package com.lottery.controller;

import com.lottery.dto.TicketResponseDto;
import com.lottery.service.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/ticket")
@RequiredArgsConstructor
@RestController
public class TicketController {

    private final TicketServiceImpl ticketService;
    @PostMapping("user/{userId}")
    public ResponseEntity<TicketResponseDto> issueTicket(@PathVariable Long userId) {
        return new ResponseEntity(ticketService.issueTicket(userId), HttpStatus.OK);
    }

}
