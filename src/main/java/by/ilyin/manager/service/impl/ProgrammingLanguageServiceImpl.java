package by.ilyin.manager.service.impl;

import by.ilyin.manager.entity.ProgrammingLanguage;
import by.ilyin.manager.repository.ProgrammingLanguageRepository;
import by.ilyin.manager.service.ProgrammingLanguageService;
import by.ilyin.manager.util.observer.Observer;
import by.ilyin.manager.util.observer.impl.ProgLangRepositoryObserver;
import by.ilyin.manager.util.observer.impl.ProgramLangRepositoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

    private final ProgrammingLanguageRepository programmingLanguageRepository;
    private ProgLangRepositoryObserver observer;

    @Autowired
    public ProgrammingLanguageServiceImpl(ProgrammingLanguageRepository programmingLanguageRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
    }

    @Override
    public Optional<ProgrammingLanguage> findByLanguageName(String languageName) {
        return programmingLanguageRepository.findByLanguageName(languageName);
    }

    @Override
    public Optional<ProgrammingLanguage> findById(Long id) {
        return programmingLanguageRepository.findById(id);
    }

    @Override
    public List<ProgrammingLanguage> findAll() {
        return programmingLanguageRepository.findAll();
    }

    @Transactional
    @Override
    public void save(ProgrammingLanguage programmingLanguage) {
        programmingLanguageRepository.save(programmingLanguage);
        notifyObserver();
    }

    @Override
    public void attach(Observer observer) {
        this.observer = (ProgLangRepositoryObserver) observer;
    }

    @Override
    public void detach() {
        this.observer = null;
    }

    @Override
    public void notifyObserver() {
        if (observer != null) {
            ProgramLangRepositoryEvent event = new ProgramLangRepositoryEvent(findAll());
            observer.handleEvent(event);
        }
    }
}

