package com.Finance.FinApp.controller;

import com.Finance.FinApp.models.debit.Debit;
import com.Finance.FinApp.service.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/update")
    @ResponseBody
    public Debit debUpModal(Long id) {
        return debitService.getDebitById(id);
    }

    @RequestMapping(value = "/updated", method = {RequestMethod.PUT, RequestMethod.GET})
    public String debitUpdate(Debit debit) {
        debitService.updateDebit(debit);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteDebit(@PathVariable(value = "id") Long id){
        debitService.deleteDebit(id);
        return "redirect:/";
    }
}
