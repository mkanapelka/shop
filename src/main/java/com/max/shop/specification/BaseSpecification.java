package com.max.shop.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@UtilityClass
public class BaseSpecification {

    public <T> Specification<T> withFetch(String... relatedEntityNames) {
        return ((root, query, cb) -> {
            //if always do fetch, pagination will fail
            if (query.getResultType() == Long.class) {
                Join<?, ?> j = null;
                for (String name : relatedEntityNames) {
                    if (j == null) {
                        j = root.join(name, JoinType.LEFT);
                    } else {
                        j = j.join(name, JoinType.LEFT);
                    }
                }
            } else {
                Fetch<?, ?> f = null;
                for (String name : relatedEntityNames) {
                    if (f == null) {
                        f = root.fetch(name, JoinType.LEFT);
                    } else {
                        f = f.fetch(name, JoinType.LEFT);
                    }
                }
            }
            return null;
        });
    }
}
