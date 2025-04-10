package ma.kata.product_app.dataloader;

import jakarta.transaction.Transactional;
import ma.kata.product_app.model.User;
import ma.kata.product_app.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDataLoader {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDataLoader(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void loadData() {
        if (userRepository.count() == 0) {
            String defaultPassword = "12345678";
            String encodedPassword = passwordEncoder.encode(defaultPassword);

            User user1 = User.builder()
                    .username("admin")
                    .firstname("Admin")
                    .email("admin@admin.com")
                    .password(encodedPassword)
                    .build();

            User user2 = User.builder()
                    .username("user")
                    .firstname("user")
                    .email("user@user.com")
                    .password(encodedPassword)
                    .build();

            userRepository.saveAll(Arrays.asList(user1, user2));
        }
    }
}
