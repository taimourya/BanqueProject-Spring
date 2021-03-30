package com.example.banque.Metier;

import com.example.banque.Dao.CompteRepository;
import com.example.banque.Dao.OperationRepository;
import com.example.banque.Model.Compte;
import com.example.banque.Model.Operation;
import com.example.banque.Model.Retrait;
import com.example.banque.Model.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BanqueMetierDB implements IBanqueMetier{

    private CompteRepository compteRepository;
    private OperationRepository operationRepository;

    @Autowired
    public BanqueMetierDB(CompteRepository compteRepository, OperationRepository operationRepository) {
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Compte consulterCompte(String codeCpt) {
        Optional<Compte> cp = compteRepository.findById(codeCpt);
        if(cp.isEmpty())
            throw new RuntimeException("compte introuvable");
        return cp.get();
    }

    @Override
    public void verser(String codeCpt, double montant) {
        if(montant <= 0)
            throw new RuntimeException("le montant doit etre positif");
        Compte cp = consulterCompte(codeCpt);

        Versement versement = new Versement(new Date(), montant, cp);
        operationRepository.save(versement);

        cp.setSolde(cp.getSolde() + montant);
        compteRepository.save(cp);
    }

    @Override
    public void retirer(String codeCpt, double montant) {
        if(montant <= 0)
            throw new RuntimeException("le montant doit etre positif");
        Compte cp = consulterCompte(codeCpt);
        if(cp.getSolde()  < montant)
            throw new RuntimeException("solde insufisant");
        Retrait retrait = new Retrait(new Date(), montant,cp);
        operationRepository.save(retrait);
        cp.setSolde(cp.getSolde() - montant);
        compteRepository.save(cp);
    }

    @Override
    public void virement(String codeCpt1, String codeCpt2, double montant) {
        if(codeCpt1.equals(codeCpt2))
            throw new RuntimeException("vous ne pouvez pas effectuer un virement sur le meme compte");
        retirer(codeCpt1, montant);
        verser(codeCpt2, montant);
    }

    @Override
    public List<Operation> listOperation(String codeCpt) {
        Compte cp = consulterCompte(codeCpt);
        return (List<Operation>)cp.getOperations();
    }
}
