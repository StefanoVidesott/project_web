package exam_project.main_webapp.services;

import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckUserService {
    private final UserRepository userRepository;

    @Autowired
    public CheckUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userExists(String username) {
        return userRepository.userExists(username);
    }
}
