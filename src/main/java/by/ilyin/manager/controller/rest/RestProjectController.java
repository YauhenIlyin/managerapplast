package by.ilyin.manager.controller.rest;


import by.ilyin.manager.entity.Project;
import by.ilyin.manager.exception.rest.ProjectNotFoundException;
import by.ilyin.manager.service.ProjectService;
import by.ilyin.manager.util.rest.ProjectErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class RestProjectController {

    private final ProjectService projectService;

    @Autowired
    public RestProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public List<Project> getProjects() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Project getOneProject(@PathVariable("id") long id) throws ProjectNotFoundException {
        Optional<Project> optionalProject = projectService.findById(id);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new ProjectNotFoundException("dsa");
        }
        //todo there is an exception from the service, and the handler catches it
    }

    @ExceptionHandler
    private ResponseEntity<ProjectErrorResponse> handleException(ProjectNotFoundException e) {
        ProjectErrorResponse response = new ProjectErrorResponse("Project with this id not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
