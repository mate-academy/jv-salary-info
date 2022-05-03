package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        long fromDate = LocalDate.parse(dateFrom, formatter).toEpochDay();
        long toDate = LocalDate.parse(dateTo, formatter).toEpochDay();
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String dat: data) {
                String[] aboutEmployee = dat.split(" ");
                long workDate = LocalDate.parse(aboutEmployee[0], formatter).toEpochDay();
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
