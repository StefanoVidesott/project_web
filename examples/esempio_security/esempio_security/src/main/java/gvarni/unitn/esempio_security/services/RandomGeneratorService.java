package gvarni.unitn.esempio_security.services;

import org.springframework.stereotype.Service;

@Service
public class RandomGeneratorService {
    final int max = 100;
    final int min = 1;

    public int getNumber() {
        int number = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return number;
    }
}
