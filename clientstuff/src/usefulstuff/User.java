package usefulstuff;

import java.io.Serializable;

public class User implements Serializable {
    private static User instance;

    private String name;
    private String password;
    private Integer user_id;

    private User() {}

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserId(Integer key) {this.user_id = key;}

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserId() {
        return user_id;
    }
}
