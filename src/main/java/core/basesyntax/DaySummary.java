package core.basesyntax;

public class DaySummary {
    private final String date;
    private final String name;
    private final int daySumIncome;

    public DaySummary(String date, String name, int hours, int incomePerHour) {
        this.date = date;
        this.name = name;
        this.daySumIncome = hours * incomePerHour;
    }

    public String getDate() {
        return date;
    }

    public String getEmployee() {
        return name;
    }

    public int getDaySumIncome() {
        return daySumIncome;
    }
}
