package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int salaryAmount = 0;
            for (String record : data) {
                String[] salaryInfo = record.split(" ");
                if (name.equals(extractName(salaryInfo))
                        && isDateInRange(extractDate(salaryInfo), fromDate, toDate)) {
                    salaryAmount += calculateSalary(salaryInfo[HOURS_INDEX],
                            salaryInfo[RATE_INDEX]);
                }
            }
            builder.append(name).append(" - ").append(salaryAmount)
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private String extractName(String[] parseData) {
        return parseData[NAME_INDEX].trim();
    }

    private LocalDate extractDate(String[] parseData) {
        return LocalDate.parse(parseData[DATE_INDEX].trim(), DATE_TIME_FORMATTER);
    }

    private boolean isDateInRange(LocalDate targetDate, LocalDate fromDate, LocalDate toDate) {
        return !targetDate.isBefore(fromDate) && !targetDate.isAfter(toDate);
    }

    private int calculateSalary(String hoursStr, String rateStr) {
        return Integer.parseInt(hoursStr) * Integer.parseInt(rateStr);
    }
}
