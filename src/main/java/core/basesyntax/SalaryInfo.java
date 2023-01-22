package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int LOCAL_DAY = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = getDay(dateFrom);
        LocalDate localDateTo = getDay(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String employeeInfo : data) {
                String[] employeeDailyDate = employeeInfo.split(" ");
                LocalDate foundDay = getDay(employeeDailyDate[LOCAL_DAY]);
                if (name.equals(employeeDailyDate[EMPLOYEE_NAME])
                        && !foundDay.isAfter(localDateTo)
                        && !foundDay.isBefore(localDateFrom)) {
                    salary += Integer.parseInt(employeeDailyDate[WORK_HOURS])
                            * Integer.parseInt(employeeDailyDate[SALARY_PER_HOUR]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }

    private LocalDate getDay(String data) {
        return LocalDate.parse(data, DATE_FORMATTER);
    }
}
