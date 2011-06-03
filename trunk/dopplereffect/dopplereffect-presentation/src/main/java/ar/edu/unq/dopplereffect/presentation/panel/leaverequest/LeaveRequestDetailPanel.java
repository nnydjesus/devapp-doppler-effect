package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.io.Serializable;

import ar.edu.unq.dopplereffect.leaverequests.LeaveRequest;
import ar.edu.unq.dopplereffect.presentation.pages.basic.NavigablePanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;

public class LeaveRequestDetailPanel extends NavigablePanel<LeaveRequest> {

    private static final long serialVersionUID = 4019981393236356602L;

    public LeaveRequestDetailPanel(final String id, final LeaveRequest model,
            final AbstractCallbackPanel<? extends Serializable> previousPage) {
        super(id, model, previousPage);
        // TODO
    }

}
