package org.example.HCDD_412_Project.Service;

import org.example.HCDD_412_Project.Model.User;
import org.example.HCDD_412_Project.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest
{
    @Autowired
    private UserRepository repository;

    @Test
    void getAllUsers()
    {
        List<User> items = repository.findAll();
        assertEquals(5, items.size());
    }

    @Test
    void testFindOneUser()
    {
        User user = repository.findById(1L).get();
        assertEquals("ash.n.stack@gmail.com", user.getEmailAddress());
    }

}
