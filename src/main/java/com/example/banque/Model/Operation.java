package com.example.banque.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP", length = 2)
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero;
    private Date dateOperation;
    private double montant;

    @ManyToOne
    @JoinColumn(name = "CODE_CPTE")
    private Compte compte;

    public Operation() {
    }

    public Operation(Date dateOperation, double montant, Compte compte) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte = compte;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
