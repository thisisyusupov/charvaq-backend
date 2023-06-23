package com.example.demo.entity.auth;

import com.example.demo.enums.Status;
import com.example.demo.enums.SystemRoleName;
import com.example.demo.enums.UserType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "db_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "pasport_number")
    private String passportNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "system_role_name")
    SystemRoleName systemRoleName;

    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private Status status = Status.ACTIVE;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private UserType userType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.systemRoleName == null) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(SystemRoleName.SYSTEM_ROLE_SUPER_ADMIN.name());
            return Collections.singleton(simpleGrantedAuthority);
        }
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.systemRoleName.name());
        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return this.login;
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
