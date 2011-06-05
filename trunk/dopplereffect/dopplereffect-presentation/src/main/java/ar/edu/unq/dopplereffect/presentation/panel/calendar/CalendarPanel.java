package ar.edu.unq.dopplereffect.presentation.panel.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.joda.time.DateTime;
import org.odlabs.wiquery.ui.datepicker.DatePicker;
import org.odlabs.wiquery.ui.datepicker.DatePicker.ShowOnEnum;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.calendar.Calendar;
import ar.edu.unq.dopplereffect.calendar.CalendarStrategy;
import ar.edu.unq.dopplereffect.calendar.Matrix;
import ar.edu.unq.dopplereffect.calendar.MonthStrategy;
import ar.edu.unq.dopplereffect.calendar.PrintDay;
import ar.edu.unq.dopplereffect.calendar.WeekdayStrategy;
import ar.edu.unq.dopplereffect.employees.Employee;
import ar.edu.unq.dopplereffect.presentation.components.CustomComponent;
import ar.edu.unq.dopplereffect.presentation.employee.EmployeeSearchModel;
import ar.edu.unq.dopplereffect.presentation.jquery.scroolpane.ScrollPaneBehavior;
import ar.edu.unq.dopplereffect.presentation.panel.AjaxReflextionActionPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.util.GenericSortableDataProvider;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.presentation.util.ReflextionAjaxLink;

public class CalendarPanel extends Panel {
    private static final long serialVersionUID = 1L;

    // El ponerle _ACTION es para que PMD no chille
    private static final String NEXT_ACTION = "next";

    private static final String PREVIOUS_ACTION = "next";

    private static final String WEEKLY_ACTION = "weekly";

    private static final String MONTHLY_ACTION = "monthly";

    private EmployeeSearchModel employeeService;

    private Calendar calendar;

    private Matrix<Employee, DateTime, Assignable> matrix;

    private MonthStrategy monthStrategy;

    private WeekdayStrategy weekdayStrategy;

    private WebMarkupContainer scrollpane;

    private Date currentDate;

    private Model<Date> datePicketModel;

    private Model<String> hastaModel;

    private DatePicker<Date> datePicker;

    private DateFormat dateFormat;

    public CalendarPanel(final String id, final EmployeeSearchModel employeeSearchModel) {
        super(id, new Model<EmployeeSearchModel>(employeeSearchModel));
        employeeService = employeeSearchModel;
        employeeSearchModel.search();
        DateTime day = new DateTime();
        this.setMonthStrategy(new MonthStrategy(day));
        this.setWeekdayStrategy(new WeekdayStrategy(day));
        calendar = new Calendar(this.getMonthStrategy());
        this.setMatrix(calendar.getCalendar(employeeService.getResults()));
        this.setScrollpane(new WebMarkupContainer("scrollpane"));
        this.getScrollpane().add(new ScrollPaneBehavior());
        this.makePage();
        this.add(this.getScrollpane());
    }

    private void makePage() {
        this.setDatePicketModel(new Model<Date>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void setObject(final Date object) {
                super.setObject(object);
                CalendarPanel.this.getCalendar().getStrategy().setDay(new DateTime(object));
                CalendarPanel.this.updateTable();
            }

        });

        this.setHastaModel(new Model<String>(""));
        this.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        this.updateTable();
        this.addComponents();
    }

    protected MarkupContainer updateTable() {
        this.getEmployeeService().search();
        this.setMatrix(this.getCalendar().getCalendar(this.getEmployeeService().getResults()));
        return this.getScrollpane().addOrReplace(this.createTable());
    }

    private AjaxFallbackDefaultDataTable<Entry<Employee, Map<DateTime, Assignable>>> createTable() {
        ArrayList<IColumn<Entry<Employee, Map<DateTime, Assignable>>>> columns = new ArrayList<IColumn<Entry<Employee, Map<DateTime, Assignable>>>>();

        columns.add(new AbstractColumn<Entry<Employee, Map<DateTime, Assignable>>>(new Model<String>("Assignable")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<Entry<Employee, Map<DateTime, Assignable>>>> cellItem,
                    final String componentId, final IModel<Entry<Employee, Map<DateTime, Assignable>>> model) {
                cellItem.add(new Label(componentId, new Model<Employee>(model.getObject().getKey())));
            }
        });

        CalendarStrategy strategy = this.getCalendar().getStrategy().cloneStrategy();
        this.setCurrentDate(this.getCalendar().getStrategy().getDay().toDate());
        this.getDatePicketModel().updateObject(this.getCurrentDate());

        for (int day = 1; day <= CalendarPanel.this.getCalendar().getStrategy().getTotalDays(); day++) {
            final DateTime date = strategy.getDay();

            columns.add(this.createColumn(date));
            strategy.plus();
        }

        this.getHastaModel().setObject(this.getDateFormat().format(strategy.getDay().toDate()));

        return new AjaxFallbackDefaultDataTable<Entry<Employee, Map<DateTime, Assignable>>>("calendarTable", columns,
                new GenericSortableDataProvider<Entry<Employee, Map<DateTime, Assignable>>>("entrySet",
                        this.getMatrix()), SearchModel.PAGE_SIZE);
    }

    protected AbstractColumn<Entry<Employee, Map<DateTime, Assignable>>> createColumn(final DateTime date) {
        return new AbstractColumn<Entry<Employee, Map<DateTime, Assignable>>>(
                new Model<String>(PrintDay.printDay(date))) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<Entry<Employee, Map<DateTime, Assignable>>>> cellItem,
                    final String componentId, final IModel<Entry<Employee, Map<DateTime, Assignable>>> model) {
                final Label label = new Label(componentId,
                        new Model<Assignable>(model.getObject().getValue().get(date)));
                cellItem.add(label);
            }
        };
    }

    protected void addComponents() {
        this.setDatePicker(new DatePicker<Date>("datePicker", this.getDatePicketModel(), Date.class)
                .setButtonText("<div class=\"ui-icon ui-icon-calendar\"></div>").setShowOn(ShowOnEnum.BOTH)
                .setShowButtonPanel(true));

        this.add(this.getDatePicker());

        this.add(new Label("hasta", this.getHastaModel()));

        this.add(CustomComponent.addButtonSking(new AjaxReflextionActionPanel<CalendarPanel>("nextMonth", this,
                NEXT_ACTION, "next.png", "")));

        this.add(CustomComponent.addButtonSking(new AjaxReflextionActionPanel<CalendarPanel>("previousMonth", this,
                PREVIOUS_ACTION, "previous.png", "")));

        this.add(CustomComponent.addButtonSking(new ReflextionAjaxLink<CalendarPanel>("mensual", MONTHLY_ACTION, this,
                this, new Model<String>("Monthly"))));
        this.add(CustomComponent.addButtonSking(new ReflextionAjaxLink<CalendarPanel>("semanal", WEEKLY_ACTION, this,
                this, new Model<String>("Weekly"))));
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
        this.getCalendar().setStrategy(CalendarPanel.this.getWeekdayStrategy());
        this.updateTable();
    }

    public void monthly() {
        this.getCalendar().setStrategy(CalendarPanel.this.getMonthStrategy());
        this.updateTable();
    }

    // setters&getters
    public void setEmployeeService(final EmployeeSearchModel employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeSearchModel getEmployeeService() {
        return employeeService;
    }

    public void setCalendar(final Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setMatrix(final Matrix<Employee, DateTime, Assignable> matrix) {
        this.matrix = matrix;
    }

    public Matrix<Employee, DateTime, Assignable> getMatrix() {
        return matrix;
    }

    public void setMonthStrategy(final MonthStrategy monthStrategy) {
        this.monthStrategy = monthStrategy;
    }

    public MonthStrategy getMonthStrategy() {
        return monthStrategy;
    }

    public void setWeekdayStrategy(final WeekdayStrategy weekdayStrategy) {
        this.weekdayStrategy = weekdayStrategy;
    }

    public WeekdayStrategy getWeekdayStrategy() {
        return weekdayStrategy;
    }

    public void setScrollpane(final WebMarkupContainer scrollpane) {
        this.scrollpane = scrollpane;
    }

    public WebMarkupContainer getScrollpane() {
        return scrollpane;
    }

    public void setCurrentDate(final Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setDatePicketModel(final Model<Date> datePicketModel) {
        this.datePicketModel = datePicketModel;
    }

    public Model<Date> getDatePicketModel() {
        return datePicketModel;
    }

    public void setHastaModel(final Model<String> hastaModel) {
        this.hastaModel = hastaModel;
    }

    public Model<String> getHastaModel() {
        return hastaModel;
    }

    public void setDatePicker(final DatePicker<Date> datePicker) {
        this.datePicker = datePicker;
    }

    public DatePicker<Date> getDatePicker() {
        return datePicker;
    }

    public void setDateFormat(final DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }
}