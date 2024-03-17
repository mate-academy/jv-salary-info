package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int MULTIPLIER_INDEX = 2;
    static final int AMOUNT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        if (names == null && data == null) {
            return null;
        }

        for (String searchedName : names) {
            int salary = 0;

            for (String tempDate : data) {
                String[] dividedData = tempDate.split(" ");
                LocalDate date = LocalDate.parse(dividedData[DATE_INDEX], FORMATTER);
                String name = dividedData[NAME_INDEX];
                int multiplier = Integer.parseInt(dividedData[MULTIPLIER_INDEX]);
                int amount = Integer.parseInt(dividedData[AMOUNT_INDEX]);

                if (searchedName.equals(name)
                        && !date.isBefore(dateFromFormatted)
                        && !date.isAfter(dateToFormatted)) {
                    int currentSalary = multiplier * amount;
                    salary = currentSalary + salary;
                }
            }
            builder.append(System.lineSeparator())
                    .append(searchedName)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
