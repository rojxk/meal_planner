package model;

/**
 * Represents user data within the application, storing both an identifier and a username.
 * This class is typically used to manage user-specific settings or information,
 * such as associating meals in a MealDictionary with a particular user.
 */
public class Userdata {
    private int idUser; // Unique identifier for the user.
    private String userName; // Username of the user.

    /**
     * Constructs a new Userdata object with a specified user ID and username.
     *
     * @param idUser The unique identifier for the user.
     * @param userName The username of the user.
     */
    public Userdata(int idUser, String userName) {
        this.idUser = idUser;
        this.userName = userName;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username of this user.
     */
    public String getUsername() {
        return userName;
    }

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return The user ID.
     */
    public int getIdUser() {
        return idUser;
    }

}
