package com.example.project_esalaf;
public class admin {
    private  String username;
    private  int password;

    public admin(String text, int text1) {
        this.username=text;
        this.password=text1;
    }
    public admin(){

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getPassword() {
        return password;
    }
    public void setPassword(int password) {
        this.password = password;
    }
}
