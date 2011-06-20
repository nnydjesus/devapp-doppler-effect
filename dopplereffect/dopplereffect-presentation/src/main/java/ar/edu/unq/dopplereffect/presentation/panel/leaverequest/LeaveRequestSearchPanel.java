package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;

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
        // StyleDateConverter converter = new StyleDateConverter(true);
        // DateTextField dateTextField = new DateTextField("searchByDate", new
        // PropertyModel<Date>(
        // form.getDefaultModelObject(), "searchByDate"), converter);
        // dateTextField.add(new DatePicker());
        // form.add(dateTextField);
        List<String> choices = this.getModelObject().getService().searchAllReasons();
        DropDownChoice<String> reasonCombo = new DropDownChoice<String>("searchByReason", choices);
        form.add(reasonCombo);
    }

    @Override
    protected LeaveRequestAjaxDataTablePage createAjaxTable() {
        return new LeaveRequestAjaxDataTablePage(this, this.getTableWicketId(), this.getSortName(),
                (LeaveRequestSearchModel) this.getDefaultModelObject(), this.getCallback(), this.getFields(),
                this.getAbmClass());
    }
}