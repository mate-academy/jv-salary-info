package core.basesyntax;

public class CalendarDay {
    private int dayNumber;
    private int month;
    private int year;

    public CalendarDay(int dayNumber, int month, int year) {
        this.dayNumber = dayNumber;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return dayNumber;
    }

    public void setDay(int day) {
        this.dayNumber = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
