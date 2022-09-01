package by.ilyin.manager.repository.specification;

import by.ilyin.manager.entity.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class SpecificationBuilder<T extends BaseEntity> {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationBuilder with(String fieldName, String operation, Object value) {
        if (fieldName != null && operation != null && value != null) {
            params.add(new SearchCriteria(fieldName, operation, value));
        }
        return this;
    }

    public SpecificationBuilder with(SearchCriteria searchCriteria) {
        if (searchCriteria != null) {
            params.add(searchCriteria);
        }
        return this;
    }

    public Specification<T> build(FieldCriteriaTypes fieldCriteriaTypes) {
        if (params.size() == 0) {
            return null;
        }
        List<Specification> specs = params.stream()
                .map(ProjectSpecification::new)
                .collect(Collectors.toList());
        String currentCriteriaName = params.get(0).getFieldName();
        boolean isAndCurrent = fieldCriteriaTypes.isAndProjectCriteria(currentCriteriaName);
        Specification result = specs.get(0);
        for (int i = 1; i < params.size(); i++) {
            result = isAndCurrent ?
                    Specification.where(result).and(specs.get(i)) :
                    Specification.where(result).or(specs.get(i));
            currentCriteriaName = params.get(0).getFieldName();
            isAndCurrent = fieldCriteriaTypes.isAndProjectCriteria(currentCriteriaName);
        }
        return result;
    }
}
