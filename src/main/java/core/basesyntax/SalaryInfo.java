package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] splitData = record.split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[DATE_INDEX], FORMATTER);
                String currentName = splitData[NAME_INDEX];
                int hoursWorked = Integer.parseInt(splitData[HOURS_WORKED_INDEX]);
                int incomePerHour = Integer.parseInt(splitData[INCOME_PER_HOUR_INDEX]);

                if (isInDateRangeAndSameName(currentDate, startDate, endDate, currentName, name)) {
                    totalSalary += hoursWorked * incomePerHour;
                }
            }
            report.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(totalSalary);
        }

        return report.toString();
    }

    private boolean isInDateRangeAndSameName(LocalDate currentDate, LocalDate startDate,
                LocalDate endDate, String currentName, String name) {
        return currentName.equals(name)
                && (currentDate.isEqual(startDate)
                || currentDate.isAfter(startDate))
                && (currentDate.isEqual(endDate)
                || currentDate.isBefore(endDate));
    }
}
