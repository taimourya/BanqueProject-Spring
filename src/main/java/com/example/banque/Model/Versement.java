package com.example.banque.Model;

import javax.management.DescriptorKey;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation{

    public Versement() {
    }

    public Versement(Date dateOperation, double montant, Compte compte) {
        super(dateOperation, montant, compte);
    }

}
