package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataElement : data) {
                String[] arrayOfDataElements = dataElement.split(" ");
                LocalDate currentDate
                        = LocalDate.parse(arrayOfDataElements[0], FORMATTER);
                String employerName = arrayOfDataElements[1];
                int workingHours = Integer.parseInt(arrayOfDataElements[2]);
                int incomePerHour = Integer.parseInt(arrayOfDataElements[3]);

                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && employerName.equals(name)) {
                    salary += workingHours * incomePerHour;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
