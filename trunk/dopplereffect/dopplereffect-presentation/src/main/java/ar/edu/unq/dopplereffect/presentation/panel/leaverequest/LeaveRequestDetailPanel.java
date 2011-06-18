package ar.edu.unq.dopplereffect.presentation.panel.leaverequest;

import java.io.Serializable;

import ar.edu.unq.dopplereffect.presentation.panel.NavigablePanel;
import ar.edu.unq.dopplereffect.presentation.panel.utils.AbstractCallbackPanel;
import ar.edu.unq.dopplereffect.service.leaverequest.LeaveRequestDetailDTO;

public class LeaveRequestDetailPanel extends NavigablePanel<LeaveRequestDetailDTO> {

    private static final long serialVersionUID = 4019981393236356602L;

    public LeaveRequestDetailPanel(final String id, final LeaveRequestDetailDTO model,
            final AbstractCallbackPanel<? extends Serializable> previousPage) {
        super(id, model, previousPage);
    }

}
