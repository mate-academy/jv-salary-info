package core.basesyntax;

public class EmployeeWorkingTimeData {
    private String workingDay;
    private String name;
    private int ratePerHour;
    private int numberOfHours;

    public EmployeeWorkingTimeData(String[] employeeWorkingTimeData) {
        this.workingDay = employeeWorkingTimeData[0];
        this.name = employeeWorkingTimeData[1];
        this.ratePerHour = Integer.parseInt(employeeWorkingTimeData[2]);
        this.numberOfHours = Integer.parseInt(employeeWorkingTimeData[3]);
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public String getName() {
        return name;
    }

    public int getRatePerHour() {
        return ratePerHour;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    @Override
    public String toString() {
        return "EmployeeWorkingTimeData{"
                + "workingDay='" + workingDay + '\''
                + ", name='" + name + '\''
                + ", ratePerHour=" + ratePerHour
                + ", numberOfHours=" + numberOfHours
                + '}';
    }
}
