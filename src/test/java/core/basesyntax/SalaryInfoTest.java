package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate localDateFrom = parseDateFromString(dateFrom);
        LocalDate localDateTo = parseDateFromString(dateTo);

        report.append("Report for period ")
              .append(dateFrom)
              .append(" - ")
              .append(dateTo);
        
        for (String currentName : names) {
            int totalSalary = 0;
            for (String record : data) {
                if (record == null || record.isEmpty()) {
                    continue;
                }

                // Split the record into date, name, hours, and hourly rate
                String[] recordData = record.split(" ");
                LocalDate workDate = parseDateFromString(recordData[0]);
                String employeeName = recordData[1];
                int hoursWorked = parseNumberFromString(recordData[2]);
                int hourlyRate = parseNumberFromString(recordData[3]);

                if ((workDate.isEqual(localDateFrom) || workDate.isAfter(localDateFrom))
                    && (workDate.isEqual(localDateTo) || workDate.isBefore(localDateTo))
                    && currentName.equals(employeeName)) {
                    totalSalary += hoursWorked * hourlyRate;
                }
            }
    
            report.append(System.lineSeparator())
                  .append(currentName)
                  .append(" - ")
                  .append(totalSalary);
        }

        return report.toString();
    }

    private LocalDate parseDateFromString(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse date: " + date);
        }
    }

    private int parseNumberFromString(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse number: " + number);
        }
    }
}
