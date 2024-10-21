package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder();

  
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);

        report.append("Report for period ")
              .append(dateFrom)
              .append(" - ")
              .append(dateTo);

      
        for (String currentName : names) {
            int salaryAmount = 0;

            for (String record : data) {
                String[] recordParts = record.split(" ");
                LocalDate workDate = LocalDate.parse(recordParts[0], DATE_FORMATTER);
                String employeeName = recordParts[1];
                int hoursWorked = Integer.parseInt(recordParts[2]);
                int hourlyRate = Integer.parseInt(recordParts[3]);

                if (!employeeName.equals(currentName)) {
                    continue;
                }
                if ((workDate.isEqual(localDateFrom) || workDate.isAfter(localDateFrom))
                        && (workDate.isEqual(localDateTo) || workDate.isBefore(localDateTo))) {
                    salaryAmount += hoursWorked * hourlyRate;
                }
            }

            report.append(System.lineSeparator())
                  .append(currentName)
                  .append(" - ")
                  .append(salaryAmount);
        }

        
        return report.toString();
    }
}
