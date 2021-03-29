package com.example.banque;

import com.example.banque.Dao.ClientRepository;
import com.example.banque.Dao.CompteRepository;
import com.example.banque.Dao.OperationRepository;
import com.example.banque.Model.Client;
import com.example.banque.Model.Compte;
import com.example.banque.Model.CompteCourant;
import com.example.banque.Model.CompteEpargne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BanqueApplication implements CommandLineRunner {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(BanqueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Client client = new Client("taimourya");
        clientRepository.save(client);

        compteRepository.save(new CompteCourant("cc1", 1200, new Date(), client, 1.6));
        compteRepository.save(new CompteCourant("cc2", 1500, new Date(), client, 1.6));
        compteRepository.save(new CompteEpargne("ce1", 1500, new Date(), client, 1.6));

        compteRepository.findAll().forEach(compte -> System.out.println("compte : " + compte.getCode()));
    }
}
