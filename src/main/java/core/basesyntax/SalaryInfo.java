package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String SEPARATOR = " - ";
    public static final String SPACE_SEPARATOR = " ";
    public static final int RECORD_DATE_FIRST_ELEMENT = 0;
    public static final int EMPLOYEE_NAME_SECOND_ELEMENT = 1;
    public static final int HOURS_WORKED_THIRD_ELEMENT = 2;
    public static final int HOURLY_RATE_FORTH_ELEMENT = 3;
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder report = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(SEPARATOR).append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] parts = record.split(SPACE_SEPARATOR);
                if (parts.length == 4) {
                    String recordDateStr = parts[RECORD_DATE_FIRST_ELEMENT];
                    String employeeName = parts[EMPLOYEE_NAME_SECOND_ELEMENT];
                    int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_THIRD_ELEMENT]);
                    int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_FORTH_ELEMENT]);

                    if (employeeName.equals(name)) {
                        LocalDate recordDate = LocalDate.parse(recordDateStr, dateFormatter);
                        if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                            totalSalary += hoursWorked * hourlyRate;
                        }
                    }
                }
            }
            report.append(name).append(SEPARATOR).append(totalSalary)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}


