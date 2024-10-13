package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            for (String line: data) {
                String[] dataParts = line.split(" ");
                LocalDate localDate = LocalDate.parse(dataParts[0], formatter);
                if ((localDate.isAfter(localDateFrom) || localDate.equals(localDateFrom))
                        && (localDate.isBefore(localDateTo)
                        || localDate.equals(localDateTo)) && dataParts[1].equals(name)) {
                    salary += Integer.parseInt(dataParts[2])
                            * Integer.parseInt(dataParts[3]);

                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ")
                    .append(salary).toString();
        }
        return report.toString();
    }
}
