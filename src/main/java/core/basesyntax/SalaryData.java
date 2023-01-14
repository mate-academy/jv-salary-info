package core.basesyntax;

public abstract class SalaryData {
    private int[] date;
    private int[] hoursPerDay;
    private int[] dayIncome;

    public int[] getDate() {
        return date;
    }

    public void setDate(int[] date) {
        this.date = date;
    }

    public int[] getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int[] hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public int[] getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(int[] dayIncome) {
        this.dayIncome = dayIncome;
    }
}
