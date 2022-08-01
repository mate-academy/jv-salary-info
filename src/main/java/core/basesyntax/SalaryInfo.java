package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int DATE_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int WORKING_HOUR_COLUMN = 2;
    private static final int INCOME_PER_HOUR_COLUMN = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator());
            int totalSalary = 0;
            for (String dataRow : data) {
                String[] spitedDataRow = dataRow.split(" ");
                try {
                    LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
                    LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);
                    LocalDate workingDate = LocalDate.parse(spitedDataRow[DATE_COLUMN], formatter);

                    if (name.equals(spitedDataRow[NAME_COLUMN])
                            && isDateBetween(workingDate, parseDateFrom, parseDateTo)) {
                        int hour = Integer.parseInt(spitedDataRow[WORKING_HOUR_COLUMN]);
                        int income = Integer.parseInt(spitedDataRow[INCOME_PER_HOUR_COLUMN]);
                        totalSalary += hour * income;
                    }
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Wrong date", e);
                }
            }
            stringBuilder
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return stringBuilder.toString();
    }

    private boolean isDateBetween(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (date.isAfter(dateFrom)
                || date.isEqual(dateFrom))
                && ((date.isBefore(dateTo)
                || date.isEqual(dateTo)));
    }
}

