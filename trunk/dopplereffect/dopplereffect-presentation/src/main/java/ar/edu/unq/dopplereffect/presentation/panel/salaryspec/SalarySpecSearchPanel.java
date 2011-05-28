package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import java.util.Arrays;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.App;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.search.salaryspec.SalarySpecSearch;
import ar.edu.unq.dopplereffect.presentation.util.CallBack;

public class SalarySpecSearchPanel extends AbstractSearchPanel<SalarySpecSearch> {

    private static final long serialVersionUID = 4870066995423669454L;

    public SalarySpecSearchPanel(final String id, final CallBack<Component> parentPage) {
        super(id, parentPage, App.salarySpecSearch, Arrays.asList("year", "minSalary", "maxSalary"),
                SalarySpecPanel.class);
    }

    @Override
    protected String getDefaultInputSearchWicketId() {
        return "searchByYear";
    }

}
