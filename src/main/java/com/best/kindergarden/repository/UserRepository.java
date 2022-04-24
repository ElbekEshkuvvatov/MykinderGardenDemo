package com.best.kindergarden.repository;

import com.best.kindergarden.model.domain.User;
import com.best.kindergarden.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    boolean existsById(Integer integer);


    Optional<User> findByIdAndStatus(Integer integer, Status status);


}
