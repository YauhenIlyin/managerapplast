package by.ilyin.manager.util.observer.impl;

import by.ilyin.manager.entity.ProgrammingLanguage;

import java.util.EventObject;
import java.util.List;

public class ProgramLangRepositoryEvent extends EventObject {

    public ProgramLangRepositoryEvent(List<ProgrammingLanguage> source) {
        super(source);
    }

    @Override
    public List<ProgrammingLanguage> getSource() {
        return (List<ProgrammingLanguage>) super.getSource();
    }

}
