package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            String regexSymbol = " ";
            for (String datum : data) {
                String[] parts = datum.split(regexSymbol);
                int dateIndex = 0;
                int nameIndex = 1;
                int hoursIndex = 2;
                int incomeIndex = 3;
                int hours = Integer.parseInt(parts[hoursIndex]);
                int income = Integer.parseInt(parts[incomeIndex]);
                if (parts[nameIndex].equals(name)
                        && dateComparator(parts[dateIndex], dateFrom, dateTo)) {
                    salary += hours * income;
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return result.toString();
    }

    private static final boolean dateComparator(String data, String dataFrom, String dataTo) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(("dd.MM.yyyy"));
        LocalDate date = LocalDate.parse(data, format);
        LocalDate dateFrom = LocalDate.parse(dataFrom, format);
        LocalDate dateTo = LocalDate.parse(dataTo, format);
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);

    }
}
