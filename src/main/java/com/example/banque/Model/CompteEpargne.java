package com.example.banque.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{
    private double taux;

    public CompteEpargne() {
    }

    public CompteEpargne(String code, double solde, Date date, Client client, double taux) {
        super(code, solde, date, client);
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }
}
