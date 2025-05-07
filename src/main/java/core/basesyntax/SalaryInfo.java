package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int INDEX_DATE = 0;
    public static final int INDEX_NAME_OF_EMPLOYEE = 1;
    public static final int INDEX_WORKING_HOUR = 2;
    public static final int INDEX_INCOME_PER_HOUR = 3;
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int sumSalary = 0;
            for (String datum : data) {
                String[] elements = datum.split("\\s");
                if (name.equals(elements[INDEX_NAME_OF_EMPLOYEE])
                        && dateIsInRange(dateFrom, dateTo, elements[INDEX_DATE])) {
                    sumSalary += Integer.parseInt(elements[INDEX_WORKING_HOUR])
                            * Integer.parseInt(elements[INDEX_INCOME_PER_HOUR]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(sumSalary);
        }

        return report.toString();
    }

    private boolean dateIsInRange(String dateFrom, String dateTo, String workDay) {
        LocalDate from = getDate(dateFrom);
        LocalDate to = getDate(dateTo);
        LocalDate day = getDate(workDay);
        return (from.equals(day) || to.equals(day)) || (day.isBefore(to) && day.isAfter(from));

    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
