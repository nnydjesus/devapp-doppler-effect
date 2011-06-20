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

public abstract class AddIntervalDuration extends Dialog implements Serializable {

    private static final long serialVersionUID = 1L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private Date startDate;

    private Date endDate;

    /* *************************** CONSTRUCTORS *************************** */

    public AddIntervalDuration(final String id) {
        super(id);
        this.setModal(true);
        Form<Object> form = new Form<Object>("form");
        form.add(new DatePicker<Date>("startDate", new PropertyModel<Date>(this, "startDate")));

        form.add(new FeedbackPanel("feedback"));
        form.add(new DatePicker<Date>("endDate", new PropertyModel<Date>(this, "endDate")));

        AjaxButton ajaxButton = new AjaxButton("acept", new Model<String>("acept")) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(final AjaxRequestTarget target, final Form<?> theForm) {
                try {
                    AddIntervalDuration.this.validateDates();
                    AddIntervalDuration.this.onAccept(
                            new IntervalDurationStrategy(new DateTime(AddIntervalDuration.this.getStartDate()),
                                    new DateTime(AddIntervalDuration.this.getEndDate())), target);
                    target.appendJavascript(AddIntervalDuration.this.close().render().toString());
                    AddIntervalDuration.this.clean();
                } catch (AssignmentException e) {
                    theForm.error(AddIntervalDuration.this.getLocalizer().getString(e.getKey(),
                            AddIntervalDuration.this)
                            + e.getExtraData());
                    target.addComponent(theForm);
                } catch (Exception e) {
                    theForm.error(e.getMessage());
                    target.addComponent(theForm);
                }
            }
        };
        form.add(ajaxButton);
        this.add(form);
    }

    /* **************************** ACCESSORS ***************************** */

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(final Date start) {
        startDate = (Date) start.clone();
    }

    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    public void setEndDate(final Date endDate) {
        this.endDate = (Date) endDate.clone();
    }

    /* **************************** OPERATIONS **************************** */

    public void clean() {
        startDate = null;
        endDate = null;
    }

    /* ************************* PRIVATE METHODS ************************** */

    protected void validateDates() {
        if (this.getStartDate() == null || this.getEndDate() == null) {
            throw new AssignmentException("dateNull");
        }
        if (this.getStartDate().after(this.getEndDate())) {
            throw new AssignmentException("afterDate");
        }
    }

    /* ************************ ABSTRACT METHODS ************************** */

    public abstract void onAccept(final IntervalDurationStrategy intervalDurationStrategy, AjaxRequestTarget target);

}