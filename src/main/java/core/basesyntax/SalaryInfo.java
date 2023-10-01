package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_WORKING_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        String separator = " - ";
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate particularDay = null;
        int employeeMoneyEarned;

        builder.append(dateFrom).append(separator).append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            employeeMoneyEarned = 0;
            builder.append(name).append(separator);
            for (String currentData : data) {
                particularDay = LocalDate.parse(currentData.substring(0, currentData.indexOf(" ")), DATE_TIME_FORMATTER);
                if (((particularDay.isAfter(formattedDateFrom) || particularDay.isEqual(formattedDateFrom))
                        && (particularDay.isBefore(formattedDateTo) || particularDay.isEqual(formattedDateTo))) && currentData.contains(name)) {
                    String[] segregatedData = currentData.split(" ");
                    employeeMoneyEarned += Integer.parseInt(segregatedData[INDEX_OF_WORKING_HOURS])
                            * Integer.parseInt(segregatedData[INDEX_OF_INCOME_PER_HOUR]);
                }
            }
            builder.append(employeeMoneyEarned).append(System.lineSeparator());
        }
        return builder.substring(0, builder.length() - separator.length()+ 1).toString();
    }
}
