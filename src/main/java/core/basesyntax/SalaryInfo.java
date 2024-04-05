package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate fromDate = LocalDate.parse(dateFrom, inputFormatter);
        final LocalDate toDate = LocalDate.parse(dateTo, inputFormatter);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(fromDate.format(inputFormatter))
                .append(" - ")
                .append(toDate.format(inputFormatter));
        int[] salaries = new int[names.length];

        for (String info : data) {
            String[] parts = info.split(" ");
            LocalDate recordDay = LocalDate.parse(parts[DATE], inputFormatter);

            if ((recordDay.isAfter(fromDate) || recordDay.isEqual(fromDate))
                    && (recordDay.isBefore(toDate) || recordDay.isEqual(toDate))) {
                for (int i = 0; i < names.length; i++) {

                    if (names[i].equals(parts[NAME])) {
                        salaries[i] += Integer.parseInt(parts[HOURS])
                                * Integer.parseInt(parts[INCOME]);
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return report.toString();
    }
}
