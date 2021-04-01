package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] dataParts = datum.split(" ");
                LocalDate localDate = LocalDate.parse(dataParts[0], DATE_FORMAT);
                if (dataParts[1].equals(name) && (localDate.equals(startDate)
                        || localDate.equals(endDate) || localDate.isAfter(startDate)
                        && localDate.isBefore(endDate))) {
                    salary += Integer.parseInt(dataParts[2]) * Integer.parseInt(dataParts[3]);
                }
            }
            result.append("\n").append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}