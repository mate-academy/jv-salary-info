package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder finalInfo = new StringBuilder(
                String.format("Report for period %s - %s\r\n", dateFrom, dateTo));

        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMAT);

        for (int i = 0; i < names.length; i++) {
            int salaryInfo = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    salaryInfo += parseLine(data[j], names[i], from, to);
                }
            }
            finalInfo.append(names[i]).append(" - ").append(salaryInfo).append(System.lineSeparator());
        }
        finalInfo.delete(finalInfo.lastIndexOf(System.lineSeparator()), finalInfo.length());

        return finalInfo.toString();
    }

    private int parseLine(String line, String name, LocalDate dateFrom, LocalDate dateTo) {
        LocalDate date = LocalDate.parse(line.substring(0, 10), DATE_FORMAT);
        if (date.compareTo(dateFrom) >= 0 && date.compareTo(dateTo) <= 0) {
            String salary = line.substring(11 + name.length()).trim();
            int hours = Integer.parseInt(salary.substring(0, salary.indexOf(" ")));
            int hourCost = Integer.parseInt(salary.substring(salary.indexOf(" ") + 1));
            return hours * hourCost;
        }
        return 0;
    }
}
