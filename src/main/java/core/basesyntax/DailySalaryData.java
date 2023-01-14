package core.basesyntax;

public class DailySalaryData extends CalendarDay {
    private int hoursPerDay;
    private int dayIncome;

    public DailySalaryData(int[] date, int hoursPerDay, int dayIncome) {
        super(date[0],date[1],date[2]);
        this.hoursPerDay = hoursPerDay;
        this.dayIncome = dayIncome;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public int getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(int dayIncome) {
        this.dayIncome = dayIncome;
    }
}
