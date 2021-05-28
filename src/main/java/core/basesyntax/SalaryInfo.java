package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (String name : names) {
            report.append(name).append(" - ")
                    .append(getIndividualSalary(data, dateFrom, dateTo, name))
                    .append("\n");
        }
        return report.deleteCharAt(report.length() - 1).toString();
    }

    private static int getIndividualSalary(
            String[] data, String dateFrom, String dateTo, String rowName) {
        int salary = 0;
        for (String row : data) {
            String[] dividedRow = row.split(" ");
            LocalDate rowDate =
                    LocalDate.parse(dividedRow[0], DATE_FORMATTER);
            if (rowDate.isAfter(LocalDate.parse(dateFrom, DATE_FORMATTER))
                    && rowDate.isBefore(LocalDate.parse(dateTo, DATE_FORMATTER).plusDays(1))
                    && dividedRow[1].equals(rowName)) {
                salary += Integer.parseInt(dividedRow[2])
                        * Integer.parseInt(dividedRow[3]);
            }
        }
        return salary;
    }
}
