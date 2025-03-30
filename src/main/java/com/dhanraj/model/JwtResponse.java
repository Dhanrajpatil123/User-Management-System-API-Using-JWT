package com.dhanraj.model;

public class JwtResponse {
    private String jwtToken;
    private String user;

    // Private constructor
    private JwtResponse(Builder builder) {
        this.jwtToken = builder.jwtToken;
        this.user = builder.user;
    }

    // Getters
    public String getJwtToken() {
        return jwtToken;
    }

    public String getUser() {
        return user;
    }

    // Static Builder Class
    public static class Builder {
        private String jwtToken;
        private String user;

        public Builder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(this);
        }
    }
}
