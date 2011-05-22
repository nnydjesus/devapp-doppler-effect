package ar.edu.unq.dopplereffect;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import ar.edu.unq.dopplereffect.persistence.repositories.ProjectRepositoryImpl;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.project.ProjectBuilder;

public class ProjectRepositoryImplTest extends AbstractTransactionalDataSourceSpringContextTests implements
        TestTransactional {

    private static final String PROJECT_NAME = "P1";

    private static final String UPDATE_PROJECT_NAME = "P5";

    private static final Integer EFFORT = 2500;

    // @Autowired
    // private Project project;

    @Autowired
    private ProjectRepositoryImpl repository;

    public ProjectRepositoryImpl getRepository() {
        return repository;
    }

    public void setRepository(final ProjectRepositoryImpl repository) {
        this.repository = repository;
    }

    @Test
    public void testSave() {
        repository.save(this.createProject(PROJECT_NAME));
        Project project = repository.getByName(PROJECT_NAME);

        Assert.assertNotNull(project);
        Assert.assertEquals(PROJECT_NAME, project.getName());
        Assert.assertEquals(EFFORT, project.getMaxEffort());
    }

    @Test
    public void testDelete() {
        repository.save(this.createProject(PROJECT_NAME + "sa"));
        Project project = repository.getByName(PROJECT_NAME + "sa");

        Assert.assertNotNull(project);

        repository.delete(project);
        Project project2 = repository.getByName(PROJECT_NAME + "sa");

        Assert.assertNull(project2);
    }

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

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "/spring/data-source.xml", "/spring/transactions.xml", "/spring/repositories.xml" };
    }

}
