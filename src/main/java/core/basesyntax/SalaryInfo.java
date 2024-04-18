package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();

        try {
            LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate to = LocalDate.parse(dateTo, FORMATTER);

            report.append("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo);

            for (String name : names) {
                int salary = 0;
                for (String record : data) {
                    String[] recordParts = record.split(" ");
                    LocalDate recordDate = LocalDate.parse(recordParts[DATE_INDEX], FORMATTER);
                    String recordName = recordParts[NAME_INDEX];
                    int hoursWorked = Integer.parseInt(recordParts[HOURS_INDEX]);
                    int hourlyRate = Integer.parseInt(recordParts[RATE_INDEX]);

                    if (name.equals(recordName)
                            && (recordDate.isEqual(from) || recordDate.isAfter(from))
                            && (recordDate.isEqual(to) || recordDate.isBefore(to))) {

                        salary += hoursWorked * hourlyRate;
                    }
                }
                report.append(System.lineSeparator())
                        .append(name)
                        .append(" - ")
                        .append(salary);
            }

        } catch (DateTimeParseException e) {
            return "Invalid date format";
        }

        return report.toString();
    }
}
