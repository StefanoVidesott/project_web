package exam_project.main_webapp.services;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public int deleteExpiredTrialUsers() {
        return userRepository.deleteExpiredTrialUsers();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        return userRepository.changePassword(username, oldPassword, newPassword);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void upgradeUser(String username, String newPlan) {
        userRepository.upgradeUser(username, newPlan);
    }
}
