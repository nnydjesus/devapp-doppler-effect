package ar.edu.unq.dopplereffect.persistence.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

public class EnumUserType<E extends Enum<E>> implements UserType {

    private Class<E> clazz = null;

    protected EnumUserType(final Class<E> clazz) {
        this.clazz = clazz;
    }

    private static final int[] SQL_TYPES = { Types.VARCHAR };

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class returnedClass() {
        return clazz;
    }

    @Override
    public Object nullSafeGet(final ResultSet resultSet, final String[] names, final Object owner)
            throws HibernateException, SQLException {
        String name = resultSet.getString(names[0]);
        E result = null;
        if (!resultSet.wasNull()) {
            result = Enum.valueOf(clazz, name);
        }
        return result;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index)
            throws HibernateException, SQLException {
        if (null == value) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            preparedStatement.setString(index, ((Enum) value).name());
        }
    }

    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner) throws HibernateException {
        return original;
    }

    @Override
    public int hashCode(final Object object) throws HibernateException {
        return object.hashCode();
    }

    @Override
    public boolean equals(final Object object1, final Object object2) throws HibernateException {
        if (object1 == object2) {
            return true;
        }
        if (null == object1 || null == object2) {
            return false;
        }
        return object1.equals(object2);
    }
}