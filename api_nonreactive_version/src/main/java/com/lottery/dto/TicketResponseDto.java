package com.lottery.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public final class TicketResponseDto {
    private Integer confirmationNumber;
    private Date now;
}
