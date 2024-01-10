package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy", Locale.ENGLISH);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryForPeriod = new int[names.length];
        LocalDate beginDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            int employerSalary = 0;
            for (String datum : data) {
                String[] dataFormatted = datum.split(" ");
                LocalDate workingDayDate = LocalDate.parse(dataFormatted[DATE_INDEX],
                        DATE_TIME_FORMATTER);
                if (!workingDayDate.isAfter(endDate)
                        && !workingDayDate.isBefore(beginDate)
                        && names[i].equals(dataFormatted[NAME_INDEX])) {
                    employerSalary = employerSalary
                            + Integer.parseInt(dataFormatted[WORK_HOURS_INDEX])
                            * Integer.parseInt(dataFormatted[INCOME_PER_HOUR_INDEX]);
                }
            }
            salaryForPeriod[i] = employerSalary;
        }

        StringBuilder stringBuilder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder
                    .append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaryForPeriod[i]);
        }

        return stringBuilder.toString();
    }
}
