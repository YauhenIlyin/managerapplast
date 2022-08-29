package by.ilyin.manager.util.observer.impl;

import by.ilyin.manager.entity.ProgrammingLanguage;
import by.ilyin.manager.util.AppBaseDataCore;
import by.ilyin.manager.util.observer.Observer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;
import java.util.List;

public class ProgLangRepositoryObserver implements Observer {

    private final AppBaseDataCore appBaseDataCore;

    @Autowired
    public ProgLangRepositoryObserver(AppBaseDataCore appBaseDataCore) {
        this.appBaseDataCore = appBaseDataCore;
    }

    @Override
    public void handleEvent(EventObject event) {
        List<ProgrammingLanguage> progLangList = (List<ProgrammingLanguage>) event.getSource();
        appBaseDataCore.setProgrammingLanguageList(progLangList);
    }
}
