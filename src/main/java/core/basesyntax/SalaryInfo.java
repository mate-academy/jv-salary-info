package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        long fromDate = LocalDate.parse(dateFrom, FORMATTER).toEpochDay();
        long toDate = LocalDate.parse(dateTo, FORMATTER).toEpochDay();
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String dat: data) {
                String[] aboutEmployee = dat.split(" ");
                long workDate = LocalDate.parse(aboutEmployee[0], FORMATTER).toEpochDay();
                if (name.equals(aboutEmployee[1]) && (workDate >= fromDate && workDate <= toDate)) {
                    salary += Integer.parseInt(aboutEmployee[3])
                            * Integer.parseInt(aboutEmployee[2]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(salary);
        }
        return reportBuilder.toString();
    }
}
