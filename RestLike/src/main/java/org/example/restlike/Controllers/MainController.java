package org.example.restlike.Controllers;

import org.example.restlike.Pojos.Composizione;
import org.example.restlike.Pojos.Esercizio;
import org.example.restlike.Pojos.Programma;
import org.example.restlike.Repository.ProgrammiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final ProgrammiRepository programmiRepository;

    @Autowired
    public MainController(ProgrammiRepository programmiRepository){this.programmiRepository = programmiRepository;}

    @GetMapping("/programmiDEF")
    public List<Programma> getProgrammi(){return programmiRepository.getProgrammi(); }

    @GetMapping("/esercizi")
    public List<Composizione> getEsercizi(@RequestParam String code){return programmiRepository.getEsercizi(code); }

    @PostMapping("/kcal")
    public double getKcal(@RequestBody List<Composizione> composizioni){

        System.out.println("---- DEBUG RICEZIONE DATI ----");
        System.out.println("Numero esercizi ricevuti: " + composizioni.size());
        if(!composizioni.isEmpty()) {
            System.out.println("Nome primo esercizio: " + composizioni.get(0).getNome_esercizio());
        }
        System.out.println("------------------------------");

        return programmiRepository.getKcal(composizioni); }

}
