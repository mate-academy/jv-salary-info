package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        if (fromDate != null && toDate != null && names != null && data != null) {
            for (String name : names) {
                int salary = 0;
                for (String dataFromData : data) {
                    String[] dataEach = dataFromData.split(" ");
                    LocalDate dataDate = LocalDate.parse(dataEach[0], formatter);
                    if (dataEach[1].equals(name) && !dataDate.isBefore(fromDate)
                            && !dataDate.isAfter(toDate)) {
                        salary += Integer.parseInt(dataEach[2]) * Integer.parseInt(dataEach[3]);
                    }
                }
                result.append(name).append(" - ").append(salary);
                if (!name.equals(names[names.length - 1])) {
                    result.append(System.lineSeparator());
                }
            }
        }
        return result.toString();
    }
}
