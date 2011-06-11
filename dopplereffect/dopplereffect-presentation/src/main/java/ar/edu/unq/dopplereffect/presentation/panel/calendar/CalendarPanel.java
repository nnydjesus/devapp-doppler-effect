package ar.edu.unq.dopplereffect.presentation.panel.calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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
import org.apache.wicket.model.PropertyModel;
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
    private static final String NEXT_METHOD = "next";

    private static final String PREVIOUS_METHOD = "previous";

    private static final String WEEKLY_METHOD = "weekly";

    private static final String MONTHLY_METHOD = "monthly";

    private EmployeeSearchModel employeeService;

    private Calendar calendar;

    private Matrix<Employee, DateTime, Assignable> matrix;

    private MonthStrategy monthStrategy;

    private WeekdayStrategy weekdayStrategy;

    private WebMarkupContainer scrollpane;

    private Date currentDate;

    private Date datePicketModel;

    private Model<String> hastaModel;

    private DatePicker<Date> datePicker;

    private DateFormat dateFormat;

    public CalendarPanel(final String id, final EmployeeSearchModel employeeSearchModel) {
        super(id, new Model<EmployeeSearchModel>(employeeSearchModel));
        employeeService = employeeSearchModel;
        employeeSearchModel.search();
        DateTime day = new DateTime();
        monthStrategy = new MonthStrategy(day);
        weekdayStrategy = new WeekdayStrategy(day);
        calendar = new Calendar(monthStrategy);
        // TODO
        // matrix = calendar.getCalendar(employeeService.getResults());
        scrollpane = new WebMarkupContainer("scrollpane");
        scrollpane.add(new ScrollPaneBehavior());
        this.makePage();
        this.add(scrollpane);
    }

    private void makePage() {
        hastaModel = new Model<String>("");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        this.updateTable();
        this.addComponents();
    }

    protected MarkupContainer updateTable() {
        employeeService.search();
        // TODO
        // matrix = calendar.getCalendar(employeeService.getResults());
        return scrollpane.addOrReplace(this.createTable());
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

        CalendarStrategy strategy = calendar.getStrategy().cloneStrategy();
        currentDate = calendar.getStrategy().getDay().toDate();
        this.setDatePicketModel(currentDate);

        for (int day = 1; day <= calendar.getStrategy().getTotalDays(); day++) {
            final DateTime date = strategy.getDay();

            columns.add(this.createColumn(date));
            strategy.plus();
        }

        hastaModel.setObject(dateFormat.format(strategy.getDay().toDate()));

        return new AjaxFallbackDefaultDataTable<Entry<Employee, Map<DateTime, Assignable>>>("calendarTable", columns,
                new GenericSortableDataProvider<Entry<Employee, Map<DateTime, Assignable>>>("entrySet", matrix),
                SearchModel.PAGE_SIZE);
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
        datePicker = new DatePicker<Date>("datePicker", new PropertyModel<Date>(this, "datePicketModel"), Date.class)
                .setButtonText("<div class=\"ui-icon ui-icon-calendar\"></div>").setShowOn(ShowOnEnum.BOTH)
                .setShowButtonPanel(true);

        this.add(datePicker);

        this.add(new Label("hasta", hastaModel));

        this.add(CustomComponent.addButtonSking(new AjaxReflextionActionPanel<CalendarPanel>("nextMonth", this,
                NEXT_METHOD, "next.png", "")));

        this.add(CustomComponent.addButtonSking(new AjaxReflextionActionPanel<CalendarPanel>("previousMonth", this,
                PREVIOUS_METHOD, "previous.png", "")));

        this.add(CustomComponent.addButtonSking(new ReflextionAjaxLink<CalendarPanel>("mensual", MONTHLY_METHOD, this,
                this, new Model<String>("Monthly"))));

        this.add(CustomComponent.addButtonSking(new ReflextionAjaxLink<CalendarPanel>("semanal", WEEKLY_METHOD, this,
                this, new Model<String>("Weekly"))));
    }

    public void next() {
        calendar.getStrategy().next();
        this.updateTable();
    }

    public void previous() {
        calendar.getStrategy().previous();
        this.updateTable();
    }

    public void weekly() {
        calendar.setStrategy(weekdayStrategy);
        this.updateTable();
    }

    public void monthly() {
        calendar.setStrategy(monthStrategy);
        this.updateTable();
    }

    public void setDatePicketModel(final Date datePicketModel) {
        this.datePicketModel = datePicketModel;
    }

    public Date getDatePicketModel() {
        return datePicketModel;
    }

}