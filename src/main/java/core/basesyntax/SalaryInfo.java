package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyy");
    public static final int DATE_PART_LENGTH = 10;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Fields must be not null");
        }

        LocalDate fromLocalDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toLocalDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalUserSalary = 0;
            for (String dataString : data) {
                String[] dataSet = dataString.split(" ");
                if (isDateInTheLimits(fromLocalDate, toLocalDate,
                        LocalDate.parse(dataSet[0], DATE_TIME_FORMATTER))
                        && name.equals(dataSet[1])) {
                    totalUserSalary += Integer.parseInt(dataSet[2]) * Integer.parseInt(dataSet[3]);
                }
            }
            addSalaryRecord(stringBuilder, name, totalUserSalary);
        }
        return stringBuilder.toString();
    }

    private void addSalaryRecord(StringBuilder builder, String name, int salary) {
        builder.append(System.lineSeparator())
                .append(name)
                .append(" - ")
                .append(salary);
    }

    private boolean isDateInTheLimits(LocalDate fromDate, LocalDate toDate, LocalDate checkedDate) {
        return checkedDate.isEqual(fromDate) || checkedDate.isEqual(toDate)
                || checkedDate.isAfter(fromDate) && checkedDate.isBefore(toDate);
    }
}
