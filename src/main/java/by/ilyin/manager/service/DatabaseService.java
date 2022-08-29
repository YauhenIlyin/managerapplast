package by.ilyin.manager.service;

import by.ilyin.manager.entity.Database;
import by.ilyin.manager.util.observer.Observable;

import java.util.List;
import java.util.Optional;

public interface DatabaseService extends Observable {

    Optional<Database> findByName(String databaseName);

    Optional<Database> findById(Long id);

    List<Database> findAll();

    void save(Database database);

}
