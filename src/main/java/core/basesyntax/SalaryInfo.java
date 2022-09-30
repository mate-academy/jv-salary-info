package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String separator = System.lineSeparator();
    private static final SalaryInfo salaryInfo = new SalaryInfo();
    private static final int DATE_INDEX = 0;
    private static final int HOURS_OF_WORK_INDEX = 2;
    private static final int NAME_INDEX = 1;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = salaryInfo.calculateSalary(name, data, dateFrom, dateTo);
            stringBuilder.append(separator)
                         .append(name)
                         .append(" - ")
                         .append(salary);
        }
        return stringBuilder.toString();
    }

    public int calculateSalary(String name, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        int salary = 0;
        for (int i = 0; i < data.length; i++) {
            String[] slrInfo = data[i].split(" ");
            LocalDate slrDate = LocalDate.parse(slrInfo[DATE_INDEX], formatter);
            String employerName = slrInfo[NAME_INDEX];
            int hoursOfWork = Integer.parseInt(slrInfo[HOURS_OF_WORK_INDEX]);
            int salaryPerHour = Integer.parseInt(slrInfo[SALARY_PER_HOUR_INDEX]);
            if (name.equals(employerName)
                    && !slrDate.isAfter(toDate) && !slrDate.isBefore(fromDate)) {
                salary += hoursOfWork * salaryPerHour;
            }
        }
        return salary;
    }
}
