package com.Finance.FinApp.controller;

import com.Finance.FinApp.models.credit.Credit;
import com.Finance.FinApp.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/update")
    @ResponseBody
    public Credit showUpdate(Long id) {
        return creditService.getCreditById(id);
    }

    @RequestMapping(value = "/updated", method = {RequestMethod.PUT, RequestMethod.GET})
    public String creditUpdate(Credit credit) {
        creditService.updateCredit(credit);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCredit(@PathVariable(value = "id") Long id) {
        creditService.deleteCredit(id);
        return "redirect:/";
    }
}