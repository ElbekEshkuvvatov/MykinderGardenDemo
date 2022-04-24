package com.best.kindergarden.service.impl;
import com.best.kindergarden.model.domain.Group;
import com.best.kindergarden.model.dto.GroupDTO;
import com.best.kindergarden.model.enums.Status;
import com.best.kindergarden.service.GroupRepositoryService;
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
public class GroupRepositoryServiceImpl implements GroupRepositoryService {

    @Autowired
    private EntityManager entityManager;


    public PageImpl<GroupDTO> findAll(Status status, String name, Integer maxChild, Integer page, Integer size) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder queries = new StringBuilder("select g FROM Group g ");
        StringBuilder conditions = new StringBuilder("");

        if (Objects.nonNull(status)) {
            if (conditions.length() > 0) {
                conditions.append(" AND g.status  = :status");
            } else {
                conditions.append(" where g.status = :status");
            }
            params.put("status", status);

        }
        if (Objects.nonNull(name)) {
            if (conditions.length() > 0) {
                conditions.append(" and g.name = :name");
            } else {
                conditions.append(" where g.name = :name");
            }
            params.put("name", name);
        }

        if (Objects.nonNull(maxChild)) {
            if (conditions.length() > 0) {
                conditions.append(" and g.maxChild = :maxChild");
            } else {
                conditions.append(" where g.maxChild = :maxChild");
            }
            params.put("maxChild", maxChild);
        }

        Query query = entityManager.createQuery(queries.append(conditions).toString());

        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            query.setParameter(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }

        query.setFirstResult(page);   // bu yo'l orqali biza ba'zadan faqat biz berib yuborgan parametrlar buyicha qiymat oldik
        query.setMaxResults(size);

        List<Group> resultList = query.getResultList();
        List<GroupDTO> groupDTOList = resultList
                .stream()
                .map(Group::map2DTO)
                .collect(Collectors.toList());
        Pageable pageable = PageRequest.of(page, size);

        return new PageImpl<>(groupDTOList, pageable, countQuery(conditions, params));

    }

    private long countQuery(StringBuilder conditions, Map<String, Object> params) {
        StringBuilder countQuery = new StringBuilder("select count(*)  from Group g ");
        Query query = entityManager.createQuery(countQuery.append(conditions).toString());
        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            query.setParameter(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }
        return (Long) query.getSingleResult();
    }
}
