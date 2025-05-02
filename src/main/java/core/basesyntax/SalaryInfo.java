package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_INDEX = 2;
    static final int AMOUNT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder output = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String currentName : names) {
            int salary = 0;
            for (String datum : data) {
                String[] info = datum.split(" ");
                LocalDate date = LocalDate.parse(info[DATE_INDEX], FORMATTER);
                String name = info[NAME_INDEX];
                int hours = Integer.parseInt(info[HOURS_INDEX]);
                int amount = Integer.parseInt(info[AMOUNT_INDEX]);
                if (currentName.equals(name)
                        && !date.isBefore(dateFromFormatted)
                        && !date.isAfter(dateToFormatted)) {
                    salary += hours * amount;
                }
            }
            output.append(System.lineSeparator())
                    .append(currentName)
                    .append(" - ")
                    .append(salary);
        }

        return output.toString();
    }
}
