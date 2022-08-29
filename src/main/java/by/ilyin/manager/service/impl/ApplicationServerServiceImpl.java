package by.ilyin.manager.service.impl;

import by.ilyin.manager.entity.ApplicationServer;
import by.ilyin.manager.repository.ApplicationServerRepository;
import by.ilyin.manager.service.ApplicationServerService;
import by.ilyin.manager.util.observer.Observer;
import by.ilyin.manager.util.observer.impl.AppServerRepositoryEvent;
import by.ilyin.manager.util.observer.impl.AppServerRepositoryObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServerServiceImpl implements ApplicationServerService {

    private final ApplicationServerRepository applicationServerRepository;
    private AppServerRepositoryObserver observer;

    @Autowired
    public ApplicationServerServiceImpl(ApplicationServerRepository applicationServerRepository) {
        this.applicationServerRepository = applicationServerRepository;
    }

    @Override
    public Optional<ApplicationServer> findByServerName(String serverName) {
        return applicationServerRepository.findByServerName(serverName);
    }

    @Override
    public Optional<ApplicationServer> findById(Long id) {
        return applicationServerRepository.findById(id);
    }

    @Override
    public List<ApplicationServer> findAll() {
        return applicationServerRepository.findAll();
    }

    @Transactional
    @Override
    public void save(ApplicationServer applicationServer) {
        applicationServerRepository.save(applicationServer);
        notifyObserver();
    }

    @Override
    public void attach(Observer observer) {
        this.observer = (AppServerRepositoryObserver) observer;
    }

    @Override
    public void detach() {
        this.observer = null;
    }

    @Override
    public void notifyObserver() {
        if (observer != null) {
            AppServerRepositoryEvent event = new AppServerRepositoryEvent(findAll());
            observer.handleEvent(event);
        }
    }
}
