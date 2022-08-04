package com.Finance.FinApp.controller;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.models.debit.Debit;
import com.Finance.FinApp.service.CreditService;
import com.Finance.FinApp.service.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/debit")
public class DebitController {

    @Autowired
    private DebitService debitService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createDebit(Model model, @ModelAttribute Debit debit){
        debitService.createDebit(debit);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable( value = "id") Long id, Model model){

        Debit debit = debitService.getDebitById(id);
        model.addAttribute("debit", debit);
        return "debUpdate";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteDebit(@PathVariable(value = "id") Long id){
        debitService.deleteDebit(id);
        return "redirect:/debit/";
    }
}
