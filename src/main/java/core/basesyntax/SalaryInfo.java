package core.basesyntax;

public class SalaryInfo {
    private static final int DATE_OF_WORKING_DAY = 0;
    private static final int WORKING_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;
    private static final int EMPLOYEE_NAME = 1;
    private static final int MONTH = 1;
    private static final int DAY_OF_MONTH = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            result.append(System.lineSeparator()).append(name).append(" - ");
            for (String dataLine : data) {
                String[] employeeData = dataLine.split(" ");
                if (employeeData[EMPLOYEE_NAME].equals(name)) {
                    if (getDate(dateFrom) < getDate(employeeData[DATE_OF_WORKING_DAY])
                            && getDate(dateTo) >= getDate(employeeData[DATE_OF_WORKING_DAY])) {
                        totalSalary += Integer.parseInt(employeeData[WORKING_HOURS])
                                * Integer.parseInt(employeeData[SALARY_PER_HOUR]);
                    }
                }
            }
            result.append(totalSalary);
        }
        return result.toString();
    }

    public int getDate(String date) {
        String[] dateArray = date.split("\\.");
        return Integer.parseInt(dateArray[MONTH] + dateArray[DAY_OF_MONTH]);
    }
}
