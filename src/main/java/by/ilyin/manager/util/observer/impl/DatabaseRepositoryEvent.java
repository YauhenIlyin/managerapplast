package by.ilyin.manager.util.observer.impl;

import by.ilyin.manager.entity.Database;

import java.util.EventObject;
import java.util.List;

public class DatabaseRepositoryEvent extends EventObject {

    public DatabaseRepositoryEvent(List<Database> source) {
        super(source);
    }

    public DatabaseRepositoryEvent(Object source) {
        super(source);
    }

    @Override
    public List<Database> getSource() {
        return (List<Database>) super.getSource();
    }

}
