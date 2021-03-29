package com.example.banque.Controller;

import com.example.banque.Metier.IBanqueMetier;
import com.example.banque.Model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BanqueController {
    @Autowired
    IBanqueMetier banqueMetier;

    @GetMapping("/")
    public String consulterCompte(Model model, String codeCompte){
        System.out.println("code : " + codeCompte);
        model.addAttribute("codeCompte", codeCompte);
        try {
            Compte cp = banqueMetier.consulterCompte(codeCompte);
            model.addAttribute("compte", cp);
            model.addAttribute("operations", banqueMetier.listOperation(codeCompte));

        } catch (Exception e) {
            model.addAttribute("exception", e);
        }



        return "compte";
    }
}
