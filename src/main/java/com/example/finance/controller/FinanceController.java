package com.example.finance.controller;


import com.example.finance.model.Transaction;
import com.example.finance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/")
    public String dashboard(Model model){

        model.addAttribute("transaction" ,financeService.getAllTransaction());
        model.addAttribute("totalIncome",financeService.getTotalIncome());
        model.addAttribute("totalExpense",financeService.getTotalExpense());

        return "dashboard";

    }
    @GetMapping("/add")
    public String addTransactionForm(Model model){
        model.addAttribute("transaction",new Transaction());
        return "addTransaction";
    }

    @PostMapping("/save")
    public String saveTransaction(@ModelAttribute Transaction transaction){
        financeService.saveTransaction(transaction);
        return "redirect:/";
    }

}
