package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATA_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataLine : data) {
                String[] toFields = dataLine.split(" ");
                if (toFields[1].equals(name) && matchesPeriod(toFields[0], dateFrom, dateTo)) {
                    salary += Integer.parseInt(toFields[2]) * Integer.parseInt(toFields[3]);
                }
            }

            result.append("\n").append(name).append(" - ").append(salary);
        }

        return result.toString();
    }

    private LocalDate toDate(String date) {
        return LocalDate.parse(date, DATA_FORMATTER);
    }

    private boolean matchesPeriod(String checkDate, String dateFrom, String dateTo) {
        return toDate(checkDate).isAfter(toDate(dateFrom))
                && toDate(checkDate).isBefore(toDate(dateTo))
                || toDate(checkDate).isEqual(toDate(dateFrom))
                || toDate(checkDate).isEqual(toDate(dateTo));
    }
}