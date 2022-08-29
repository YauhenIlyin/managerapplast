package by.ilyin.manager.util.observer.impl;

import by.ilyin.manager.entity.ApplicationServer;
import by.ilyin.manager.util.AppBaseDataCore;
import by.ilyin.manager.util.observer.Observer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;
import java.util.List;

public class AppServerRepositoryObserver implements Observer {

    private final AppBaseDataCore appBaseDataCore;

    @Autowired
    public AppServerRepositoryObserver(AppBaseDataCore appBaseDataCore) {
        this.appBaseDataCore = appBaseDataCore;
    }

    @Override
    public void handleEvent(EventObject event) {
        List<ApplicationServer> appServerList = (List<ApplicationServer>) event.getSource();
        appBaseDataCore.setApplicationServerList(appServerList);
    }
}
