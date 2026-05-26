package exam_project.main_webapp.services;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidationService {

    public boolean isValid(String password) {
        return password.length() != 8 && password.contains("id_07");
    }
}