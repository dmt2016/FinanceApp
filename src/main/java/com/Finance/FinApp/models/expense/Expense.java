package com.Finance.FinApp.models.expense;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Repetition repetition;
    @Enumerated(EnumType.STRING)
    private ExpType expType;
}
