package by.ilyin.manager.repository;

import by.ilyin.manager.entity.ApplicationServer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationServerRepository extends JpaRepository<ApplicationServer, Long> {

    Optional<ApplicationServer> findByServerName(String serverName);

}
