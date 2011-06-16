package ar.edu.unq.dopplereffect.builders.employees;

import ar.edu.unq.dopplereffect.builders.Builder;
import ar.edu.unq.dopplereffect.data.Address;

public class AddressBuilder implements Builder<Address> {

    protected transient String street = "xxx";

    protected transient int number = 123;

    protected transient String city = "XXX";

    public AddressBuilder withStreet(final String theStreet) {
        street = theStreet;
        return this;
    }

    public AddressBuilder withNumber(final int theNumber) {
        number = theNumber;
        return this;
    }

    public AddressBuilder withCity(final String theCity) {
        city = theCity;
        return this;
    }

    @Override
    public Address build() {
        return new Address(street, number, city);
    }
}
