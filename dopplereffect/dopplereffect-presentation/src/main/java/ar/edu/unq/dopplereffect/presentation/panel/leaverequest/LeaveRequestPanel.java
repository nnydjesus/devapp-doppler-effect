package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.leaverequests.LeaveRequestType;
import ar.edu.unq.dopplereffect.presentation.pages.basic.EntityPanel;
import ar.edu.unq.dopplereffect.time.DurationStrategy;
import ar.edu.unq.dopplereffect.time.IntervalDurationStrategy;
import ar.edu.unq.dopplereffect.time.OneDayDurationStrategy;

public class LeaveRequestPanel extends EntityPanel<LeaveRequest> {

    private static final long serialVersionUID = 1511189466309907850L;

    public LeaveRequestPanel(final String id, final LeaveRequestSearchPanel previousPage) {
        super(id, new LeaveRequest(), previousPage);
    }

    public LeaveRequestPanel(final String id, final LeaveRequest model, final LeaveRequestSearchPanel previousPage,
            final Boolean editMode) {
        super(id, model, previousPage, editMode);
    }

    @Override
    protected void addFields(final Form<LeaveRequest> form) {
        form.add(this.getFeedbackPanel());
        this.addTypeCombo(form);
        this.addDurationTypeCombo(form);
        this.addStartDateField(form);
        this.addEndDateField(form);
    }

    protected void addDurationTypeCombo(final Form<LeaveRequest> form) {
        @SuppressWarnings({ "rawtypes", "unchecked" })
        DropDownChoice durationTypeDropDownChoice = new DropDownChoice("duration", new PropertyModel<DurationStrategy>(
                form.getDefaultModelObject(), "strategy"), Arrays.asList(new OneDayDurationStrategy(),
                new IntervalDurationStrategy()));
        durationTypeDropDownChoice.setRequired(true);
        form.add(durationTypeDropDownChoice);
    }

    protected void addTypeCombo(final Form<LeaveRequest> form) {
        // TODO traer estas choices de un servicio
        List<LeaveRequestType> choices = new LinkedList<LeaveRequestType>();
        @SuppressWarnings({ "rawtypes", "unchecked" })
        DropDownChoice typeDropDownChoice = new DropDownChoice("type", new PropertyModel<LeaveRequestType>(
                form.getDefaultModelObject(), "type"), choices);
        typeDropDownChoice.setRequired(true);
        form.add(typeDropDownChoice);
    }

    private void addStartDateField(final Form<LeaveRequest> form) {
        // TODO una chanchada, prometo mejorarlo
        StyleDateConverter converter = new StyleDateConverter(true);
        IModel<Date> model = new Model<Date>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Date getObject() {
                DurationStrategy durStrategy = ((LeaveRequest) form.getDefaultModelObject()).getDurationStrategy();
                if (durStrategy == null) {
                    return null;
                }
                if (durStrategy instanceof IntervalDurationStrategy) {
                    return ((IntervalDurationStrategy) durStrategy).getStartDate().toDate();
                }
                if (durStrategy instanceof OneDayDurationStrategy) {
                    return ((OneDayDurationStrategy) durStrategy).getDate().toDate();
                }
                return null;
            }

            @Override
            public void setObject(final Date object) {
                DurationStrategy durStrategy = ((LeaveRequest) form.getDefaultModelObject()).getDurationStrategy();
                if (durStrategy instanceof IntervalDurationStrategy) {
                    ((IntervalDurationStrategy) durStrategy).setStartDate(new DateTime(object));
                }
                if (durStrategy instanceof OneDayDurationStrategy) {
                    ((OneDayDurationStrategy) durStrategy).setDate(new DateTime(object));
                }
            }
        };
        DateTextField dateTextField = new DateTextField("startDate", model, converter);
        dateTextField.add(new DatePicker());
        dateTextField.setRequired(true);
        form.add(dateTextField);
    }

    private void addEndDateField(final Form<LeaveRequest> form) {
        StyleDateConverter converter = new StyleDateConverter(true);
        IModel<Date> model = new Model<Date>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Date getObject() {
                DurationStrategy durStrategy = ((LeaveRequest) form.getDefaultModelObject()).getDurationStrategy();
                if (durStrategy == null) {
                    return null;
                }
                if (durStrategy instanceof IntervalDurationStrategy) {
                    return ((IntervalDurationStrategy) durStrategy).getEndDate().toDate();
                }
                return null;
            }

            @Override
            public void setObject(final Date object) {
                DurationStrategy durStrategy = ((LeaveRequest) form.getDefaultModelObject()).getDurationStrategy();
                if (durStrategy instanceof IntervalDurationStrategy) {
                    ((IntervalDurationStrategy) durStrategy).setEndDate(new DateTime(object));
                }
            }
        };
        DateTextField dateTextField = new DateTextField("endDate", model, converter);
        dateTextField.add(new DatePicker());
        form.add(dateTextField);
    }

    @Override
    protected String getFormWicketId() {
        return "leaveRequestForm";
    }
}
