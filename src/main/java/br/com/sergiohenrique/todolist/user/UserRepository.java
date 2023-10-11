package br.com.sergiohenrique.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<UserModel, UUID> {
    List<UserModel> findByUsername(String username);
}
