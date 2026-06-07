package exam_project.main_webapp.proxies;

import exam_project.main_webapp.pojos.Composition;
import exam_project.main_webapp.pojos.Training;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "Trainings", url = "${name.service.url}")
public interface TrainingsProxy {

    @GetMapping("/trainings")
    List<Training> getDefaultTrainings();

    @GetMapping("/exercises")
    List<Composition> getExercisesByTrainingName(@RequestParam("name") String name);

    @PostMapping("/kcal")
    double getKcal(@RequestBody List<Composition> exercises);

    @GetMapping("/exercise-names")
    List<String> getExerciseNames();
}
