package com.Finance.FinApp.service;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.models.credit.CreditRepo;
import com.Finance.FinApp.models.debit.Debit;
import com.Finance.FinApp.models.debit.DebitRepo;
import com.Finance.FinApp.models.expense.Expense;
import com.Finance.FinApp.models.income.Income;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class Tracker {

    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private DebitRepo debitRepo;

    private List<Expense> expenses;
    private List<Income> income;
    private List<Credit> credits;
    private List<Debit> debits;
    private Date startDate;
    private Date endDate;

    public Double getTotalCredits(Date startDate, Date endDate){
         double sum = 0;
        for (int i = 0; i < credits.size(); i++)
            sum = sum + credits.get(i).getAmount();
        return sum;
    }

    public Double getTotalExpenses(){
        return null;
    }

    public Double getTotalIncome(){
        return null;
    }
}
