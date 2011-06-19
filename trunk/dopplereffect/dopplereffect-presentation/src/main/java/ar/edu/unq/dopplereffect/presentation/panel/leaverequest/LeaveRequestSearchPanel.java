package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.util.Arrays;
import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.PropertyModel;

import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractPanel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

public class LeaveRequestSearchPanel extends AbstractSearchPanel<LeaveRequestSearchModel> {

    private static final long serialVersionUID = -2655592827481638839L;

    public LeaveRequestSearchPanel(final String id, final AjaxCallBack<Component> parentPage,
            final AbstractPanel<?> backPanel, final LeaveRequestSearchModel model) {
        super(id, parentPage, backPanel, model, Arrays.asList("startDate", "amountOfDays", "reason"),
                LeaveRequestPanel.class);
        this.getModelObject().search();

    }

    public LeaveRequestSearchPanel(final String id, final AjaxCallBack<Component> parentPage,
            final LeaveRequestSearchModel model) {
        this(id, parentPage, null, model);
    }

    @Override
    protected String getSortName() {
        return "startDate";
    }

    @Override
    protected String getFormWicketId() {
        return "searchLeaveRequestForm";
    }

    @Override
    protected String getNewFromBeanWicketId() {
        return "newLeaveRequest";
    }

    @Override
    protected void buildForm(final Form<LeaveRequestSearchModel> form) {
        StyleDateConverter converter = new StyleDateConverter(true);
        DateTextField dateTextField = new DateTextField("searchByDate", new PropertyModel<Date>(
                form.getDefaultModelObject(), "searchByDate"), converter);
        dateTextField.add(new DatePicker());
        form.add(dateTextField);
    }

    @Override
    protected LeaveRequestAjaxDataTablePage createAjaxTable() {
        return new LeaveRequestAjaxDataTablePage(this, this.getTableWicketId(), this.getSortName(),
                (LeaveRequestSearchModel) this.getDefaultModelObject(), this.getCallback(), this.getFields(),
                this.getAbmClass());
    }
}
