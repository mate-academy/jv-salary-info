package core.basesyntax;

public class Report {
    private final String date;
    private final String name;
    private final int hoursCount;
    private final int salaryPerHour;

    public Report(String report) {
        String[] splitReportString = report.split(" ");
        this.date = splitReportString[0];
        this.name = splitReportString[1];
        this.hoursCount = Integer.parseInt(splitReportString[2]);
        this.salaryPerHour = Integer.parseInt(splitReportString[3]);
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getHoursCount() {
        return hoursCount;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }
}
