package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int WORKING_DAY = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] elements = line.split(" ");
                if (elements[NAME].equals(name)) {
                    LocalDate localDateInLine = LocalDate.parse(elements[WORKING_DAY], dateTimeFormatter);
                    if (localDateInLine.compareTo(localDateFrom) >= 0
                            && localDateInLine.compareTo(localDateTo) <= 0) {
                        salary += Integer.parseInt(elements[INCOME]) * Integer.parseInt(elements[HOURS]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
