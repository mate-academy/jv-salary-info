package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_STRING_NUMBER_IN_DATA = 0;
    private static final int HOURS_STRING_NUMBER_IN_DATA = 2;
    private static final int MONEY_STRING_NUMBER_IN_DATA = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        String separator = System.lineSeparator();
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(separator);
        int[] salaryArr = new int[names.length];
        for (int name = 0; name < names.length; name++) {
            for (int record = 0; record < data.length; record++) {
                if (data[record].contains(names[name])) {
                    LocalDate day = LocalDate.parse((data[record].split(" "))
                            [DATE_STRING_NUMBER_IN_DATA], FORMATTER);
                    if (!day.isBefore(fromDate) && !day.isAfter(toDate)) {
                        salaryArr[name] = salaryArr[name]
                                    + Integer.parseInt(data[record].split(" ")
                                [HOURS_STRING_NUMBER_IN_DATA])
                                    * Integer.parseInt(data[record].split(" ")
                                [MONEY_STRING_NUMBER_IN_DATA]);
                    }
                }
            }
            if (name == names.length - 1) {
                report.append(names[name]).append(" - ").append(salaryArr[name]);
            } else {
                report.append(names[name]).append(" - ")
                        .append(salaryArr[name]).append(separator);
            }
        }
        return report.toString();
    }
}
