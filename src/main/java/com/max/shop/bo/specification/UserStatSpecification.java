package com.max.shop.bo.specification;

import com.max.shop.dto.request.UserStatCriteriaDto;
import com.max.shop.entity.stat.UserStat;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UserStatSpecification {

    public static Specification<UserStat> buildListFilterForUserStat(UserStatCriteriaDto userStatCriteria) {
        return ((root, query, cb) -> {
            if (userStatCriteria == null) {
                return null;
            }

            List<Predicate> predicates = new ArrayList<>();

            if (userStatCriteria.getUserId() != null) {
                predicates.add(cb.equal(root.get("userId"), userStatCriteria.getUserId()));
            }

            if (userStatCriteria.getDateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("dateViews"), userStatCriteria.getDateFrom()));
            }

            if (userStatCriteria.getDateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dateViews"), userStatCriteria.getDateTo()));
            }

            if (userStatCriteria.getQuantityViewsFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("quantityViews"), userStatCriteria.getQuantityViewsFrom()));
            }

            if (userStatCriteria.getQuantityViewsTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("quantityViews"), userStatCriteria.getQuantityViewsTo()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
