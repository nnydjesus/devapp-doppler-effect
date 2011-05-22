package ar.edu.unq.dopplereffect.presentation.project;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import ar.edu.unq.dopplereffect.data.PersonalData;

/**
 * TODO: description
 */
public class FormPersonalData<T extends PersonalData> extends Form<T> {

    private static final long serialVersionUID = 1L;

    public FormPersonalData(final String id, final T personalData) {
        super(id, new CompoundPropertyModel<T>(personalData));
        this.add(new TextField("firstName"));
        this.add(new TextField("phoneNumber"));
        this.add(new TextField("email"));
        this.add(new TextField("address"));
    }

}
