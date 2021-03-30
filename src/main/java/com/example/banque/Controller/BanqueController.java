package com.example.banque.Controller;

import com.example.banque.Metier.IBanqueMetier;
import com.example.banque.Model.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    @PostMapping("/saveOperation")
    public String saveOperation(Model model, String codeCompte, String typeOperation, String codeCompte2, double montant) {
        try {
            if(typeOperation.equals("VERS")) {
                banqueMetier.verser(codeCompte, montant);
            }
            else if(typeOperation.equals("RETR")) {
                banqueMetier.retirer(codeCompte, montant);
            }
            else {
                banqueMetier.virement(codeCompte, codeCompte2, montant);
            }
        } catch (Exception e) {
            model.addAttribute("errorOperation", e);
        }


        return "redirect:/?codeCompte="+codeCompte;
    }
}
