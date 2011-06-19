package ar.edu.unq.dopplereffect.presentation.util;

import java.io.Serializable;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Objects;

/**
 * Model para cuando queremos usar un T que no es serializable
 */
public class Model<T> implements IModel<T>, Serializable {
    private static final long serialVersionUID = 1L;

    /** Backing object. */
    private T object;

    /**
     * Construct the model without providing an object.
     */
    public Model() {
        super();
    }

    /**
     * Construct the model, setting the given object as the wrapped object.
     * 
     * @param object
     *            The model object proper
     */
    public Model(final T object) {
        this.setObject(object);
    }

    /**
     * Factory methods for Model which uses type inference to make code shorter.
     * Equivalent to <code>new Model<TypeOfObject>()</code>.
     * 
     * @param <T>
     * @param object
     * @return Model that contains <code>object</code>
     */
    public static <T> Model<T> of() {
        return new Model<T>();
    }

    /**
     * @see org.apache.wicket.model.IModel#getObject()
     */
    @Override
    public T getObject() {
        return object;
    }

    @Override
    public void setObject(final T aObject) {
        this.object = aObject;
    }

    public void updateObject(final T aObject) {
        this.object = aObject;
    }

    /**
     * @see org.apache.wicket.model.IDetachable#detach()
     */
    @Override
    public void detach() {
        if (object instanceof IDetachable) {
            ((IDetachable) object).detach();
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Model:classname=[");
        String str = "]";
        sb.append(this.getClass().getName()).append(str);
        sb.append(":object=[").append(object).append(str);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(object);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Model<?>)) {
            return false;
        }
        Model<?> that = (Model<?>) obj;
        return Objects.equal(object, that.object);
    }
}
