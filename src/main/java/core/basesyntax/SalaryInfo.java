package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salary = sumInfoByName(data, name, localDateFrom, localDateTo);
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    public int sumInfoByName(String[] data, String name,
                             LocalDate localDateFrom, LocalDate localDateTo) {
        int sum = 0;
        for (String string : data) {
            String[] salaryInfo = string.split(" ");
            if (salaryInfo[1].equals(name)
                    && checkPeriod(localDateFrom, localDateTo, salaryInfo[0])) {
                sum += Integer.parseInt(salaryInfo[2]) * Integer.parseInt(salaryInfo[3]);
            }
        }
        return sum;
    }

    private boolean checkPeriod(LocalDate localDateFrom, LocalDate localDateTo, String text) {
        return (LocalDate.parse(text, formatter).isAfter(localDateFrom)
                || LocalDate.parse(text, formatter).equals(localDateFrom))
                && (LocalDate.parse(text, formatter).isBefore(localDateTo)
                || LocalDate.parse(text, formatter).equals(localDateTo));
    }
}
