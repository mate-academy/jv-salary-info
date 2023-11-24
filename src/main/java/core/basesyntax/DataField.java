package core.basesyntax;

public class DataField {
    private final int workingHour;
    private final int incomePerHour;
    private final String date;
    private final String employeeName;

    public DataField(String date, String employeeName, int workingHour, int incomePerHour) {
        this.workingHour = workingHour;
        this.incomePerHour = incomePerHour;
        this.date = date;
        this.employeeName = employeeName;
    }

    public int getWorkingHour() {
        return workingHour;
    }

    public int getIncomePerHour() {
        return incomePerHour;
    }

    public String getDate() {
        return date;
    }

    public String getEmployeeName() {
        return employeeName;
    }
}
