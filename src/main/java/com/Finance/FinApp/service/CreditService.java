package com.Finance.FinApp.service;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.models.credit.CreditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;

@Service
public class CreditService {

    private List<Credit> credits;
    @Autowired
    private CreditRepo creditRepo;

    public Credit createCredit(Credit credit){
        return creditRepo.save(credit);
    }

    public void deleteCredit(Long id){
        creditRepo.deleteById(id);
    }

    public void updateCredit(Credit credit){
        creditRepo.save(credit);
    }

    public Credit getCreditById(Long id) {
        Optional<Credit> optionalCredit = creditRepo.findById(id);
        Credit credit = null;
        if (optionalCredit.isPresent()) {
            credit = optionalCredit.get();
        } else {
            throw new RuntimeException("Credit not found for id : " + id);
        }
        return credit;
    }

    public List<Credit> getCredits(){
        return creditRepo.findAll();
    }

    public Double getYTDCredits(){
        Double sum = 0.0;
        int currYear = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(currYear, 1, 1);
        LocalDate endDate = LocalDate.of(currYear,12,31);
        credits = creditRepo.findByDateBetween(startDate, endDate);
        for (Credit eachCredit : credits){
            sum = sum + eachCredit.getAmount();
            }
        return sum;
    }
}
