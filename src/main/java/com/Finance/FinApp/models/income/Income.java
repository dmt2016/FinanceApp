package com.Finance.FinApp.models.income;

import com.Finance.FinApp.models.expense.Repetition;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Repetition repetition;
    @Enumerated(EnumType.STRING)
    private IncomeType incomeType;
}
