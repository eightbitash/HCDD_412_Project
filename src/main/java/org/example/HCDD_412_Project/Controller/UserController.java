package org.example.HCDD_412_Project.Controller;

import org.example.HCDD_412_Project.Model.Game;
import org.example.HCDD_412_Project.Repository.GameRepository;
import org.example.HCDD_412_Project.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.GetMapping;

import org.example.HCDD_412_Project.Model.User;
import org.example.HCDD_412_Project.Service.UserService;
import org.example.HCDD_412_Project.Service.GameService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    // Repositories
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;


        // display list of users
        @GetMapping("/user_list")
        public String viewHomePage(Model model)
        {
            model.addAttribute("listUsers", userService.getAllUsers());

            return "user_list";

        }

        @GetMapping("/showNewUserForm")
        public String showNewUserForm(Model model)
        {
            // create model attribute to bind form data
            User user = new User();
            model.addAttribute("user", user);
            return "new_user";
        }


        @PostMapping("/saveUser")
        public String saveUser(@ModelAttribute("user") User user){

            // save user to database
            userService.saveUser(user);
            return "redirect:/users/user_list";

        }

        @PostMapping("/addGameToLibrary")
        public String addGameToLibrary(
                @RequestParam("userID") Long userID,
                @RequestParam("gameTitle") String gameTitle)
        {
            userService.addGameToUser(userID, gameTitle);

            return "redirect:/users/user_list";
        }


        @GetMapping("/showFormForUpdate/{id}")
        public String showFormForUpdate(@PathVariable (value = "id") long id, Model model)
        {
            // get the user from the service
            User user = userService.getUserById(id);

            // retrieve games in catalog
            List<Game> allGames = gameService.getAllGames();

            // set the user and catalog games as a model attribute to pre-populate the form
            model.addAttribute("user", user);
            model.addAttribute("allGames", allGames);
            return "update_user";
        }

        @GetMapping("/deleteUser/{id}")
        public String deleteUser(@PathVariable (value = "id") long id)
        {
            // call delete user method
            this.userService.deleteUserById(id);
            return "redirect:/users/user_list";

        }

        @GetMapping("/page/{pageNo}")
        public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model)
        {
            int pageSize = 5;

            Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
            List<User> listUsers = page.getContent();

            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());

            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

            model.addAttribute("listUsers", listUsers);

            return "user_list";
        }


}
