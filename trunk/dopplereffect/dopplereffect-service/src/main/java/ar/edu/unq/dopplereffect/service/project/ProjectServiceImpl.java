package ar.edu.unq.dopplereffect.service.project;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.dopplereffect.persistence.project.ProjectRepositoryImpl;
import ar.edu.unq.dopplereffect.projects.Project;
import ar.edu.unq.dopplereffect.projects.Skill;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final long serialVersionUID = -8603838797683404612L;

    private ProjectRepositoryImpl projectRepo;

    private SkillServiceImpl skillService;

    public ProjectRepositoryImpl getProjectRepo() {
        return projectRepo;
    }

    public void setProjectRepo(final ProjectRepositoryImpl projectRepo) {
        this.projectRepo = projectRepo;
    }

    public SkillServiceImpl getSkillService() {
        return skillService;
    }

    public void setSkillService(final SkillServiceImpl skillService) {
        this.skillService = skillService;
    }

    @Override
    @Transactional
    public List<ProjectDTO> searchAllProjects() {
        return this.convertAll(this.getProjectRepo().searchAll());
    }

    private List<ProjectDTO> convertAll(final List<Project> projects) {
        List<ProjectDTO> results = new LinkedList<ProjectDTO>();
        for (Project p : projects) {
            results.add(this.convert(p));
        }
        return results;
    }

    private ProjectDTO convert(final Project project) {
        ProjectDTO pDTO = new ProjectDTO();
        pDTO.setName(project.getName());
        pDTO.setMaxEffort(project.getMaxEffort());
        pDTO.setSkills(this.getSkillService().convertAll(project.getSkills()));
        return pDTO;
    }

    @Override
    @Transactional
    public void newProject(final ProjectDTO entity) {
        Project project = new Project();
        project.setName(entity.getName());
        project.setMaxEffort(entity.getMaxEffort());
        Set<Skill> skills = new HashSet<Skill>();
        for (SkillDTO skDTO : entity.getSkills()) {
            skills.add(this.getSkillService().getSkillRepo().findOrCreate(skDTO.getName(), skDTO.getLevel()));
        }
        project.setSkills(skills);
        this.getProjectRepo().save(project);
    }

    @Override
    @Transactional
    public void deleteProject(final ProjectDTO entity) {
        Project project = this.getProjectRepo().getByName(entity.getName());
        this.getProjectRepo().delete(project);
    }

    @Override
    @Transactional
    public void updateProject(final ProjectDTO entity) {
        Project project = this.getProjectRepo().getByName(entity.getName());
        project.setName(entity.getName());
        project.setMaxEffort(entity.getMaxEffort());
        Set<Skill> skills = new HashSet<Skill>();
        for (SkillDTO skDTO : entity.getSkills()) {
            skills.add(this.getSkillService().getSkillRepo().findOrCreate(skDTO.getName(), skDTO.getLevel()));
        }
        project.setSkills(skills);
        this.getProjectRepo().update(project);
    }

}
