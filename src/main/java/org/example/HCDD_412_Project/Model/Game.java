package org.example.HCDD_412_Project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Game implements Serializable
{
    // Attributes
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long gameID;                                        // game ID -- primary key
    private String title;                                       // title or name of game
    private String developer;                                   // developer of game
    private String publisher;                                   // publisher of game
    private String rating;                                      // ESRB rating of game
    private String releaseDate;                                 // date of release
    private String description;                                 // description of game as provided by developer or publisher
    @ElementCollection
    private List<String> genres;                                // genre or genres of game

    // Zero-args constructor -- doesn't need to be manually initialized
    public Game()
    {
    }

    // Constructor when all params are initialized
    public Game(Long gameID, String title, String developer, String publisher, String rating, String releaseDate, String description, List<String> genres) {
        this.gameID = gameID;
        this.title = title;
        this.developer = developer;
        this.publisher = publisher;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.description = description;
        this.genres = genres;
    }

    // Setters
    public void setGameID(Long gameID) {
        this.gameID = gameID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    // Getters
    public Long getGameID() {
        return gameID;
    }

    public String getTitle() {
        return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getRating() {
        return rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getGenres() {
        return genres;
    }
}
