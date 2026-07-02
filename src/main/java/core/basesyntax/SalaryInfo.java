package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo)
                .append(System.lineSeparator());

        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;

            for (String record : data) {
                String[] fragments = record.split(" ");
                if (name.equals(fragments[NAME_INDEX])) {
                    LocalDate recordDate =
                            LocalDate.parse(fragments[0], FORMATTER);

                    if (!recordDate.isBefore(fromDate)
                            && !recordDate.isAfter(toDate)) {
                        int hours = Integer.parseInt(fragments[HOURS_INDEX]);
                        int rate = Integer.parseInt(fragments[RATE_INDEX]);
                        salary += hours * rate;
                    }
                }
            }
            result.append(name).append(" - ").append(salary)
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
