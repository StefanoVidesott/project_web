package gvarni.unitn.esempio_security.services;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateTimeService {

    public String getDate (){
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd/MM/yyy");
        LocalDateTime now = LocalDateTime.now();
        return day.format(now);}

    public String getTime(){
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return time.format(now);}

}


