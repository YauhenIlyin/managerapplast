package by.ilyin.manager.service.impl;

import by.ilyin.manager.entity.Database;
import by.ilyin.manager.repository.DatabaseRepository;
import by.ilyin.manager.service.DatabaseService;
import by.ilyin.manager.util.observer.Observer;
import by.ilyin.manager.util.observer.impl.DatabaseRepositoryEvent;
import by.ilyin.manager.util.observer.impl.DatabaseRepositoryObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    private final DatabaseRepository databaseRepository;
    private DatabaseRepositoryObserver observer;

    @Autowired
    public DatabaseServiceImpl(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    @Override
    public Optional<Database> findByName(String databaseName) {
        return databaseRepository.findDatabaseByDatabaseName(databaseName);
    }

    @Override
    public Optional<Database> findById(Long id) {
        return databaseRepository.findById(id);
    }

    @Override
    public List<Database> findAll() {
        return databaseRepository.findAll();
    }

    @Transactional
    @Override
    public void save(Database database) {
        databaseRepository.save(database);
        notifyObserver();
    }

    @Override
    public void attach(Observer observer) {
        this.observer = (DatabaseRepositoryObserver) observer;
    }

    @Override
    public void detach() {
        this.observer = null;
    }

    @Override
    public void notifyObserver() {
        if (observer != null) {
            DatabaseRepositoryEvent event = new DatabaseRepositoryEvent(findAll());
            observer.handleEvent(event);
        }
    }
}
