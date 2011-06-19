package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import org.apache.wicket.Component;

import ar.edu.unq.dopplereffect.presentation.panel.NavigablePanel;
import ar.edu.unq.dopplereffect.presentation.search.AbstractSearchPanel;
import ar.edu.unq.dopplereffect.presentation.util.AjaxCallBack;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestDetailDTO;

public class LeaveRequestDetailPanel extends NavigablePanel<LeaveRequestDetailDTO> {

    private static final long serialVersionUID = 4019981393236356602L;

    public LeaveRequestDetailPanel(final String id, final LeaveRequestDetailDTO model,
            final AjaxCallBack<Component> callback, final AbstractSearchPanel<?> backPanel) {
        super(id, model);
        super.init(callback, backPanel);
    }

}
