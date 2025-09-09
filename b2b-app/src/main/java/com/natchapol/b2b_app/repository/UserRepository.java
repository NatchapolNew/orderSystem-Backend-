package com.natchapol.b2b_app.repository;

import com.natchapol.b2b_app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
