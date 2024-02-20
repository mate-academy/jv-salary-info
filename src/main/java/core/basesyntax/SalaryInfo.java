package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(("dd.MM.yyyy"));
    private static final String HYPHEN = " - ";
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder reportStringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(HYPHEN).append(dateTo);
        for (String name : names) {
            int moneyEarned =
                    calculateMoneyEarned(name, data, parsedDateFrom, parsedDateTo);
            reportStringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(HYPHEN)
                    .append(moneyEarned);
        }
        return reportStringBuilder.toString();
    }

    private int calculateMoneyEarned(String name, String[] data,
                                     LocalDate dateFrom, LocalDate dateTo) {
        int moneyEarned = 0;
        for (String personalData : data) {
            String[] splitedData = personalData.split(" ");
            LocalDate date = LocalDate.parse(splitedData[DATE], DATE_TIME_FORMATTER);
            String personName = splitedData[NAME];
            int workingHours = Integer.parseInt(splitedData[WORKING_HOURS]);
            int incomePerHour = Integer.parseInt(splitedData[INCOME_PER_HOUR]);
            if (name.equals(personName) && (date.isAfter(dateFrom) || date.isEqual(dateFrom))
                    && (date.isBefore(dateTo) || date.isEqual(dateTo))) {
                moneyEarned += incomePerHour * workingHours;
            }
        }
        return moneyEarned;
    }
}
