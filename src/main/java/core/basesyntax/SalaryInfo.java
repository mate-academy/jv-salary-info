package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PAY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        for (String datum : data) {
            String[] temp = datum.split(" ");
            if (temp[DATE].length() < 10) {
                temp[DATE] = "0" + temp[DATE];
            }
            LocalDate date = LocalDate.parse(temp[0], FORMATTER);
            if (date.compareTo(startDate) >= 0 && (date.compareTo(endDate) <= 0)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(temp[NAME])) {
                        salaries[i] = salaries[i] + Integer.parseInt(temp[HOURS])
                                * Integer.parseInt(temp[PAY_PER_HOUR]);
                    }
                }
            }
        }
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        report.append(System.lineSeparator());
        for (int j = 0; j < names.length; j++) {
            report.append(names[j]).append(" - ").append(salaries[j])
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
