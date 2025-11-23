package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {

        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        // 1-й цикл по іменах
        for (String name : names) {
            int salary = 0;

            // 2-й цикл по data (вкладений)
            for (String entry : data) {
                String[] tokens = entry.split(" ");
                LocalDate workDate = LocalDate.parse(tokens[DATE_INDEX], FORMATTER);

                if (!workDate.isBefore(from) && !workDate.isAfter(to)
                        && name.equals(tokens[NAME_INDEX])) {

                    int hours = Integer.parseInt(tokens[HOURS_INDEX]);
                    int rate = Integer.parseInt(tokens[RATE_INDEX]);
                    salary += hours * rate;
                }
            }

            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }

        return report.toString();
    }
}


