package com.cognizant.jwt.repo;

import com.cognizant.jwt.mdoel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public UserEntity findByUsername(String username);
}
