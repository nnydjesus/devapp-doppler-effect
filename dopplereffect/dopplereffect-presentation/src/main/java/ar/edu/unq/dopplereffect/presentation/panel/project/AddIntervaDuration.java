package ar.edu.unq.dopplereffect.presentation.panel.project;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.DateTime;
import org.odlabs.wiquery.ui.datepicker.DatePicker;
import org.odlabs.wiquery.ui.dialog.Dialog;

import ar.edu.unq.dopplereffect.exceptions.AssignmentException;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;

/**
 */
public abstract class AddIntervaDuration extends Dialog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date startDate;

    private Date endDate;

    public AddIntervaDuration(final String id) {
        super(id);
        this.setModal(true);
        Form<Object> form = new Form<Object>("form");
        form.add(new DatePicker<Date>("startDate", new PropertyModel<Date>(this, "startDate")));

        form.add(new FeedbackPanel("feedback"));
        form.add(new DatePicker<Date>("endDate", new PropertyModel<Date>(this, "endDate")));

        AjaxButton ajaxButton = new AjaxButton("acept", new Model<String>("acept")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> form) {
                try {
                    AddIntervaDuration.this.validateDates();
                    AddIntervaDuration.this.onAcept(
                            new IntervalDurationStrategy(new DateTime(AddIntervaDuration.this.getStartDate()),
                                    new DateTime(AddIntervaDuration.this.getEndDate())), target);
                    target.appendJavascript(AddIntervaDuration.this.close().render().toString());
                    AddIntervaDuration.this.clean();
                } catch (AssignmentException e) {
                    form.error(AddIntervaDuration.this.getLocalizer().getString(e.getKey(), AddIntervaDuration.this)
                            + e.getExtraData());
                    target.addComponent(form);
                } catch (Exception e) {
                    form.error(e.getMessage());
                    target.addComponent(form);
                }
            }
        };
        form.add(ajaxButton);
        this.add(form);
    }

    protected void validateDates() {
        if (this.getStartDate() == null || this.getEndDate() == null) {
            throw new AssignmentException("dateNull");
        }
        if (this.getStartDate().after(this.getEndDate())) {
            throw new AssignmentException("afterDate");
        }
    }

    public abstract void onAcept(final IntervalDurationStrategy intervalDurationStrategy, AjaxRequestTarget target);

    public void setStartDate(final Date start) {
        startDate = start;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void clean() {
        startDate = null;
        endDate = null;
    }

}