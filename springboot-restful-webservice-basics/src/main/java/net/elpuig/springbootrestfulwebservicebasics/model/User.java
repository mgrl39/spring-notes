package net.elpuig.springbootrestfulwebservicebasics.model;

// Una classe (record) User amb (id, email, fullName i password)

public record User(long id, String email,  String fullName, String password) {
}
