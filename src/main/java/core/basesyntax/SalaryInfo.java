package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    private LocalDate getParsedDay(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportFromPeriod = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate localDateFrom = getParsedDay(dateFrom);
        LocalDate localDateTo = getParsedDay(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String employeeInfo : data) {
                String[] employeeSplitInfo = employeeInfo.split(" ");
                LocalDate foundDate = getParsedDay(employeeSplitInfo[DATE_INDEX]);
                if (name.equals(employeeSplitInfo[EMPLOYEE_INDEX])
                        && !foundDate.isAfter(localDateTo)
                        && !foundDate.isBefore(localDateFrom)) {
                    salary += Integer.parseInt(employeeSplitInfo[WORK_HOURS_INDEX])
                            * Integer.parseInt(employeeSplitInfo[SALARY_PER_HOUR_INDEX]);
                }
            }
            reportFromPeriod.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportFromPeriod.toString();
    }
}