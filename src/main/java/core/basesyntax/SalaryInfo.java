package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_FROM_LIST = 0;
    private static final int NAME_FROM_LIST = 1;
    private static final int HOURS_FROM_LIST = 2;
    private static final int HOURS_RATE_FROM_LIST = 3;
    private static final String LINE_SEPARATOR = "\\s+";

    public String getSalaryInfo(String[] names, String[] dates, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        try {
            for (int i = 0; i < names.length; i++) {
                int employeeSalary = 0;
                for (String date : dates) {
                    String[] record = date.split(LINE_SEPARATOR);
                    String recordDate = record[DATE_FROM_LIST];
                    String recordName = record[NAME_FROM_LIST];
                    int totalHours = Integer.parseInt(record[HOURS_FROM_LIST]);
                    int salaryPerHour = Integer.parseInt(record[HOURS_RATE_FROM_LIST]);

                    if (recordName.equals(names[i])
                            && isDateInRange(dateFrom, dateTo, recordDate)) {
                        employeeSalary += totalHours * salaryPerHour;
                    }
                }
                builder.append(names[i]).append(" - ").append(employeeSalary)
                        .append(System.lineSeparator());
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            System.out.println("Invalid date format" + e);

        }
        return builder.toString().trim();
    }

    private static boolean isDateInRange(String fromDate, String toDate, String dateFromAList) {

        try {
            LocalDate startDate = LocalDate.parse(fromDate, FORMATTER);
            LocalDate endDate = LocalDate.parse(toDate, FORMATTER);
            LocalDate dateToCheck = LocalDate.parse(dateFromAList, FORMATTER);
            return (!dateToCheck.isBefore(startDate)
                    && !dateToCheck.isAfter(endDate));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format" + e);
            return false;
        }
    }
}
