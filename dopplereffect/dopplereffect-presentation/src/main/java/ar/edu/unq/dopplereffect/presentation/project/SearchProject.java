package ar.edu.unq.dopplereffect.presentation.project;

import ar.edu.unq.dopplereffect.presentation.search.Search;
import ar.edu.unq.dopplereffect.project.Project;
import ar.edu.unq.dopplereffect.project.ProjectBuilder;

public class SearchProject extends Search<Project> {
    private static final long serialVersionUID = 1L;

    private String nombre = "";

    public SearchProject() {
        this.save(new ProjectBuilder().withName("Wicket").withEstimatedEffort(33333).build());
        this.save(new ProjectBuilder().withName("PAPAPA").withEstimatedEffort(34234).build());
        this.save(new ProjectBuilder().withName("SASASA").withEstimatedEffort(11111).build());
        this.save(new ProjectBuilder().withName("LALALA").withEstimatedEffort(789898).build());

    }

    @Override
    public void save(final Project project) {
        // projectHome.getInstance().agregar(project);
        this.getResults().add(project);
        this.search();
    }

    @Override
    public void search() {
        // this.setResultado(projectHome.getInstance().buscarByExample(this.crearExample()));
    }

    @Override
    public void remove(final Project project) {
        // projectHome.getInstance().eliminar(project);
        this.getResults().remove(project);
    }

    @Override
    public void update(final Project project) {
        // projectHome.getInstance().actualizar(project);
        this.remove(project);
        this.save(project);
        this.search();
    }

    @Override
    public Project createExample() {
        return new Project();
    }

    public String getSearchByName() {
        return nombre;
    }

    public void setSearchByName(final String s) {
        nombre = s;
    }

}
