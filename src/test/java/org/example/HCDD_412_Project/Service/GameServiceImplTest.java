package org.example.HCDD_412_Project.Service;

import org.example.HCDD_412_Project.Model.Game;
import org.example.HCDD_412_Project.Repository.GameRepository;
import org.example.HCDD_412_Project.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameServiceImplTest
{
    @Autowired
    private GameRepository repository;

    @Test
    public void getAllGames()
    {
        List<Game> items = repository.findAll();
        assertEquals(5, items.size());
    }

    @Test
    public void testFindOneGame()
    {
        Game game = repository.findById(1L).get();
        assertEquals("Katamari Damacy", game.getTitle());
    }

}
