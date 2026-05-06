package com.duoc.backend;

import org.springframework.data.repository.CrudRepository;

import com.duoc.backend.User;

// Este código será CREADO AUTOMATICAMENTE por Spring en un Bean llamado userRepository
// CRUD significa Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

}
