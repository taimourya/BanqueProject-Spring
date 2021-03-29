package com.example.banque.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{
    private double decouvert;

    public CompteCourant() {
    }

    public CompteCourant(String code, double solde, Date date, Client client, double decouvert) {
        super(code, solde, date, client);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
