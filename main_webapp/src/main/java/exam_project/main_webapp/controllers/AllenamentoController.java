package exam_project.main_webapp.controllers;

import exam_project.main_webapp.EserciziDTO;
import exam_project.main_webapp.Proxy.ProgrammiProxy;
import exam_project.main_webapp.pojos.Composizione;
import exam_project.main_webapp.pojos.ComposizioneCustom;
import exam_project.main_webapp.pojos.Programma;
import exam_project.main_webapp.repositories.AllenamentoRepository;
import exam_project.main_webapp.repositories.UserRepository;
import exam_project.main_webapp.services.TrainingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AllenamentoController {
    private final ProgrammiProxy programmiProxy;
    private final AllenamentoRepository allenamentoRepository;
    private final UserRepository userRepository;
    private final TrainingService trainingService;

    public AllenamentoController(ProgrammiProxy programmiProxy,AllenamentoRepository allenamentoRepository, UserRepository userRepository, TrainingService trainingService){
        this.programmiProxy = programmiProxy;
        this.allenamentoRepository = allenamentoRepository;
        this.userRepository = userRepository;
        this.trainingService = trainingService;
    }

    @GetMapping("/allenamenti")
    public String allenamenti(Model model){
        List<Programma> programmi = this.programmiProxy.getProgrammi();
        List<Programma> programmiCustom = this.allenamentoRepository.getProgrammiCustom();
        model.addAttribute("allenamenti",programmi);
        model.addAttribute("allenamentiCustom",programmiCustom);
        return "allenamento";
    }

    @GetMapping("/composizione")
    public String composizione(@RequestParam String code, Model model){
        List<Composizione> esercizi = this.programmiProxy.getEsercizi(code);
        model.addAttribute("composizione",esercizi);
        return "composizione";
    }

    @GetMapping("/composizioneCustom")
    public String composizione(@RequestParam int id, Model model){
        List<ComposizioneCustom> esercizi = this.allenamentoRepository.getEserciziCustom(id);
        model.addAttribute("composizioneCustom",esercizi);
        return "composizioneCustom";
    }

    @GetMapping("/allenamentoCustom")
    public String allenamentoCustom(){
        return "allenamentoCustom";
    }

    @PostMapping("/allenamentoCustomSend")
    public String allenamentoCustomSend(Authentication authentication, String nomeProgramma, EserciziDTO esercizi, Model model){
       // Programma pC = new Programma();
       // pC.setNome(nomeProgramma);
        String username = authentication.getName();
        List<Composizione> es = esercizi.getEsercizi();
        allenamentoRepository.addAllenamento(username, nomeProgramma,es);

        return "allenamentoCustomSend";
    }

    @PostMapping("/completato")
    public String completato(@RequestParam int trainingId, @RequestParam boolean isDefault, Authentication authentication, Model model) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        trainingService.incrementTrainingCounter(username, trainingId, isDefault);

        if (role.equals("ROLE_USER_PROVA")) {
            int totale = userRepository.getDefaultTrainingsCount(username);
            if (totale >= 3) {
                userRepository.disableUser(username);
                return "redirect:/perform_logout";
            }
        }

        return "redirect:/dashboard";
    }
}
