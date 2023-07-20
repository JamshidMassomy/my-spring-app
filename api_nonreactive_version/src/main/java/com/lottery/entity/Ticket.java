package com.lottery.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ticketNumber;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

}
