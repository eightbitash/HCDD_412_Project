package org.example.HCDD_412_Project.Service;

import org.example.HCDD_412_Project.Model.Game;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

public interface GameService
{
    List<Game> getAllGames();
    void saveGame(Game game);
    Game getGameById(long id);
    void deleteGameById(long id);
    Page<Game> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
