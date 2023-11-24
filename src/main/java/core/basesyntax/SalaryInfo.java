package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final int WORK_DATE_INDEX = 0;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int HOUR_SALARY_INDEX = 3;
    private static final String DATA_SEPARATOR = " ";
    private static final String REPORT_TEXT = "Report for period ";
    private static final String REPORT_TEXT_SEPARATOR = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start = LocalDate.parse(dateFrom, FORMATTER).minusDays(1);
        LocalDate finish = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        int salaryDay = 0;

        StringBuilder result = new StringBuilder(REPORT_TEXT)
                .append(dateFrom)
                .append(REPORT_TEXT_SEPARATOR)
                .append(dateTo);

        for (String name : names) {
            for (String employeeData : data) {
                String[] employeeInfo = employeeData.split(DATA_SEPARATOR);
                LocalDate workDate = LocalDate.parse(employeeInfo[WORK_DATE_INDEX], FORMATTER);

                if (employeeData.contains(name)
                        && workDate.isAfter(start) && workDate.isBefore(finish)) {
                    salaryDay += Integer.parseInt(employeeInfo[WORK_HOURS_INDEX])
                            * Integer.parseInt(employeeInfo[HOUR_SALARY_INDEX]);
                }
            }

            result.append(System.lineSeparator())
                    .append(name)
                    .append(REPORT_TEXT_SEPARATOR)
                    .append(salaryDay);

            salaryDay = 0;
        }

        return result.toString();
    }
}
