package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salary = new StringBuilder();
        LocalDate dateFromGet = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToGet = LocalDate.parse(dateTo, formatter);
        salary.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salaryCounter = 0;
            salary.append(System.lineSeparator())
                    .append(name);
            for (String datum : data) {
                LocalDate date = LocalDate.parse(datum.split(" ")[0], formatter);
                if (datum.split(" ")[1].equals(name)
                        && date.isAfter(dateFromGet)
                        && date.isBefore(dateToGet.plusDays(1))) {
                    salaryCounter = Integer.parseInt(datum.split(" ")[2])
                            * Integer.parseInt(datum.split(" ")[3])
                            + salaryCounter;
                }
            }
            salary.append(" - ")
                    .append(salaryCounter);

        }
        return salary.toString();
    }
}
