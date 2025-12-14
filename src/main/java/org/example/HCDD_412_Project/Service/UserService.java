package org.example.HCDD_412_Project.Service;

import org.example.HCDD_412_Project.Model.Game;
import org.example.HCDD_412_Project.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService
{
    List<User> getAllUsers();
    void saveUser(User user);
    User  getUserById(long id);
    void deleteUserById(long id);
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    void addGameToUser(Long userID, String gameTitle);
}