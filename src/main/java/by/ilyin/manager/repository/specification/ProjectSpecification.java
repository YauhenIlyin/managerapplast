package by.ilyin.manager.repository.specification;

import by.ilyin.manager.entity.Project;
import by.ilyin.manager.evidence.KeyWordFilterProcess;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProjectSpecification implements Specification<Project> {

    private SearchCriteria criteria;

    public ProjectSpecification(SearchCriteria projectSearchCriteria) {
        this.criteria = projectSearchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate predicate;
        String criteriaOperation = criteria.getOperation();
        switch (criteriaOperation) {
            case KeyWordFilterProcess.OPERATION_MORE:
                predicate = builder.greaterThanOrEqualTo(
                        root.<String>get(criteria.getFieldName()), criteria.getValue().toString()
                );
                break;
            case KeyWordFilterProcess.OPERATION_LESS:
                predicate = builder.lessThanOrEqualTo(
                        root.<String>get(criteria.getFieldName()), criteria.getValue().toString()
                );
                break;
            case KeyWordFilterProcess.OPERATION_EQUALS:
                if (root.get(criteria.getFieldName()).getJavaType() == String.class) {
                    predicate = builder.like(
                            root.get(criteria.getFieldName()), "%" + criteria.getValue() + "%");
                } else {
                    predicate = builder.equal(root.get(criteria.getFieldName()), criteria.getValue());
                }
                break;
            default:
                predicate = null;
        }
        return predicate;
    }

}
