package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0 || data.length == 0 || dateFrom.isEmpty() || dateTo.isEmpty()) {
            return "";
        }
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
        LocalDate toDate = LocalDate.parse(dateTo, formatter).plusDays(1);
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] rowData = data[i].split(" ");
                LocalDate date = LocalDate.parse(rowData[0], formatter);
                if (name.equals(rowData[1]) && date.isAfter(fromDate) && date.isBefore(toDate)) {
                    salary += Integer.parseInt(rowData[2]) * Integer.parseInt(rowData[3]);
                }
            }
            report.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
