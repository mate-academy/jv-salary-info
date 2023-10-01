package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;
    private static final String SEPARATOR = " - ";
    private static final String REPORT_BEGINNING = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder(REPORT_BEGINNING);
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate particularDay;
        int employeeMoneyEarned;

        builder.append(dateFrom).append(SEPARATOR).append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            employeeMoneyEarned = 0;
            builder.append(name).append(SEPARATOR);
            for (String currentData : data) {
                particularDay = LocalDate.parse(
                        currentData.substring(0, currentData.indexOf(" ")), DATE_TIME_FORMATTER);
                if (isDateInRange(particularDay, formattedDateFrom, formattedDateTo)
                        && currentData.contains(name)) {
                    String[] segregatedData = currentData.split(" ");
                    employeeMoneyEarned += calculateDaySalary(segregatedData);
                }
            }
            builder.append(employeeMoneyEarned).append(System.lineSeparator());
        }
        int trimmedReportLength = builder.length() - SEPARATOR.length() + 1;
        String builderToString = builder.toString();
        return builderToString.substring(0, trimmedReportLength);
    }

    private boolean isDateInRange(LocalDate particularDay, LocalDate dateFrom, LocalDate dateTo) {
        return (particularDay.isAfter(dateFrom) && (particularDay.isBefore(dateTo))
                || particularDay.isEqual(dateFrom)) || particularDay.isEqual(dateTo);
    }

    private int calculateDaySalary(String[] segregatedData) {
        return Integer.parseInt(segregatedData[INDEX_OF_WORKING_HOURS])
                * Integer.parseInt(segregatedData[INDEX_OF_INCOME_PER_HOUR]);
    }
}
