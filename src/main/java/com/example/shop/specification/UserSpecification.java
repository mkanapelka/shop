package com.example.shop.specification;

import com.example.shop.dto.request.UserListCriteriaDto;
import com.example.shop.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<User> buildListFilter(UserListCriteriaDto userCriteria) {
        return ((root, query, cb) -> {
            if (userCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(userCriteria.getUsername())) {
                predicates.add(cb.equal(root.get("name"), userCriteria.getUsername()));
            }
            if (StringUtils.isNotBlank(userCriteria.getEmail())) {
                predicates.add(cb.equal(root.get("email"), userCriteria.getEmail()));
            }
            if (userCriteria.getDateCreatedFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("created"), userCriteria.getDateCreatedFrom()));
            }
            if (userCriteria.getDateCreatedTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("created"), userCriteria.getDateCreatedTo()));
            }
            if (StringUtils.isNotBlank(userCriteria.getUsername())) {
                predicates.add(cb.equal(root.get("name"), userCriteria.getUsername()));
            }
            if (userCriteria.getIsActive() != null) {
                //TODO add filter
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
