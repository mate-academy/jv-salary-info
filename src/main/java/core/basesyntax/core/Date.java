package core.basesyntax.core;

public class Date {
    private static final int DAY_START_INDEX = 0;
    private static final int DAY_END_INDEX = 2;
    private static final int MONTH_START_INDEX = 3;
    private static final int MONTH_END_INDEX = 5;
    private static final int YEAR_START_INDEX = 6;

    private String day;
    private String month;
    private String year;

    public Date(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String dateAsString) {
        this.day = dateAsString.substring(DAY_START_INDEX, DAY_END_INDEX);
        this.month = dateAsString.substring(MONTH_START_INDEX, MONTH_END_INDEX);
        this.year = dateAsString.substring(YEAR_START_INDEX);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getAsIntegerForEquals() {
        return Integer.parseInt(year + month + day);
    }

    @Override
    public String toString() {
        return day + "." + month + "." + year;
    }
}
