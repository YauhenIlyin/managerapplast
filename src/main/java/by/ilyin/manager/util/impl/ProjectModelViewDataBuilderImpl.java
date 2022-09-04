package by.ilyin.manager.util.impl;

import by.ilyin.manager.evidence.KeyWordsSessionRequest;
import by.ilyin.manager.util.AppBaseDataCore;
import by.ilyin.manager.util.ModelViewDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ProjectModelViewDataBuilderImpl implements ModelViewDataBuilder {

    private final AppBaseDataCore appBaseDataCore;

    @Autowired
    private ProjectModelViewDataBuilderImpl(AppBaseDataCore appBaseDataCore) {
        this.appBaseDataCore = appBaseDataCore;
    }


    @Override
    public ModelViewDataBuilder addAppServers(Model model) {
        return buildModel(model, KeyWordsSessionRequest.APP_SERVERS, appBaseDataCore.getApplicationServerList());
    }

    @Override
    public ModelViewDataBuilder addProgLangs(Model model) {
        return buildModel(model, KeyWordsSessionRequest.PROG_LANGS, appBaseDataCore.getProgrammingLanguageList());
    }

    @Override
    public ModelViewDataBuilder addDatabases(Model model) {
        return buildModel(model, KeyWordsSessionRequest.DATABASES, appBaseDataCore.getDatabaseList());
    }

    @Override
    public ModelViewDataBuilder addAppServers(ModelAndView model) {
        return buildModelAndView(model, KeyWordsSessionRequest.APP_SERVERS, appBaseDataCore.getApplicationServerList());
    }

    @Override
    public ModelViewDataBuilder addProgLangs(ModelAndView model) {
        return buildModelAndView(model, KeyWordsSessionRequest.PROG_LANGS, appBaseDataCore.getProgrammingLanguageList());
    }

    @Override
    public ModelViewDataBuilder addDatabases(ModelAndView model) {
        return buildModelAndView(model, KeyWordsSessionRequest.DATABASES, appBaseDataCore.getDatabaseList());
    }

    private ModelViewDataBuilder buildModel(Model model, String name, Object value) {
        if (model != null) {
            model.addAttribute(name, value);
        }
        return this;
    }

    private ModelViewDataBuilder buildModelAndView(ModelAndView model, String name, Object value) {
        if (model != null) {
            model.addObject(name, value);
        }
        return this;
    }
}
