package by.ilyin.manager.repository.specification;

public class SearchCriteria {

    private String fieldName;
    private String operation;
    private Object value;
    private boolean isAndCriteria = false;

    public SearchCriteria() {
    }

    public SearchCriteria(String fieldName, String operation, Object value) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.value = value;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
