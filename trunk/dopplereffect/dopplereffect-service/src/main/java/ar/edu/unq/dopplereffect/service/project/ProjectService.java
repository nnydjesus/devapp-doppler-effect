package ar.edu.unq.dopplereffect.service.project;

import java.util.List;

import ar.edu.unq.dopplereffect.service.Service;

public interface ProjectService extends Service {

    List<ProjectDTO> searchAllProjects();

    void newProject(ProjectDTO entity);

    void deleteProject(ProjectDTO entity);

    void updateProject(ProjectDTO entity);

}
