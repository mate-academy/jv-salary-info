
package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFrom1 = LocalDate.parse(dateFrom, formatter);
        LocalDate dateTo1 = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder();

        for (String name : names) {
            int salaryCount = 0;
            for (String date : data) {
                String[] splitData = date.split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[0], formatter);
                if (splitData[1].equals(name)
                        && ((currentDate.isAfter(dateFrom1)) || (currentDate.isEqual(dateFrom1)))
                        && (currentDate.isBefore(dateTo1) || currentDate.isEqual(dateTo1))) {
                    salaryCount = salaryCount + Integer.parseInt(splitData[2])
                            * Integer.parseInt(splitData[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salaryCount);
        }
        return "Report for period " + dateFrom + " - " + dateTo + report;
    }
}
