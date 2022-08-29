package by.ilyin.manager.util.observer.impl;

import by.ilyin.manager.entity.Database;
import by.ilyin.manager.util.AppBaseDataCore;
import by.ilyin.manager.util.observer.Observer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;
import java.util.List;

public class DatabaseRepositoryObserver implements Observer {

    private final AppBaseDataCore appBaseDataCore;

    @Autowired
    public DatabaseRepositoryObserver(AppBaseDataCore appBaseDataCore) {
        this.appBaseDataCore = appBaseDataCore;
    }

    @Override
    public void handleEvent(EventObject event) {
        List<Database> databaseList = (List<Database>) event.getSource();
        appBaseDataCore.setDatabaseList(databaseList);
    }
}
