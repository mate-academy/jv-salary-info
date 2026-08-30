package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String REPORT = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();
        report.append(REPORT).append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int salary = 0;

            for (String datum : data) {
                String[] dataArray = datum.split(" ");
                LocalDate localDate = LocalDate.parse(dataArray[0], formatter);
                if (!localDate.isBefore(from)
                        && !localDate.isAfter(to)
                        && name.equals(dataArray[1])) {
                    salary += Integer.parseInt(dataArray[2])
                            * Integer.parseInt(dataArray[3]);
                }
            }
            report.append(name).append(" - ").append(salary);
            if (!name.equals(names[names.length - 1])) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}