package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyy");
    public static final int DATE_POSITION_IN_DATASET = 0;
    public static final int NAME_POSITION_IN_DATASET = 1;
    public static final int WORKING_HOURS_POSITION_IN_DATASET = 2;
    public static final int INCOME_PER_HOUR_POSITION_IN_DATASET = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Fields must be not null");
        }

        LocalDate fromLocalDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toLocalDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int totalUserSalary = 0;
            for (String dataString : data) {
                String[] dataSet = dataString.split(" ");
                if (isDateInTheLimits(fromLocalDate, toLocalDate,
                        LocalDate.parse(dataSet[DATE_POSITION_IN_DATASET], DATE_TIME_FORMATTER))
                        && name.equals(dataSet[NAME_POSITION_IN_DATASET])) {
                    totalUserSalary += Integer.parseInt(dataSet[WORKING_HOURS_POSITION_IN_DATASET])
                            * Integer.parseInt(dataSet[INCOME_PER_HOUR_POSITION_IN_DATASET]);
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
