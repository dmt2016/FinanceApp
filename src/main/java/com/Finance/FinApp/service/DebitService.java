package com.Finance.FinApp.service;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.models.debit.Debit;
import com.Finance.FinApp.models.debit.DebitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DebitService {

    private List<Debit> debits;
    @Autowired
    private DebitRepo debitRepo;

    public Debit createDebit(Debit debit){
        return debitRepo.save(debit);
    }

    public void deleteDebit(Long id){
        debitRepo.deleteById(id);
    }

    public void updateDebit(Debit debit){
        debitRepo.save(debit);
    }

    public Debit getDebitById(Long id) {
        Optional<Debit> optionalDebit = debitRepo.findById(id);
        Debit debit = null;
        if (optionalDebit.isPresent()) {
            debit = optionalDebit.get();
        } else {
            throw new RuntimeException("Debit not found for id : " + id);
        }
        return debit;
    }

    public List<Debit> getDebits(){ return debitRepo.findAll();}

    public Double getYTDDebits(){
        Double sum = 0.0;
        int currYear = LocalDate.now().getYear();
        LocalDate startDate = LocalDate.of(currYear, 1, 1);
        LocalDate endDate = LocalDate.of(currYear,12,31);
        debits = debitRepo.findByDateBetween(startDate, endDate);
        for (Debit eachDebit : debits){
            sum = sum + eachDebit.getAmount();
        }
        return sum;
    }
}
