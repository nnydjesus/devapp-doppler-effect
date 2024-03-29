package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.panel.SortablePanel;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.service.project.ProjectDTO;
import ar.edu.unq.dopplereffect.service.project.SkillDTO;
import ar.edu.unq.dopplereffect.service.project.SkillService;

public class AddSkillDialog extends Dialog {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    @SpringBean(name = "service.skill")
    private SkillService skillService;

    private List<SkillDTO> skills;

    private ProjectDTO project;

    private List<SkillDTO> results;

    /* *************************** CONSTRUCTORS *************************** */

    public AddSkillDialog(final String id, final ProjectDTO project) {
        super(id);
        this.setWidth(300);
        this.setHeight(400);
        this.setProject(project);
        this.setSkills(new ArrayList<SkillDTO>());
        this.getSkills().addAll(project.getSkills());
        this.setResults(this.getSkillService().searchAllSkills());
        this.getResults().removeAll(this.getSkills());

        Form<String> form = new Form<String>("form");

        form.add(new SortablePanel<SkillDTO>("skill1", this.getSkills()));
        form.add(new SortablePanel<SkillDTO>("skill2", this.getResults()));
        form.add(CustomComponent.addButtonSking(new Button("aceptar", new StringResourceModel("aceptbutton",
                new Model<String>(""))) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                HashSet<SkillDTO> hashSet = new HashSet<SkillDTO>(AddSkillDialog.this.getSkills());
                AddSkillDialog.this.getProject().setSkills(new LinkedList<SkillDTO>(hashSet));
                AddSkillDialog.this.getResults().removeAll(AddSkillDialog.this.getSkills());
                AddSkillDialog.this.close();
            }

        }));
        this.add(form);
    }

    /* **************************** ACCESSORS ***************************** */

    public List<SkillDTO> getResults() {
        return results;
    }

    public void setResults(final List<SkillDTO> results) {
        this.results = results;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(final ProjectDTO project) {
        this.project = project;
    }

    public List<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(final List<SkillDTO> skills) {
        this.skills = skills;
    }

    public SkillService getSkillService() {
        return skillService;
    }

    public void setSkillService(final SkillService skillService) {
        this.skillService = skillService;
    }
}
