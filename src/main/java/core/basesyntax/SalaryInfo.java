package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        String[] dateArray;
        for (String name : names) {
            int salary = 0;
            for (String dates : data) {
                dateArray = dates.split(" ");
                if (name.equals(dateArray[1])) {
                    LocalDate dateFromData = LocalDate.parse(dateArray[0], FORMATTER);
                    if ((dateFromData.isBefore(localDateTo) && dateFromData.isAfter(localDateFrom))
                            || dateFromData.isEqual(localDateTo)) {
                        salary += Integer.parseInt(dateArray[2]) * Integer.parseInt(dateArray[3]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
