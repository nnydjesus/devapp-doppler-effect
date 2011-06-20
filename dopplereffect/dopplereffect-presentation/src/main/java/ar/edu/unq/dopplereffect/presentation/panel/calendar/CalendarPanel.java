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
import ar.edu.unq.dopplereffect.presentation.panel.AjaxReflectionActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.presentation.util.ReflectionAjaxLink;

/**
 * Panel que muestra en forma de calendario diferentes tipos de asignaciones.
 */
public class CalendarPanel<T extends Calendareable> extends AbstractCallbackPanel<Model<SearchModel<T>>> {

    private static final long serialVersionUID = 1L;

    // @formatter:off
    private static final String
        NEXT_METHOD = "next",
        PREVIOUS_METHOD = "previous",
        WEEKLY_METHOD = "weekly",
        MONTHLY_METHOD = "monthly";
    // @formatter:on

    /* ************************ INSTANCE VARIABLES ************************ */

    private SearchModel<T> model;

    private Calendar<T> calendar;

    private Matrix<T, DateTime, Assignable> matrix;

    private MonthStrategy monthStrategy;

    private WeekdayStrategy weekdayStrategy;

    private WebMarkupContainer scrollpane;

    private Date datePickerModel;

    private Model<String> hastaModel;

    private DateFormat dateFormat;

    private AjaxCallBack<Component> callback;

    private LeaveRequestSearchModel leaveRequestSearchModel;

    private CalendarTable<T> calendarTable;

    /* *************************** CONSTRUCTORS *************************** */

    public CalendarPanel(final String id, final SearchModel<T> employeeSearchModel,
            final LeaveRequestSearchModel leaveReqSearchModel, final AjaxCallBack<Component> callback) {
        super(id, new Model<SearchModel<T>>(employeeSearchModel));
        model = employeeSearchModel;
        this.setLeaveRequestSearchModel(leaveReqSearchModel);
        this.setCallback(callback);
        employeeSearchModel.search();
        DateTime day = new DateTime();
        this.setMonthStrategy(new MonthStrategy(day));
        this.setWeekdayStrategy(new WeekdayStrategy(day));
        this.setCalendar(new Calendar<T>(this.getMonthStrategy()));
        this.setMatrix(this.getCalendar().getCalendar(model.getResults()));
        this.setScrollpane(new WebMarkupContainer("scrollpane"));
        this.getScrollpane().add(new ScrollPaneBehavior().setShowArrows(true));
        this.setCalendarTable(new CalendarTable<T>(this));
        this.makePage();
        this.add(this.getScrollpane());
    }

    /* **************************** ACCESSORS ***************************** */

    @Override
    public AjaxCallBack<Component> getCallback() {
        return callback;
    }

    @Override
    public void setCallback(final AjaxCallBack<Component> callback) {
        this.callback = callback;
    }

    public LeaveRequestSearchModel getLeaveRequestSearchModel() {
        return leaveRequestSearchModel;
    }

    public void setLeaveRequestSearchModel(final LeaveRequestSearchModel leaveRequestSearchModel) {
        this.leaveRequestSearchModel = leaveRequestSearchModel;
    }

    public Calendar<T> getCalendar() {
        return calendar;
    }

    public void setCalendar(final Calendar<T> calendar) {
        this.calendar = calendar;
    }

    public Matrix<T, DateTime, Assignable> getMatrix() {
        return matrix;
    }

    public void setMatrix(final Matrix<T, DateTime, Assignable> matrix) {
        this.matrix = matrix;
    }

    public Date getDatePickerModel() {
        if (datePickerModel == null) {
            return null;
        }
        return (Date) datePickerModel.clone();
    }

    public void setDatePickerModel(final Date datePickerModel) {
        this.datePickerModel = (Date) datePickerModel.clone();
    }

    public MonthStrategy getMonthStrategy() {
        return monthStrategy;
    }

    public void setMonthStrategy(final MonthStrategy monthStrategy) {
        this.monthStrategy = monthStrategy;
    }

    public WeekdayStrategy getWeekdayStrategy() {
        return weekdayStrategy;
    }

    public void setWeekdayStrategy(final WeekdayStrategy weekdayStrategy) {
        this.weekdayStrategy = weekdayStrategy;
    }

    public WebMarkupContainer getScrollpane() {
        return scrollpane;
    }

    public void setScrollpane(final WebMarkupContainer scrollpane) {
        this.scrollpane = scrollpane;
    }

    public Model<String> getHastaModel() {
        return hastaModel;
    }

    public void setHastaModel(final Model<String> hastaModel) {
        this.hastaModel = hastaModel;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(final DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public CalendarTable<T> getCalendarTable() {
        return calendarTable;
    }

    public void setCalendarTable(final CalendarTable<T> calendarTable) {
        this.calendarTable = calendarTable;
    }

    /* **************************** OPERATIONS **************************** */

    public void next() {
        this.getCalendar().getStrategy().next();
        this.updateTable();
    }

    public void previous() {
        this.getCalendar().getStrategy().previous();
        this.updateTable();
    }

    public void weekly() {
        this.getCalendar().setStrategy(this.getWeekdayStrategy());
        this.updateTable();
    }

    public void monthly() {
        this.getCalendar().setStrategy(this.getMonthStrategy());
        this.updateTable();
    }

    /* ************************* PRIVATE METHODS ************************** */

    private void makePage() {
        this.setHastaModel(new Model<String>(""));
        this.setDateFormat(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()));
        this.updateTable();
        this.addComponents();
    }

    protected void updateTable() {
        model.search();
        this.setMatrix(this.getCalendar().getCalendar(model.getResults()));
        CalendarStrategy strategy = this.getCalendar().getStrategy().cloneStrategy();
        datePickerModel = this.getCalendar().getStrategy().getDay().toDate();
        this.getScrollpane().addOrReplace(this.getCalendarTable().updateTable());
        this.getHastaModel().setObject(this.getDateFormat().format(strategy.getDay().toDate()));
    }

    protected void addComponents() {
        DatePicker<Date> datePicker = new DatePicker<Date>("datePicker", new PropertyModel<Date>(this,
                "datePickerModel"), Date.class).setButtonText("<div class=\"ui-icon ui-icon-calendar\"></div>")
                .setShowOn(ShowOnEnum.BOTH).setShowButtonPanel(true);

        this.add(datePicker);

        this.add(new Label("hasta", this.getHastaModel()));

        this.add(CustomComponent.addButtonSking(new AjaxReflectionActionPanel<CalendarPanel<T>>("nextMonth", this,
                NEXT_METHOD, "next.png")));

        this.add(CustomComponent.addButtonSking(new AjaxReflectionActionPanel<CalendarPanel<T>>("previousMonth", this,
                PREVIOUS_METHOD, "previous.png")));

        this.add(CustomComponent.addButtonSking(new ReflectionAjaxLink<CalendarPanel<T>>("mensual", MONTHLY_METHOD,
                this, this, new StringResourceModel("monthday", new Model<String>("")))));

        this.add(CustomComponent.addButtonSking(new ReflectionAjaxLink<CalendarPanel<T>>("semanal", WEEKLY_METHOD,
                this, this, new StringResourceModel("weekday", new Model<String>("")))));
    }
}