package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.panel.SortablePanel;
import ar.edu.unq.dopplereffect.presentation.project.SkillSearchModel;
import ar.edu.unq.dopplereffect.projects.Project;
import ar.edu.unq.dopplereffect.projects.Skill;

/**
 */
public class AddSkillDialog extends Dialog {

    private static final long serialVersionUID = 1L;

    @SpringBean(name = "skillSearchModel")
    private SkillSearchModel skillSearchModel;

    private List<Skill> skills;

    private Project project;

    private List<Skill> results;

    public AddSkillDialog(final String id, final Project project) {
        super(id);
        this.setWidth(300);
        this.setHeight(500);
        this.setResizable(false);
        this.setProject(project);
        this.setSkills(new ArrayList<Skill>());
        this.getSkills().addAll(project.getSkills());
        this.getSkillSearchModel().search();
        this.setResults(new ArrayList<Skill>(this.getSkillSearchModel().getResults()));
        this.getResults().removeAll(this.getSkills());
        this.add(new SortablePanel<Skill>("skill1", this.getSkills(), true));
        this.add(new SortablePanel<Skill>("skill2", this.getResults(), false));
        this.add(CustomComponent.addButtonSking(new Link<String>("aceptar") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                HashSet<Skill> hashSet = new HashSet<Skill>(AddSkillDialog.this.getSkills());
                AddSkillDialog.this.getProject().setSkills(hashSet);
                AddSkillDialog.this.getResults().removeAll(AddSkillDialog.this.getSkills());
                AddSkillDialog.this.close();
            }

        }));
    }

    public void setResults(final List<Skill> results) {
        this.results = results;
    }

    public List<Skill> getResults() {
        return results;
    }

    public void setProject(final Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void setSkills(final List<Skill> skills) {
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkillSearchModel(final SkillSearchModel skillSearchModel) {
        this.skillSearchModel = skillSearchModel;
    }

    public SkillSearchModel getSkillSearchModel() {
        return skillSearchModel;
    }
}
