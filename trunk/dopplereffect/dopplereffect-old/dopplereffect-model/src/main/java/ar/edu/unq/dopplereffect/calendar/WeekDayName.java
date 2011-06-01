package ar.edu.unq.dopplereffect.calendar;

/**
 * Enum Que contiene el nombre del dia
 */

//@formatter:off
public enum WeekDayName {
    
    Monday("Monday"),     
    Tuesday("Tuesday"),
    Wednesday("Wednesday"), 
    Thursday("Thursday") ,
    Friday("Friday"), 
    Saturday("Saturday"), 
    Sunday("Sunday");
    
  //@formatter:on

    private String name;

    private WeekDayName(final String aName) {
        name = aName;
    }

    public static String valueOf(final int number) {
        return values()[number - 1].name;
    }

    @Override
    public String toString() {
        return name;
    }

}
