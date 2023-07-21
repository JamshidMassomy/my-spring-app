package com.lottery.ticket.dto;

import lombok.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class TicketResponseDto {
    private Integer confirmationCode;
    private Date date;
}