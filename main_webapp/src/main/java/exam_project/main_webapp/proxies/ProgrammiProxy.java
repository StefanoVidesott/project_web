package exam_project.main_webapp.proxies;

import exam_project.main_webapp.pojos.Composizione;
import exam_project.main_webapp.pojos.Programma;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Allenamenti", url = "${name.service.url}")
public interface ProgrammiProxy {

    @GetMapping("/programmiDEF")
    public List<Programma> getProgrammi();

    @GetMapping("/esercizi")
    public List<Composizione> getEsercizi(@RequestParam String code);

    @PostMapping("/kcal")
    public double getKcal(@RequestBody List<Composizione> esercizi);

    // Endpoint aggiunta per facilitare il menu a tendina degli esercizi
    @GetMapping("/soloNomi")
    public List<String> getSoloNomi();
}
