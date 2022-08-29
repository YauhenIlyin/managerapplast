package by.ilyin.manager.util.observer.impl;

import by.ilyin.manager.entity.ApplicationServer;

import java.util.EventObject;
import java.util.List;

public class AppServerRepositoryEvent extends EventObject {

    public AppServerRepositoryEvent(List<ApplicationServer> source) {
        super(source);
    }

    @Override
    public List<ApplicationServer> getSource() {
        return (List<ApplicationServer>) super.getSource();
    }

}
