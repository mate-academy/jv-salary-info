package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);
        StringBuilder salaryInfoBuilder = new StringBuilder();
        salaryInfoBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] recordSplitted = record.split(" ");
                LocalDate dataDate = parseDate(recordSplitted[0]);
                if (name.equals(recordSplitted[1])
                        && dataDate.compareTo(fromDate) >= 0
                        && dataDate.compareTo(toDate) <= 0) {
                    int currentSalary = Integer.parseInt(recordSplitted[2])
                            * Integer.parseInt(recordSplitted[3]);
                    salary += currentSalary;
                }
            }
            salaryInfoBuilder.append(System.lineSeparator())
                    .append(name).append(" - ").append(salary);
        }

        return salaryInfoBuilder.toString();
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}
