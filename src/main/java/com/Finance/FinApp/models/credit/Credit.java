package com.Finance.FinApp.models.credit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern="dd.MM.yyyy")
    private LocalDate date;
    private String company;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Category category;

    public int getYear(){
        return date.getYear();
    }

}
