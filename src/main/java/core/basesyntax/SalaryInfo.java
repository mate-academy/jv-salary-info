package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] splitData = datum.split(" ");
                if (splitData[1].equals(name)
                        && !getDate(splitData[0]).isBefore(getDate(dateFrom))
                        && !getDate(splitData[0]).isAfter(getDate(dateTo))) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }

    public final LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
