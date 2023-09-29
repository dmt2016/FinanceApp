package com.Finance.FinApp.controller;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.models.debit.Debit;
import com.Finance.FinApp.service.CreditService;
import com.Finance.FinApp.service.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private DebitService debitService;
    @Autowired
    private CreditService creditService;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model){
        List<Debit> debits = debitService.getDebits();
        List<Credit> credits = creditService.getCredits();
        Double debTotal = debitService.getYTDDebits();
        Double credTotal = creditService.getYTDCredits();
        Double balTotal = credTotal - debTotal;

        model.addAttribute("balTotal", balTotal);

        model.addAttribute("debits", debits);
        model.addAttribute("debit", new Debit());
        model.addAttribute("debTotal", debTotal);

        model.addAttribute("credits", credits);
        model.addAttribute("credit", new Credit());
        model.addAttribute("credTotal", credTotal);

        return "index";
    }
}
