package com.best.kindergarden.service.impl;
import com.best.kindergarden.model.domain.KinderGarden;
import com.best.kindergarden.model.dto.KinderGardenDTO;
import com.best.kindergarden.model.enums.Status;
import com.best.kindergarden.service.KinderGardenRepositoryService;
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
public class KinderGardenRepositoryServiceImpl implements KinderGardenRepositoryService {
    @Autowired
    private EntityManager entityManager;

    public PageImpl<KinderGardenDTO> findAll(Status status, String name, Integer page,Integer size){
        Map<String, Object> params = new HashMap<>();
        StringBuilder queries = new StringBuilder(" select k from KinderGarden k ");
        StringBuilder conditions = new StringBuilder("");

        if (Objects.nonNull(status)) {
            if (conditions.length() > 0) {
                conditions.append(" and k.status = :status ");
            } else {
                conditions.append(" where g.status = :status");
            }
            params.put("status", status);
        }
        if (Objects.nonNull(name)){
            if (conditions.length() > 0){
                conditions.append(" and k.name =: name");
            }else {
                conditions.append(" where k.name  = :name");
            }
        }

        Query query = entityManager.createQuery(queries.append(conditions).toString());

        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            query.setParameter(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }

        query.setFirstResult(page);
        query.setMaxResults(size);

        List<KinderGarden> resultList = query.getResultList();
        List<KinderGardenDTO> kinderGardenDTOList = resultList
                .stream()
                .map(KinderGarden::map2DTO)
                .collect(Collectors.toList());
        Pageable pageable = PageRequest.of(page, size);


        return new PageImpl<>(kinderGardenDTOList,pageable, countQuery(conditions, params));

    }

    private  long countQuery(StringBuilder conditions, Map<String, Object> params){
        StringBuilder countQuery = new StringBuilder(" select count(*) from KinderGarden k ");
        Query query = entityManager.createQuery(countQuery.append(conditions).toString());

        for (Map.Entry<String, Object> stringObjectEntry : params.entrySet()) {
            query.setParameter(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }

        return (Long) query.getSingleResult();
    }
}
