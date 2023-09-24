package com.example.taskmanagement.test;

import com.example.taskmanagement.entity.Role;
import com.example.taskmanagement.entity.User;
import com.example.taskmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(
        connection = EmbeddedDatabaseConnection.H2
)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser(){

        User user = User.builder()
                .userName("Vasu Yadav")
                .userEmail("vasu7yadav@gmail.com")
                .userPassword("123@")
                .build();

        User saveUser = userRepository.save(user);

        assertThat(saveUser).isNotNull();
        assertThat(saveUser.getUserId()).isGreaterThan(0);
    }

    @Test
    public void getUserById(){

        User user = User.builder()
                .userName("Vasu Yadav")
                .userEmail("vasu7yadav@gmail.com")
                .userPassword("123@")
                .build();

        userRepository.save(user);

        User userResponse = userRepository.findById(user.getUserId()).get();

        assertThat(userResponse).isNotNull();

    }

    @Test
    public void getUserByUserEmail(){

        User user = User.builder()
                .userName("Vasu Yadav")
                .userEmail("vasu7yadav@gmail.com")
                .userPassword("123@")
                .build();

        userRepository.save(user);

        User userResponse = userRepository.findUserByUserEmail(user.getUserEmail()).get();

        assertThat(userResponse).isNotNull();
    }

    @Test
    public void updateUserDetails(){

        User user = User.builder()
                .userName("Vasu Yadav")
                .userEmail("vasu7yadav@gmail.com")
                .userPassword("123@")
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        User userSave = userRepository.findById(user.getUserId()).get();
        userSave.setUserEmail("mali@gmail.com");
        userSave.setUserName("Malik");

        User saveUser = userRepository.save(user);

        assertThat(saveUser.getUsername()).isNotNull();
        assertThat(saveUser.getUserEmail()).isNotNull();

    }

    @Test
    public void deleteUser(){

        User user = User.builder()
                .userName("Vasu Yadav")
                .userEmail("vasu7yadav@gmail.com")
                .userPassword("123@")
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        userRepository.deleteById(user.getUserId());
        Optional<User> userReturn = userRepository.findById(user.getUserId());

        assertThat(userReturn).isEmpty();
    }

}
