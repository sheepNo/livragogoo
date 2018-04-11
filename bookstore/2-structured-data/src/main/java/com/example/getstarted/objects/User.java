package com.example.getstarted.objects;

public class User {
    private Long id;
    private String userName;
    private String password;
    // public Long valid;

    public static final String ID = "id";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    // public static final String VALID = "valid";

    private User(Builder builder){
        this.id = builder.id;
        this.userName = builder.userName;
        this.password = builder.password;
        // this.valid = builder.valid;
    }

    public static class Builder {
        private Long id;
        private String userName;
        private String password;
        // public Long valid;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        // public Builder valid(Long valid) {
        //     this.valid = valid;
        //     return this;
        // }

        public User build() {
            return new User(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    // 
    // public Long getValid() {
    //     return valid;
    // }
    //
    // public void setValid(Long valid) {
    //     this.valid = valid;
    // }
}
