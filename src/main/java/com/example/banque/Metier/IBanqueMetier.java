package com.example.banque.Metier;

import com.example.banque.Model.Compte;
import com.example.banque.Model.Operation;

import java.util.List;

public interface IBanqueMetier {
    public Compte consulterCompte(String codeCpt);
    public void verser(String codeCpt, double montant);
    public void retirer(String codeCpt, double montant);
    public void virement(String codeCpt1, String codeCpt2, double montant);
    public List<Operation> listOperation(String codeCpt);
}
