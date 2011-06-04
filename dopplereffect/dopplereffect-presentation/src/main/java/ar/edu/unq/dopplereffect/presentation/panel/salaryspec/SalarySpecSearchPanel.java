package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import java.util.Arrays;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.salaryspec.SalarySpecSearchModel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;

public class SalarySpecSearchPanel extends AbstractSearchPanel<SalarySpecSearchModel> {

    private static final long serialVersionUID = 4870066995423669454L;

    public SalarySpecSearchPanel(final String id, final AjaxCallBack<Component> parentPage,
            final SalarySpecSearchModel model) {
        super(id, parentPage, model, Arrays.asList("year", "minSalary", "maxSalary", "plan", "level"),
                SalarySpecPanel.class);
    }

    @Override
    protected String getDefaultInputSearchWicketId() {
        return "searchByYear";
    }

    @Override
    protected String getSortName() {
        return "year";
    }

}
