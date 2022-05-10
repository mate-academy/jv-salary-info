package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int FIRST_VALUE = 0;
    private static final int PENULTIMATE_VALUE = 2;
    private static final int LAST_VALUE = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] dataArray;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                dataArray = line.split(" ");
                LocalDate currentDate = LocalDate.parse(dataArray[FIRST_VALUE], FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && dataArray[1].equals(name)) {
                    salary += Integer.parseInt(dataArray[LAST_VALUE])
                            * Integer.parseInt(dataArray[PENULTIMATE_VALUE]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
