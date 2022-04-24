package com.best.kindergarden.service.impl;
import com.best.kindergarden.model.domain.User;
import com.best.kindergarden.model.dto.UserDTO;
import com.best.kindergarden.model.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryServiceImpl {

    @Autowired
    private EntityManager entityManager;

    public PageImpl<UserDTO> findAll(Status status, String firstname, String lastname, Integer page, Integer size){
        Map<String, Object> params = new HashMap<>();
        StringBuilder queries = new StringBuilder(" select u from User u ");
        StringBuilder conditions = new StringBuilder("");

        if (Objects.nonNull(status)){
            if (conditions.length()>0){
                conditions.append(" and u.status = :status");
            }else {
                conditions.append(" where u.status = :status ");
            }
            params.put("status", status);
        }

        if (Objects.nonNull(firstname)){
            if (conditions.length() > 0){
                conditions.append(" and u.firstname = :firstname");
            }else {
                conditions.append(" where u.firstname = :firstname");
            }
            params.put("name", firstname);
        }

        if (Objects.nonNull(lastname)){
            if (conditions.length()>0){
                conditions.append(" and u.lastname = :lastname");
            }else {
                conditions.append(" where u.lastname =:lastname ");
            }
            params.put("lastname", lastname);
        }

        Query query = entityManager.createQuery(queries.append(conditions).toString());
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            query.setParameter(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }

        List<User> userList = query.getResultList();
        List<UserDTO>  userDTOList = userList
                .stream()
                .map(User::map2DTO)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<>(userDTOList, pageable, countQuery(conditions, params));


    }
    private long countQuery( StringBuilder conditions, Map<String, Object> params){
        StringBuilder countQuery = new StringBuilder("select count(*)  from User u ");
        Query query = entityManager.createQuery(countQuery.append(conditions).toString());
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            query.setParameter(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }
        return (Long) query.getSingleResult();
    }

}
