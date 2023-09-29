package com.Finance.FinApp.service;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.models.credit.CreditRepo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class  CreditService {

    private List<Credit> credits;

    private final CreditRepo creditRepo;

    public CreditService(CreditRepo creditRepo){
        this.creditRepo = creditRepo;
    }

    public Credit createCredit(Credit credit){
        return creditRepo.save(credit);
    }

    public void deleteCredit(Long id){
/*        Credit credit = null;
        Optional optional = creditRepo.findById(id);
        if (optional.isPresent()) {
            credit = creditRepo.findById(id).get();
            creditRepo.deleteById(id);
        }*/
        creditRepo.deleteById(id);
    }

    public Credit updateCredit(Credit credit){
        creditRepo.save(credit);
        return credit;
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
        Double sum = Double.valueOf(0);
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
