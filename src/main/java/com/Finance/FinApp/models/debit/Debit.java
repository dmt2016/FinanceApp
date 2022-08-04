package com.Finance.FinApp.models.debit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Debit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate date;
    private String company;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Type type;
}
