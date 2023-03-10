package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate lastDate = LocalDate.parse(dateTo, formatter);

        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name: names) {
            int totalSalary = 0;
            result.append(System.lineSeparator()).append(name).append(" - ");

            for (int i = 0; i < data.length; i++) {
                String[] arrayElements = data[i].split(" ");
                LocalDate dataDate = LocalDate.parse(arrayElements[0], formatter);
                if (arrayElements[1].equals(name)
                        && (dataDate.isAfter(startDate) || dataDate.isEqual(startDate))
                        && (dataDate.isBefore(lastDate) || dataDate.isEqual(lastDate))) {
                    totalSalary += Integer.valueOf(arrayElements[2])
                            * Integer.valueOf(arrayElements[3]);
                }
            }
            result.append(totalSalary);
        }
        return result.toString();
    }
}
