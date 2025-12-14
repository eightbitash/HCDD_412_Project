package org.example.HCDD_412_Project.Repository;

import org.example.HCDD_412_Project.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>
{
    @Override
    Optional<Game> findById(Long id);
    Game findByTitle(String title);
}
