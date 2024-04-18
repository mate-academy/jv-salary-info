package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int AMOUNT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder output = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);

        for (String name: names) {
            int salary = 0;
            for (String parsedData: data) {
                String[] info = parsedData.split(" ");
                LocalDate date = LocalDate.parse(info[DATE_INDEX], FORMATTER);
                String nameFromInfo = info[NAME_INDEX];
                int hours = Integer.parseInt(info[HOURS_INDEX]);
                int amount = Integer.parseInt(info[AMOUNT_INDEX]);
                if (name.equals(nameFromInfo) && !date.isBefore(dateFromFormatted)
                        && !date.isAfter(dateToFormatted)) {
                    salary += hours * amount;
                }
            }
            output.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }

        return output.toString();
    }
}
