package ar.edu.unq.dopplereffect.presentation.panel.salaryspec;

import org.apache.wicket.IClusterable;

public class PercentageView implements IClusterable, Comparable<PercentageView> {

    private static final long serialVersionUID = -6263618337010705840L;

    private boolean selected;

    private int value;

    public PercentageView() {
        // x
    }

    public PercentageView(final PercentageView pView) {
        this.setValue(pView.getValue());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    @Override
    public int compareTo(final PercentageView pView) {
        return this.getValue() == pView.getValue() ? 0 : this.getValue() > pView.getValue() ? 1 : -1;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime * 1 + value;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PercentageView other = (PercentageView) obj;
        if (value != other.value) {
            return false;
        }
        return true;
    }
}
