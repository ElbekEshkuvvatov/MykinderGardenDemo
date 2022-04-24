package com.best.kindergarden.repository;
import com.best.kindergarden.model.domain.Group;
import com.best.kindergarden.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Override
    boolean existsById(Integer integer);

//    @Override
//    @Query(value = "select * from Group g where g.status = 'ACTIVE'",
//    countQuery = "SELECT count(*) from Group g ")
//    Page<Group> findAll(Pageable pageable);

    //JPA query
    Optional<Group> findByIdAndStatus(Integer integer, Status status);
}
