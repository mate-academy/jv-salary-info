package core.basesyntax;

public class Calendar {
    private final int day;
    private final int month;
    private final int year;

    public Calendar(String date) {
        this.day = Integer.parseInt(date.substring(0, date.indexOf('.')));
        this.month = Integer.parseInt(date.substring(date.indexOf('.') + 1, date.lastIndexOf('.')));
        this.year = Integer.parseInt(date.substring(date.lastIndexOf('.') + 1,
                date.lastIndexOf('.') + 5));
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static boolean isDataBetweenTwoDates(Calendar yourDate, Calendar firstDate,
                                                Calendar secondDate) {
        if (yourDate.getYear() > firstDate.getYear() && yourDate.getYear()
                < secondDate.getYear()) {
            return true;
        } else if (yourDate.getYear() == firstDate.getYear() || yourDate.getYear()
                == secondDate.getYear()) {
            if ((yourDate.getYear() > firstDate.getYear() || yourDate.getMonth()
                    > firstDate.getMonth())
                    && (yourDate.getMonth() < secondDate.getMonth() || yourDate.getYear()
                    < secondDate.getYear())) {
                return true;
            } else if (yourDate.getMonth() == firstDate.getMonth() || yourDate.getMonth()
                    == secondDate.getMonth()) {
                return (yourDate.getMonth() > firstDate.getMonth() || yourDate.getDay()
                        >= firstDate.getDay())
                        && (yourDate.getDay() <= secondDate.getDay() || yourDate.getMonth()
                        < secondDate.getMonth());
            }
        }
        return false;
    }
}
