package core.basesyntax;

public class EmployeeWorkingTimeData {
    private static final int WORKING_DAY_ARRAY_POSITION = 0;
    private static final int EMPLOYEE_NAME_ARRAY_POSITION = 1;
    private static final int RATE_PER_HOUR_ARRAY_POSITION = 2;
    private static final int NUMBER_OF_HOURS_ARRAY_POSITION = 3;
    private String workingDay;
    private String name;
    private int ratePerHour;
    private int numberOfHours;

    public EmployeeWorkingTimeData(String[] employeeWorkingTimeData) {
        this.workingDay = employeeWorkingTimeData[WORKING_DAY_ARRAY_POSITION];
        this.name = employeeWorkingTimeData[EMPLOYEE_NAME_ARRAY_POSITION];
        this.ratePerHour = Integer.parseInt(employeeWorkingTimeData[RATE_PER_HOUR_ARRAY_POSITION]);
        this.numberOfHours = Integer.parseInt(
                employeeWorkingTimeData[NUMBER_OF_HOURS_ARRAY_POSITION]);
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
