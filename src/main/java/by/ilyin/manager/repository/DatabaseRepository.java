package by.ilyin.manager.repository;

import by.ilyin.manager.entity.Database;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DatabaseRepository extends JpaRepository<Database, Long> {

    Optional<Database> findDatabaseByDatabaseName(String databaseName);

}
