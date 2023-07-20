package com.lottery.ticket;

import com.lottery.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;


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
    private UUID id;

    @Column("confirmation_code")
    private String confirmationCode;

    @Column("user_id")
    private UUID userId;

}
