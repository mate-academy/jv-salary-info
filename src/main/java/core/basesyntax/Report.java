package core.basesyntax;

public class Report {
    private final String date;
    private final String userName;
    private final int hoursAmount;
    private final int salaryPerHour;

    public Report(String date, String userName, int hoursAmount, int salaryPerHour) {
        this.date = date;
        this.userName = userName;
        this.hoursAmount = hoursAmount;
        this.salaryPerHour = salaryPerHour;
    }

    public String getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }

    public int getHoursAmount() {
        return hoursAmount;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }
}
