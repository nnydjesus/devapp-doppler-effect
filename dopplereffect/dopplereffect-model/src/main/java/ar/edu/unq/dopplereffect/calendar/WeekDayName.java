package ar.edu.unq.dopplereffect.calendar;

/**
 */

//@formatter:off
public enum WeekDayName {
    
    Monday("Lunes"),     
    Tuesday("Martes"),
    Wednesday("Miércoles"), 
    Thursday("Jueves") ,
    Friday("Viernes"), 
    Saturday("Sábado"), 
    Sunday("Domingo");
    
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
