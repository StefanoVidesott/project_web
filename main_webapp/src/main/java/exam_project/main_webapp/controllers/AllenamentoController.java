package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.EserciziDTO;
import exam_project.main_webapp.proxies.ProgrammiProxy;
import exam_project.main_webapp.pojos.Composizione;
import exam_project.main_webapp.pojos.ComposizioneCustom;
import exam_project.main_webapp.pojos.Programma;
import exam_project.main_webapp.repositories.AllenamentoRepository;
import exam_project.main_webapp.repositories.UserRepository;
import exam_project.main_webapp.services.TrainingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    public String allenamenti(Authentication authentication){
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER_PRO"))) {
            return "forward:allenamentiPro";
        } else return "forward:allenamentiStandard";
    }

    @GetMapping("/allenamentiPro")
    public String getAllenamentiPro(Model model,Authentication authentication){
        List<Programma> programmi = this.programmiProxy.getProgrammi();
        this.allenamentoRepository.addKcalPredefinit(programmi);
        List<Programma> programmiCustom = this.allenamentoRepository.getProgrammiCustom(authentication.getName());
        model.addAttribute("allenamenti",programmi);
        model.addAttribute("allenamentiCustom",programmiCustom);
        return "allenamentiPro";
    }

    @GetMapping("/allenamentiStandard")
    public String getAllenamentiStandard(Model model){
        List<Programma> programmi = this.programmiProxy.getProgrammi();
        this.allenamentoRepository.addKcalPredefinit(programmi);
        model.addAttribute("allenamenti",programmi);
        return "allenamentiStandard";
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
    public String allenamentoCustom(Model model){
        List<String> nomi = this.programmiProxy.getSoloNomi();
        model.addAttribute("nomiEsercizi",nomi);
        return "allenamentoCustom";
    }

 /*   @PostMapping("/completaAllenamento")
    public String completaAllenamento(Authentication authentication,@RequestParam int code){
        this.trainin.registerTraining(authentication.getName(),code);
        if(this.trainingRepository.countTrainingsByUsername(authentication.getName()) == 3){
            return "logout";
        }
        return "dashboard";
    }*/

    @PostMapping("/allenamentoCustomSend")
    public String allenamentoCustomSend(Authentication authentication,String nomeProgramma, EserciziDTO esercizi,Model model){
        List<Composizione> es = esercizi.getEsercizi();
        boolean result = allenamentoRepository.addAllenamento(authentication.getName(),nomeProgramma,es);
        if(!result){model.addAttribute("error","Programma con lo stesso nome");}
        return "allenamentoCustomSend";
    }

    @PostMapping("/completato")
    public String completato(@RequestParam int trainingId, @RequestParam boolean isDefault, Authentication authentication) {
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
