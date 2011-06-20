package ar.edu.unq.dopplereffect.presentation.panel.calendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.joda.time.DateTime;

import ar.edu.unq.dopplereffect.assignments.Assignable;
import ar.edu.unq.dopplereffect.calendar.CalendarStrategy;
import ar.edu.unq.dopplereffect.calendar.Calendareable;
import ar.edu.unq.dopplereffect.calendar.PrintDay;
import ar.edu.unq.dopplereffect.presentation.panel.AjaxActionPanel;
import ar.edu.unq.dopplereffect.presentation.panel.leaverequest.LeaveRequestSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.SearchModel;
import ar.edu.unq.dopplereffect.presentation.search.leaverequest.LeaveRequestSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.presentation.util.GenericSortableDataProvider;
import ar.edu.unq.dopplereffect.presentation.util.Model;
import ar.edu.unq.dopplereffect.service.employee.EmployeeViewDTO;

public class CalendarTable<T extends Calendareable> implements Serializable {

    private static final long serialVersionUID = -2312382166995179303L;

    /* ************************ INSTANCE VARIABLES ************************ */

    private CalendarPanel<T> calendarPanel;

    /* *************************** CONSTRUCTORS *************************** */

    public CalendarTable(final CalendarPanel<T> calendarPanel) {
        this.setCalendarPanel(calendarPanel);
    }

    /* **************************** ACCESSORS ***************************** */

    public CalendarPanel<T> getCalendarPanel() {
        return calendarPanel;
    }

    public void setCalendarPanel(final CalendarPanel<T> calendarPanel) {
        this.calendarPanel = calendarPanel;
    }

    /* ************************* PRIVATE METHODS ************************** */

    protected AjaxFallbackDefaultDataTable<Entry<T, Map<DateTime, Assignable>>> updateTable() {
        ArrayList<IColumn<Entry<T, Map<DateTime, Assignable>>>> columns = new ArrayList<IColumn<Entry<T, Map<DateTime, Assignable>>>>();

        columns.add(new AbstractColumn<Entry<T, Map<DateTime, Assignable>>>(new Model<String>("Assignable")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<Entry<T, Map<DateTime, Assignable>>>> cellItem,
                    final String componentId, final IModel<Entry<T, Map<DateTime, Assignable>>> cellModel) {
                cellItem.add(new Label(componentId, new Model<T>(cellModel.getObject().getKey())));
            }
        });
        CalendarStrategy strategy = this.getCalendarPanel().getCalendar().getStrategy().cloneStrategy();

        for (int day = 1; day <= this.getCalendarPanel().getCalendar().getStrategy().getTotalDays(); day++) {
            final DateTime date = strategy.getDay();
            columns.add(this.createColumn(date));
            strategy.plus();
        }

        return new AjaxFallbackDefaultDataTable<Entry<T, Map<DateTime, Assignable>>>("calendarTable", columns,
                new GenericSortableDataProvider<Entry<T, Map<DateTime, Assignable>>>("entrySet", this
                        .getCalendarPanel().getMatrix(), "key"), SearchModel.PAGE_SIZE);
    }

    protected AbstractColumn<Entry<T, Map<DateTime, Assignable>>> createColumn(final DateTime date) {
        return new AbstractColumn<Entry<T, Map<DateTime, Assignable>>>(new Model<String>(PrintDay.printDay(date))) {
            private static final long serialVersionUID = 1L;

            @Override
            public void populateItem(final Item<ICellPopulator<Entry<T, Map<DateTime, Assignable>>>> cellItem,
                    final String componentId, final IModel<Entry<T, Map<DateTime, Assignable>>> cellModel) {
                Assignable assignable = cellModel.getObject().getValue().get(date);

                final AjaxActionPanel link = new AjaxActionPanel(componentId, new Model<Assignable>(assignable)) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onAction(final AjaxRequestTarget target) {
                        AjaxCallBack<Component> callBack = CalendarTable.this.getCalendarPanel().getCallback();
                        LeaveRequestSearchModel leaveReqSearchModel = CalendarTable.this.getCalendarPanel()
                                .getLeaveRequestSearchModel();
                        LeaveRequestSearchPanel comp = new LeaveRequestSearchPanel("body", callBack,
                                CalendarTable.this.getCalendarPanel(), leaveReqSearchModel);
                        leaveReqSearchModel.setSearchByEmployee((EmployeeViewDTO) cellModel.getObject().getKey());
                        leaveReqSearchModel.search();
                        callBack.execute(target, comp);
                    }

                };
                cellItem.add(link);
            }
        };
    }

}
