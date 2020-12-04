package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder workersSalary = new StringBuilder();
        workersSalary.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (String name : names) {
            workersSalary.append(name).append(" - ");
            int salary = 0;
            for (String payday : data) {
                String [] localeData = payday.split(" ");
                if (name.equals(localeData[1])) {
                    LocalDate date = LocalDate.parse(localeData[0], FORMATTER);
                    if ((date.isAfter(fromDate) && date.isBefore(toDate)) || date.equals(fromDate)
                            || date.equals(toDate)) {
                        salary += Integer.parseInt(localeData[2]) * Integer.parseInt(localeData[3]);
                    }
                }
            }
            workersSalary.append(salary).append("\n");
        }
        return (workersSalary.toString().trim());
    }
}
