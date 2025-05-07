package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int EMPTY_TOTAL_INCOME_VALUE = 0;
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME = 3;
    private static final String DATE_SEPARATOR = " - ";
    private static final String DATE_SPLITTER = " ";
    private static final String REPORT_HEADER = "Report for period ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);
        StringBuilder builder = new StringBuilder(REPORT_HEADER)
                .append(dateFrom)
                .append(DATE_SEPARATOR)
                .append(dateTo);
        for (String currentName : names) {
            int totalIncome = EMPTY_TOTAL_INCOME_VALUE;
            for (String day : data) {
                String[] currentDayData = day.split(DATE_SPLITTER);
                LocalDate currentDate = parseDate(currentDayData[DATE]);
                if (isDateInRange(currentDate, startDate, endDate)
                        && currentName.equals(currentDayData[NAME])) {
                    totalIncome += calculateTotalIncome(currentDayData[HOURS],
                                                        currentDayData[INCOME]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(currentName)
                    .append(DATE_SEPARATOR)
                    .append(totalIncome);
        }
        return builder.toString();
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private boolean isDateInRange(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        return (currentDate.equals(startDate) || currentDate.equals(endDate)
                || (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)));
    }

    private int calculateTotalIncome(String hoursAmount, String incomeAmount) {
        int hours = Integer.parseInt(hoursAmount);
        int income = Integer.parseInt(incomeAmount);
        return hours * income;
    }
}
