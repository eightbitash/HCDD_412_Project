package org.example.HCDD_412_Project.Repository;

import org.example.HCDD_412_Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @Override
    Optional<User> findById(Long id);

    User findByUsername(String username);
}
