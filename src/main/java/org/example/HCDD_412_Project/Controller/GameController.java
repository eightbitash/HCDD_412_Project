package org.example.HCDD_412_Project.Controller;

import org.example.HCDD_412_Project.Model.User;
import org.example.HCDD_412_Project.Repository.GameRepository;
import org.example.HCDD_412_Project.Repository.UserRepository;
import org.example.HCDD_412_Project.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.GetMapping;

import org.example.HCDD_412_Project.Model.Game;
import org.example.HCDD_412_Project.Service.GameService;


import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController
{
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    // Repositories
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    // Services
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    // display list of games
    @GetMapping("/game_list")
    public String viewHomePage(Model model)
    {
        model.addAttribute("listGames", gameService.getAllGames());

        return "game_list";

    }

    @GetMapping("/showNewGameForm")
    public String showNewGameForm(Model model)
    {
        // create model attribute to bind form data
        Game game = new Game();
        model.addAttribute("game", game);
        return "new_game";

    }

    @PostMapping("/saveGame")
    public String saveGame(@ModelAttribute("game") Game game)
    {
        // save game to database
        gameService.saveGame(game);
        return "redirect:/games/game_list";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id" ) long id, Model model)
    {

        // get game from the service
        Game game = gameService.getGameById(id);

        // set game as a model attribute to pre-populate the form
        model.addAttribute("game", game);
        return "update_game";

    }

    @GetMapping("/deleteGame/{id}")
    public String deleteGame(@PathVariable (value = "id") long id)
    {
        // call delete game method
        this.gameService.deleteGameById(id);
        return "redirect:/games/game_list";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model)
    {
        int pageSize = 5;

        Page<Game> page = gameService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Game> listGames = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir. equals("asc") ? "desc" : "asc");

        model.addAttribute("listGames", listGames);

        return "game_list";
    }

    @GetMapping("/select_game/{userId}")
    public String selectGame(@PathVariable (value = "userId") long userId, Model model)
    {

        model.addAttribute("listGames", gameService.getAllGames());

        User user = userService.getUserById(userId);
        model.addAttribute("user", user);

        return "select_game";
    }
}