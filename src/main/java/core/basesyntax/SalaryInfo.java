package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_RATE = 3;
    private static final String BEGIN_OF_REPORT = "Report for period ";
    private static final String DASH_FOR_REPORT = " - ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        int[] salaries = new int[names.length];

        for (String line : data) {
            String[] current = line.split(" ");
            LocalDate date = LocalDate.parse(current[INDEX_OF_DATE], FORMATTER);
            String name = current[INDEX_OF_NAME];

            if (!date.isBefore(from) && !date.isAfter(to)) {
                int hours = Integer.parseInt(current[INDEX_OF_HOURS]);
                int rate = Integer.parseInt(current[INDEX_OF_RATE]);
                int salaryPerDay = hours * rate;

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += salaryPerDay;
                        break;
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder(BEGIN_OF_REPORT);
        builder.append(dateFrom)
                .append(DASH_FOR_REPORT)
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(DASH_FOR_REPORT)
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
