package by.ilyin.manager.util;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface ModelViewDataBuilder {

    ModelViewDataBuilder addAppServers(Model model);

    ModelViewDataBuilder addProgLangs(Model model);

    ModelViewDataBuilder addDatabases(Model model);

    ModelViewDataBuilder addAppServers(ModelAndView model);

    ModelViewDataBuilder addProgLangs(ModelAndView model);

    ModelViewDataBuilder addDatabases(ModelAndView model);
}
