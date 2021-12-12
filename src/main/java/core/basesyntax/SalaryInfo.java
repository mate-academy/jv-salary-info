package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMAT);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            String salaryForName = name + " - " + 0;
            int salary = 0;
            for (String d : data) {
                String[] parts = d.split(" ");
                LocalDate now = LocalDate.parse(parts[0], FORMAT);
                if (name.equals(parts[1])
                        && (now.isAfter(localDateFrom) || now.isEqual(localDateFrom))
                        && (now.isBefore(localDateTo) || now.isEqual(localDateTo))
                ) {
                    salary += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    salaryForName = name + " - " + salary;
                }
            }
            result.append(System.lineSeparator()).append(salaryForName);
        }

        return result.toString();
    }
}
