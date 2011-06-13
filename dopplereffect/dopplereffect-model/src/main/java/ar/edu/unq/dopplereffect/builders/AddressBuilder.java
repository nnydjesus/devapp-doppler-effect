package ar.edu.unq.dopplereffect.builders;

import ar.edu.unq.dopplereffect.data.Address;

public class AddressBuilder implements Builder<Address> {

    private String street = "xxx";

    private int number = 123;

    private String city = "XXX";

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
