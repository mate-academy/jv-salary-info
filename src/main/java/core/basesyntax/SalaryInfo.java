package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, dateFormat);
        LocalDate endDate = LocalDate.parse(dateTo, dateFormat);
        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            report.append(System.lineSeparator());
            int salary = 0;
            for (String dates : data) {
                String[] parts = dates.split(" ");
                LocalDate localDate = LocalDate.parse(parts[0], dateFormat);
                if ((localDate.isAfter(startDate) || localDate.isEqual(startDate))
                        && (localDate.isEqual(endDate) || localDate.isBefore(endDate))) {
                    if (name.equals(parts[1])) {
                        salary = salary + Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    }
                }
            }
            report.append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
