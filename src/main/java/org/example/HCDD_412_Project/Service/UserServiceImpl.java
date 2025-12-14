package org.example.HCDD_412_Project.Service;

import org.example.HCDD_412_Project.Model.Game;
import org.example.HCDD_412_Project.Model.User;
import org.example.HCDD_412_Project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.example.HCDD_412_Project.Repository.GameRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, GameRepository gameRepository,
                           org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
                                   passwordEncoder) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user)
    {
        // If a userID does not exist for this user yet... -- Used for CREATE
        if (user.getUserID() == null)
        {
            // if there is a record already in the database with the username that was entered
            if (userRepository.findByUsername(user.getUsername()) != null)
            {
                throw new RuntimeException("Error: Username is taken. Please try again.");
            }
            // otherwise...
            else
            {
                // get the password, encode it, and assign to a String variable named encodedPassword
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                // set the value of the user object's password field to the value of encodedPassword
                user.setPassword(encodedPassword);
            }

        }
        // Otherwise, we are updating an existing user... -- Used for UPDATE
        else {
            // using the userID, find the user's record in the database and assign to foundUser,
            // otherwise set foundUser to null
            User foundUser = userRepository.findById(user.getUserID()).orElse(null);

            // If foundUser is not null, and the password is not being updated this time...
            if (foundUser != null && (user.getPassword() == null || user.getPassword().isEmpty()))
            {
                // get the user's current password and set it as the password to be sent
                // to the database when the record is updated
                user.setPassword(foundUser.getPassword());
            }
            else
            {
                // debug message -- should print to console if password is going to be changed with the account update
                System.out.println("User password will be changed in database.");
            }
        }

        this.userRepository.save(user);
    }

    @Override
    public User getUserById(long id)
    {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent())
        {
            user = optional.get();
        }
        else
        {
            throw new RuntimeException(" User not found for id :: " + id);
        }

        return user;
    }

    @Override
    public void deleteUserById(long id)
    {
        this.userRepository.deleteById(id);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection)
    {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }

    @Override
    public void addGameToUser(Long userID, String gameTitle)
    {
        User user = getUserById(userID);

        Game game = gameRepository.findByTitle(gameTitle);

        if (user.getGames() == null)
        {
            user.setGames(new java.util.ArrayList<>());
            user.getGames().add(game);
        }
        else
        {
            user.getGames().add(game);
        }

        userRepository.save(user);
    }
}
