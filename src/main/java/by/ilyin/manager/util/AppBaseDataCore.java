package by.ilyin.manager.util;

import by.ilyin.manager.entity.ApplicationServer;
import by.ilyin.manager.entity.Database;
import by.ilyin.manager.entity.ProgrammingLanguage;
import by.ilyin.manager.service.ApplicationServerService;
import by.ilyin.manager.service.DatabaseService;
import by.ilyin.manager.service.ProgrammingLanguageService;
import by.ilyin.manager.util.observer.impl.AppServerRepositoryObserver;
import by.ilyin.manager.util.observer.impl.DatabaseRepositoryObserver;
import by.ilyin.manager.util.observer.impl.ProgLangRepositoryObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppBaseDataCore {

    private DatabaseService databaseService;
    private ProgrammingLanguageService programmingLanguageService;
    private ApplicationServerService applicationServerService;
    private List<Database> databaseList;
    private List<ProgrammingLanguage> programmingLanguageList;
    private List<ApplicationServer> applicationServerList;

    @Autowired
    public AppBaseDataCore(DatabaseService databaseService,
                           ProgrammingLanguageService programmingLanguageService,
                           ApplicationServerService applicationServerService) {
        this.databaseService = databaseService;
        this.programmingLanguageService = programmingLanguageService;
        this.applicationServerService = applicationServerService;

        this.databaseList = databaseService.findAll();
        this.programmingLanguageList = programmingLanguageService.findAll();
        this.applicationServerList = applicationServerService.findAll();
        attachObservers();
    }

    public List<Database> getDatabaseList() {
        return databaseList;
    }

    public void setDatabaseList(List<Database> databaseList) {
        this.databaseList = databaseList;
    }

    public List<ProgrammingLanguage> getProgrammingLanguageList() {
        return programmingLanguageList;
    }

    public void setProgrammingLanguageList(List<ProgrammingLanguage> programmingLanguageList) {
        this.programmingLanguageList = programmingLanguageList;
    }

    public List<ApplicationServer> getApplicationServerList() {
        return applicationServerList;
    }

    public void setApplicationServerList(List<ApplicationServer> applicationServerList) {
        this.applicationServerList = applicationServerList;
    }

    public void attachObservers() {
        applicationServerService.attach(new AppServerRepositoryObserver(this));
        programmingLanguageService.attach(new ProgLangRepositoryObserver(this));
        databaseService.attach(new DatabaseRepositoryObserver(this));
    }

    public void detachObservers() {
        applicationServerService.detach();
        programmingLanguageService.detach();
        databaseService.detach();
    }
}