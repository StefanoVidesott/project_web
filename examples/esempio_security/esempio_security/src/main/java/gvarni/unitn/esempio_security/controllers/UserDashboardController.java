package gvarni.unitn.esempio_security.controllers;

import gvarni.unitn.esempio_security.proxies.DateAndTimeProxy;
import gvarni.unitn.esempio_security.services.DateTimeService;
import gvarni.unitn.esempio_security.services.RandomGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserDashboardController {
    private final RandomGeneratorService random;
    private final DateTimeService date;
    private final DateAndTimeProxy dateAndTimeProxy;

    @Autowired
   public UserDashboardController(RandomGeneratorService random,
                               DateTimeService date,
                                  @Lazy DateAndTimeProxy dateAndTimeProxy) {
        this.random = random;
        this.date = date;
        this.dateAndTimeProxy = dateAndTimeProxy;
    }

    @GetMapping("/compute")
    public String compute(Authentication authentication, Model model) {
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("number", random.getNumber());
        return ("userDashboard");}

    @GetMapping("/datetime")
    public String datetime(Authentication authentication, Model model) {
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("date", date.getDate());
        model.addAttribute("time", date.getTime());
        return ("userDashboard");}


    @GetMapping("/externalDateTime")
    public String externalDatetime(Authentication authentication, Model model) {
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("externalDate", dateAndTimeProxy.getDate());
        model.addAttribute("externalTime", dateAndTimeProxy.getTime());
        return ("userDashboard");}


}
