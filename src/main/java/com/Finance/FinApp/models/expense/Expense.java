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

    public Double totalAnnualExp(){
        Double total = amount * getFrequency();
        return total;
    }

    public Double getFrequency(){
        Double frequency = 0.00;
        switch (repetition){
            case WEEKLY -> {
                frequency = 52.0;
                break;
            }
            case MONTHLY -> {
                frequency = 12.0;
                break;
            }
            case QUARTERLY -> {
                frequency = 4.0;
                break;
            }
            case ANNUALLY -> {
                frequency = 1.0;
                break;
            }
        }
        return frequency;
    }
}
