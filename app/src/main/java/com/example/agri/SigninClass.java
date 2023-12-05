package com.example.agri;

public class SigninClass {String email, password, confirmpassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public SigninClass(String email, String password, String confirmpassword) {
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public SigninClass() {
    }
}
