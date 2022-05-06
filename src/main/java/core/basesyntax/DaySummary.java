package core.basesyntax;

public class DaySummary {
    private final String date;
    private final String name;
    private final int hours;
    private final int incomePerHour;
    private final int daySumIncome;

    public DaySummary(String data) {
        this.date = data.split(" ")[0];
        this.name = data.split(" ")[1];
        this.hours = Integer.valueOf(data.split(" ")[2]);
        this.incomePerHour = Integer.valueOf(data.split(" ")[3]);
        this.daySumIncome = this.hours * this.incomePerHour;
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
