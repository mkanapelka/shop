package com.max.shop.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.JoinType;

@UtilityClass
public class BaseSpecification {

    public <T> Specification<T> withFetch(String relatedEntityName) {
        return ((root, query, cb) -> {
            //if always do fetch, pagination will fail
            if (query.getResultType() == Long.class) {
                root.join(relatedEntityName, JoinType.LEFT);
            } else {
                root.fetch(relatedEntityName, JoinType.LEFT);
            }
            return null;
        });
    }
}
