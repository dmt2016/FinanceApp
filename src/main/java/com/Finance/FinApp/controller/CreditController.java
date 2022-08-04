package com.Finance.FinApp.controller;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createCredit(Model model, @ModelAttribute Credit credit) {
        creditService.createCredit(credit);
        return "redirect:/";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable(value = "id") Long id, Model model) {

        Credit credit = creditService.getCreditById(id);
        model.addAttribute("credit", credit);
        return "credUpdate";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCredit(@PathVariable(value = "id") Long id) {
        creditService.deleteCredit(id);
        return "redirect:/";
    }
}