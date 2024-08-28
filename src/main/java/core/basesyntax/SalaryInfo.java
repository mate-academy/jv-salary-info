package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] parts = datum.split(" ");
                int hours = Integer.parseInt(parts[2]);
                int income = Integer.parseInt(parts[3]);
                if (parts[1].equals(name) && dateComparator(parts[0], dateFrom, dateTo)) {
                    salary += hours * income;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return new String(result);
    }

    static final boolean dateComparator(String data, String dataFrom, String dataTo) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(("dd.MM.yyyy"));
        LocalDate date = LocalDate.parse(data, format);
        LocalDate dateFrom = LocalDate.parse(dataFrom, format);
        LocalDate dateTo = LocalDate.parse(dataTo, format);
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);

    }
}
