package com.transaction.model;

public class User {

        private int customerId;
        private String name;
        private String username;
        private String email;
        private String mobile;
        private String password;
        private String role;

        public User() {}

        public User(String name, String username, String email,
                    String mobile, String password, String role) {
            this.name = name;
            this.username = username;
            this.email = email;
            this.mobile = mobile;
            this.password = password;
            this.role = role;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }