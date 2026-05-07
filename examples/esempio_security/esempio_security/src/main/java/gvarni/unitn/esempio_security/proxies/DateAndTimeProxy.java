package gvarni.unitn.esempio_security.proxies;

import gvarni.unitn.esempio_security.configurations.OpenFeignConfig;
import gvarni.unitn.esempio_security.configurations.SecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "dateAndTime",
        url = "${name.service.url}",
        configuration = OpenFeignConfig.class)
public interface DateAndTimeProxy {
    @GetMapping("/date")
    public abstract String getDate();

    @GetMapping("/time")
    public abstract String getTime();
}

