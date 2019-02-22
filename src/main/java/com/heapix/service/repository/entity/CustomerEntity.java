package com.heapix.service.repository.entity;

import com.heapix.service.reusable.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String patronymic;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private double balance;

    @OneToMany(mappedBy = "customer")
    private Collection<PartnerEntity> partners;
}

