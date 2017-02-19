package com.example.tyk.testyourknowledge;

/**
 * Created by Axa on 29/01/2017.
 */

public class UserCloud {
    private String id;
    private String firstname;
    private String lastname;
    private String login;
    private String password;

    public UserCloud(String firstname, String lastname, String login, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    // getters
    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    //setters

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login=" + login +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) return false;
        UserCloud user = (UserCloud) o;

        if(user.login == null || user.password == null) return false;
        if(login.equals(user.login)){
            if (!password.equals(user.password)) return false;
            return true;
        }
        else return false;

    }

}
