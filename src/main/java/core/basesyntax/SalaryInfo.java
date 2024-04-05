package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate fromDate = LocalDate.parse(dateFrom, inputFormatter);
        final LocalDate toDate = LocalDate.parse(dateTo, inputFormatter);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(fromDate.format(inputFormatter) + " - " + toDate.format(inputFormatter));
        int[] salaries = new int[names.length];

        for (String info: data) {
            String[] parts = info.split(" ");
            LocalDate recordDay = LocalDate.parse(parts[0], inputFormatter);

            if (recordDay.compareTo(fromDate) >= 0 && recordDay.compareTo(toDate) <= 0) {

                for (int i = 0; i < names.length; i++) {

                    if (names[i].equals(parts[1])) {
                        salaries[i] += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaries[i]);
        }

        return report.toString();
    }
}
