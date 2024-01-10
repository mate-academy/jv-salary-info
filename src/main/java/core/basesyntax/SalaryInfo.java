package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy", Locale.ENGLISH);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryForPeriod = new int[names.length];
        LocalDate beginDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            int employerSalary = 0;
            for (String datum : data) {
                String[] dataFormatted = datum.split(" ");
                LocalDate workingDayDate = LocalDate.parse(dataFormatted[0], DATE_TIME_FORMATTER);
                if (workingDayDate.isAfter(beginDate.minusDays(1))
                        && workingDayDate.isBefore(endDate.plusDays(1))
                        && names[i].equals(dataFormatted[1])) {
                    employerSalary = employerSalary + Integer.parseInt(dataFormatted[2])
                            * Integer.parseInt(dataFormatted[3]);
                }
            }
            salaryForPeriod[i] = employerSalary;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(" - ").append(salaryForPeriod[i]);
            if (i < names.length - 1) {
                stringBuilder.append(System.lineSeparator());
            }
        }

        return stringBuilder.toString();
    }
}
