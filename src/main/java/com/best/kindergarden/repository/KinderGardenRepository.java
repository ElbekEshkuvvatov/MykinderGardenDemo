package com.best.kindergarden.repository;

import com.best.kindergarden.model.domain.KinderGarden;
import com.best.kindergarden.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KinderGardenRepository extends JpaRepository<KinderGarden, Integer> {

    @Override
    boolean existsById(Integer integer);

    Optional<KinderGarden> findByIdAndStatus(Integer integer, Status status);

}
