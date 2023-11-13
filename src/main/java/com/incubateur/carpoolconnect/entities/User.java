package com.incubateur.carpoolconnect.entities;

import com.incubateur.carpoolconnect.audit.AbstractAuditable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends AbstractAuditable implements UserDetails {

    @Serial
    private static final long serialVersionUID = 7393011510900592518L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private int points;

    private String activationKey;

    private String passwordRenewalKey;

    private boolean isActive;

    @OneToMany(mappedBy = "user")
    private List<ChatMessage> messagesSent;

    @OneToMany(mappedBy = "user")
    private List<ChatMessage> messagesReceived;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "driver")
    private List<Route> routesProposed;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user")
    private List<Car> cars;

    @OneToMany(mappedBy = "user")
    private List<Picture> icons;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    @ManyToOne
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
