package com.incubateur.carpoolconnect.entities;

import com.incubateur.carpoolconnect.audit.AbstractAuditable;
import com.incubateur.carpoolconnect.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation extends AbstractAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = -7729023018780257951L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    private Route route;

    @ManyToOne(optional = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
