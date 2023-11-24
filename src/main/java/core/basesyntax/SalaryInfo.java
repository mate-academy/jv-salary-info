package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        int[] sumOfMoney = new int[names.length];

        for (String dataString : data) {
            String[] dataInfo = dataString.split(" ");
            LocalDate compareToDate = LocalDate.parse(dataInfo[0], FORMATTER);
            if (isAfterOrEqual(localDateFrom, compareToDate)
                    && isBeforeOrEqual(localDateTo, compareToDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (dataInfo[1].equals(names[i])) {
                        sumOfMoney[i] += Integer.parseInt(dataInfo[2])
                                * Integer.parseInt(dataInfo[3]);
                        break;
                    }
                }
            }
        }
        return getMessage(names, sumOfMoney, dateFrom, dateTo);
    }

    private boolean isBeforeOrEqual(LocalDate date, LocalDate compareToDate) {
        if (date == null || compareToDate == null) {
            return false;
        }
        return !compareToDate.isAfter(date);
    }

    private boolean isAfterOrEqual(LocalDate date, LocalDate compareToDate) {
        if (date == null || compareToDate == null) {
            return false;
        }
        return !compareToDate.isBefore(date);
    }

    private String getMessage(String[] names, int[] sumOfMoney, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i])
                            .append(" - ").append(sumOfMoney[i]);
        }
        return builder.toString();
    }
}
