package org.example.HCDD_412_Project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class User implements Serializable
{
    // Attributes
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userID;                                      // user ID number - primary key
    private String emailAddress;                              // user's email address associated with account
    private String username;                                  // username, as assigned by user upon sign up
    private String password;                                  // user-set password, used for login and authentication
    private String firstName;                                 // first name of user
    private String lastName;                                  // last name of user
    private String gender;                                    // user gender - Male, Female, Non-Binary, or Prefer Not to Disclose
    private String dateOfBirth;                               // user date of birth
    private String guardianFirstName;                         // optional field -- req. for users that are under the age of 18 -- guardian's first name
    private String guardianLastName;                          // optional field -- req. for users that are under the age of 18 -- guardian's last name
    private String guardianEmailAddress;                      // optional field -- req. for users that are under the age of 18 -- guardian's email address
    private String postalCode;                                // user's postal code used for billing and calculating sales tax
    private String paymentMethod;                             // user's preferred method of payment
    @ElementCollection
    private List<String> accountRestrictions;                 // restrictions on content that can be viewed, based on ESRB rating or genre -- if empty, no restrictions


    // Support for Many-to-Many relationship between User objects and Game objects
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_games",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )

    private List<Game> games;

    // Zero-args constructor -- doesn't need to be manually initialized
    public User()
    {
    }

    // Constructor when all parameters are initialized
    public User(Long userID, String emailAddress, String username, String password, String firstName, String lastName, String gender, String dateOfBirth, String guardianFirstName, String guardianLastName,
                String guardianEmailAddress, String postalCode, String paymentMethod, List<String> accountRestrictions, List<Game> games) {
        this.userID = userID;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.guardianFirstName = guardianFirstName;
        this.guardianLastName = guardianLastName;
        this.guardianEmailAddress = guardianEmailAddress;
        this.postalCode = postalCode;
        this.paymentMethod = paymentMethod;
        this.accountRestrictions = accountRestrictions;
        this.games = games;
    }

    // Setters
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGuardianFirstName(String guardianFirstName) {
        this.guardianFirstName = guardianFirstName;
    }

    public void setGuardianLastName(String guardianLastName) {
        this.guardianLastName = guardianLastName;
    }

    public void setGuardianEmailAddress(String guardianEmailAddress) {
        this.guardianEmailAddress = guardianEmailAddress;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setAccountRestrictions(List<String> accountRestrictions) {
        this.accountRestrictions = accountRestrictions;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    // Getters
    public Long getUserID() {
        return userID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGuardianFirstName() {
        return guardianFirstName;
    }

    public String getGuardianLastName() {
        return guardianLastName;
    }

    public String getGuardianEmailAddress() {
        return guardianEmailAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public List<String> getAccountRestrictions() {
        return accountRestrictions;
    }

    public List<Game> getGames() {
        return games;
    }
}
