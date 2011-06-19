/**
 * 
 */
package ar.edu.unq.dopplereffect.presentation.theme;

import java.io.Serializable;

import org.apache.wicket.ResourceReference;

/**
 * 
 */
public abstract class UITheme implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    /**
     * Constructor
     */
    public UITheme(final String name) {
        this.name = name;
    }

    public abstract ResourceReference getTheme();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
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
        UITheme other = (UITheme) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
