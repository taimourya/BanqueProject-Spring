package com.example.banque.Model;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public class Compte {
    @Id
    private String code;
    private double solde;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "CODE_CLI")
    private Client client;

    @OneToMany(mappedBy = "compte", fetch = FetchType.LAZY)
    private Collection<Operation> operations;


    public Compte(double solde, Date date) {
        this.solde = solde;
        this.date = date;
    }

    public Compte() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Collection<Operation> operations) {
        this.operations = operations;
    }
}
