package com.example.moviebay.repositories;

import com.example.moviebay.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
