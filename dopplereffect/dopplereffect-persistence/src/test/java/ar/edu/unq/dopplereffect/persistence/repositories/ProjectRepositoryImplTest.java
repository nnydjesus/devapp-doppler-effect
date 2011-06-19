package ar.edu.unq.dopplereffect.persistence.repositories;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unq.dopplereffect.builders.projects.ProjectBuilder;
import ar.edu.unq.dopplereffect.persistence.project.ProjectRepositoryImpl;
import ar.edu.unq.dopplereffect.persistence.util.SpringPersistenceTest;
import ar.edu.unq.dopplereffect.projects.Project;

public class ProjectRepositoryImplTest extends SpringPersistenceTest {

    private static final String PROJECT_NAME = "P1";

    private static final String UPDATE_PROJECT_NAME = "P5";

    private static final Long EFFORT = 2500L;

    @Autowired
    private ProjectRepositoryImpl repository;

    @Test
    public void testSave() {
        repository.save(this.createProject(PROJECT_NAME));
        Project project = repository.getByName(PROJECT_NAME);

        Assert.assertNotNull(project);
        Assert.assertEquals(PROJECT_NAME, project.getName());
        Assert.assertEquals(EFFORT, project.getMaxEffort());
    }

    // @Test
    // public void testDelete() {
    // repository.save(this.createProject(PROJECT_NAME + "sa"));
    // Project project = repository.getByName(PROJECT_NAME + "sa");
    //
    // Assert.assertNotNull(project);
    //
    // repository.delete(project);
    // Project project2 = repository.getByName(PROJECT_NAME + "sa");
    //
    // Assert.assertNull(project2);
    // }

    @Test
    public void testUpdate() {
        repository.save(this.createProject(PROJECT_NAME));
        Project project = repository.getByName(PROJECT_NAME);

        Assert.assertNotNull(project);

        project.setName(UPDATE_PROJECT_NAME);

        repository.update(project);
        Project project2 = repository.getByName(UPDATE_PROJECT_NAME);

        Assert.assertNotNull(project2);
    }

    private Project createProject(final String name) {
        return new ProjectBuilder().withName(name).withEstimatedEffort(EFFORT).build();
    }
}
