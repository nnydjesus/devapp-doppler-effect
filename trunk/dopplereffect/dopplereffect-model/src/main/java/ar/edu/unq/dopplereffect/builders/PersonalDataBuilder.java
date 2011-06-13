package ar.edu.unq.dopplereffect.builders;

import ar.edu.unq.dopplereffect.data.Address;
import ar.edu.unq.dopplereffect.data.PersonalData;

public class PersonalDataBuilder implements Builder<PersonalData> {

    protected transient String firstName = "name";

    protected transient String phoneNumber = "(54) 11 4321 8765";

    protected transient String email = "name@example.com";

    protected transient Address address = new AddressBuilder().build();

    public PersonalDataBuilder withFirstName(final String theFirstName) {
        firstName = theFirstName;
        return this;
    }

    public PersonalDataBuilder withPhoneNumber(final String thePhoneNumber) {
        phoneNumber = thePhoneNumber;
        return this;
    }

    public PersonalDataBuilder withEmail(final String theEmail) {
        email = theEmail;
        return this;
    }

    public PersonalDataBuilder withAddress(final Address theAddress) {
        address = theAddress;
        return this;
    }

    @Override
    public PersonalData build() {
        return new PersonalData(firstName, phoneNumber, email, address);
    }
}
