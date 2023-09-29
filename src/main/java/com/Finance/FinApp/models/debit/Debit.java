package com.Finance.FinApp.models.debit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Debit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;
    private String company;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Type type;

}
