package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name :
                names) {
            int salaryCounter = 0;
            for (String line :
                    data) {
                String [] arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[0], DATE_FORMATTER);
                if (arrayOfData[1].equals(name)
                        && ((currentDate.isAfter(firstDate)) || (currentDate.isEqual(firstDate)))
                        && (currentDate.isBefore(lastDate) || currentDate.isEqual(lastDate))) {
                    salaryCounter = salaryCounter
                            + Integer.parseInt(arrayOfData[2])
                                    * Integer.parseInt(arrayOfData[3]);
                }
            }
            report.append(name)
                    .append(" - ")
                    .append(salaryCounter)
                    .append(System.lineSeparator());
        }
        return report.toString();
    }
}
