package ar.edu.unq.dopplereffect.presentation.panel.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;
import org.joda.time.DateTime;
import org.odlabs.wiquery.ui.datepicker.DatePicker;
import org.odlabs.wiquery.ui.datepicker.DatePicker.ShowOnEnum;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.calendar.Calendar;
import ar.edu.unq.dopplereffect.calendar.CalendarStrategy;
import ar.edu.unq.dopplereffect.calendar.Calendareable;
import ar.edu.unq.dopplereffect.calendar.Matrix;
import ar.edu.unq.dopplereffect.calendar.MonthStrategy;
import ar.edu.unq.dopplereffect.calendar.WeekdayStrategy;
import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.jquery.scroolpane.ScrollPaneBehavior;
import ar.edu.unq.dopplereffect.presentation.panel.AjaxReflextionActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.presentation.util.ReflextionAjaxLink;

public class CalendarPanel<T extends Calendareable> extends AbstractCallbackPanel<Model<SearchModel<T>>> {
    private static final long serialVersionUID = 1L;

    // @formatter:off
    private static final String NEXT_METHOD = "next";
    private static final String PREVIOUS_METHOD = "previous";
    private static final String WEEKLY_METHOD = "weekly";
    private static final String MONTHLY_METHOD = "monthly";
    // @formatter:on

    private SearchModel<T> model;

    private Calendar<T> calendar;

    private Matrix<T, DateTime, Assignable> matrix;

    private MonthStrategy monthStrategy;

    private WeekdayStrategy weekdayStrategy;

    private WebMarkupContainer scrollpane;

    private Date datePicketModel;

    private Model<String> hastaModel;

    private DateFormat dateFormat;

    private AjaxCallBack<Component> callback;

    private LeaveRequestSearchModel leaveRequestSearchModel;

    private CalendarTable<T> calendarTable;

    public CalendarPanel(final String id, final SearchModel<T> employeeSearchModel,
            final LeaveRequestSearchModel leaveReqSearchModel, final AjaxCallBack<Component> callback) {
        super(id, null, null, new Model<SearchModel<T>>(employeeSearchModel));
        model = employeeSearchModel;
        this.setLeaveRequestSearchModel(leaveReqSearchModel);
        this.setCallback(callback);
        employeeSearchModel.search();
        DateTime day = new DateTime();
        monthStrategy = new MonthStrategy(day);
        weekdayStrategy = new WeekdayStrategy(day);
        this.setCalendar(new Calendar<T>(monthStrategy));
        this.setMatrix(this.getCalendar().getCalendar(model.getResults()));
        scrollpane = new WebMarkupContainer("scrollpane");
        scrollpane.add(new ScrollPaneBehavior().setShowArrows(true));
        calendarTable = new CalendarTable<T>(this);
        this.makePage();
        this.add(scrollpane);
    }

    private void makePage() {
        hastaModel = new Model<String>("");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        this.updateTable();
        this.addComponents();
    }

    protected void updateTable() {
        model.search();
        this.setMatrix(this.getCalendar().getCalendar(model.getResults()));
        CalendarStrategy strategy = this.getCalendar().getStrategy().cloneStrategy();
        datePicketModel = this.getCalendar().getStrategy().getDay().toDate();
        scrollpane.addOrReplace(calendarTable.updateTable());
        hastaModel.setObject(dateFormat.format(strategy.getDay().toDate()));
    }

    protected void addComponents() {
        DatePicker<Date> datePicker = new DatePicker<Date>("datePicker", new PropertyModel<Date>(this,
                "datePicketModel"), Date.class).setButtonText("<div class=\"ui-icon ui-icon-calendar\"></div>")
                .setShowOn(ShowOnEnum.BOTH).setShowButtonPanel(true);

        this.add(datePicker);

        this.add(new Label("hasta", hastaModel));

        this.add(CustomComponent.addButtonSking(new AjaxReflextionActionPanel<CalendarPanel<T>>("nextMonth", this,
                NEXT_METHOD, "next.png", "")));

        this.add(CustomComponent.addButtonSking(new AjaxReflextionActionPanel<CalendarPanel<T>>("previousMonth", this,
                PREVIOUS_METHOD, "previous.png", "")));

        this.add(CustomComponent.addButtonSking(new ReflextionAjaxLink<CalendarPanel<T>>("mensual", MONTHLY_METHOD,
                this, this, new StringResourceModel("monthday", new Model<String>("")))));

        this.add(CustomComponent.addButtonSking(new ReflextionAjaxLink<CalendarPanel<T>>("semanal", WEEKLY_METHOD,
                this, this, new StringResourceModel("weekday", new Model<String>("")))));
    }

    public void next() {
        this.getCalendar().getStrategy().next();
        this.updateTable();
    }

    public void previous() {
        this.getCalendar().getStrategy().previous();
        this.updateTable();
    }

    public void weekly() {
        this.getCalendar().setStrategy(weekdayStrategy);
        this.updateTable();
    }

    public void monthly() {
        this.getCalendar().setStrategy(monthStrategy);
        this.updateTable();
    }

    public void setDatePicketModel(final Date datePicketModel) {
        this.datePicketModel = (Date) datePicketModel.clone();
    }

    public Date getDatePicketModel() {
        if (datePicketModel == null) {
            return null;
        }
        return (Date) datePicketModel.clone();
    }

    @Override
    public void setCallback(final AjaxCallBack<Component> callback) {
        this.callback = callback;
    }

    @Override
    public AjaxCallBack<Component> getCallback() {
        return callback;
    }

    public void setLeaveRequestSearchModel(final LeaveRequestSearchModel leaveRequestSearchModel) {
        this.leaveRequestSearchModel = leaveRequestSearchModel;
    }

    public LeaveRequestSearchModel getLeaveRequestSearchModel() {
        return leaveRequestSearchModel;
    }

    public void setCalendar(final Calendar<T> calendar) {
        this.calendar = calendar;
    }

    public Calendar<T> getCalendar() {
        return calendar;
    }

    public void setMatrix(final Matrix<T, DateTime, Assignable> matrix) {
        this.matrix = matrix;
    }

    public Matrix<T, DateTime, Assignable> getMatrix() {
        return matrix;
    }

}