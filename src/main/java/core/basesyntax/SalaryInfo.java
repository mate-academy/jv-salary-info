package core.basesyntax;

import static java.lang.Integer.parseInt;
import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = parse(dateFrom, formatter);
        LocalDate to = parse(dateTo, formatter);
        for (String name : names) {
            int salary = 0;
            for (String daySalary : data) {
                String[] dataOneDay = daySalary.split(" ");
                LocalDate day = parse(dataOneDay[0], formatter);
                if (name.equals(dataOneDay[1]) && ((day.isAfter(from) || day.equals(from)))
                        && (day.isBefore(to) || day.equals(to))) {
                    salary += parseInt(dataOneDay[2]) * parseInt(dataOneDay[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
