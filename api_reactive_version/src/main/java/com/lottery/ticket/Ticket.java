package com.lottery.ticket;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter(AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@ToString
@Table("ticket")
public final class Ticket {

    @Id
    private Long id;

    @Column("ticket_number")
    private Integer ticketNumber;

    @Column("user_id")
    private Long userId;


}
