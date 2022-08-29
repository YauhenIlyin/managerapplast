package by.ilyin.manager.service;

import by.ilyin.manager.entity.ProgrammingLanguage;
import by.ilyin.manager.util.observer.Observable;

import java.util.List;
import java.util.Optional;

public interface ProgrammingLanguageService extends Observable {

    Optional<ProgrammingLanguage> findByLanguageName(String languageName);

    Optional<ProgrammingLanguage> findById(Long id);

    List<ProgrammingLanguage> findAll();

    void save(ProgrammingLanguage programmingLanguage);

}
