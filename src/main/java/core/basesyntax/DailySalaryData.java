package core.basesyntax;

import java.time.LocalDate;

public class DailySalaryData {
    private int hoursPerDay;
    private int dayIncome;
    private LocalDate calendarDay;

    public DailySalaryData(LocalDate calendarDay, int hoursPerDay, int dayIncome) {
        this.calendarDay = calendarDay;
        this.hoursPerDay = hoursPerDay;
        this.dayIncome = dayIncome;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public int getDayIncome() {
        return dayIncome;
    }

    public LocalDate getCalendarDay() {
        return calendarDay;
    }
}
