package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int positionOfDate = 0;
    private static final int positionOfName = 1;
    private static final int positionOfHours = 2;
    private static final int positionOfSalary = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] arrayData;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                arrayData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayData[positionOfDate], FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0 && localDateTo
                        .compareTo(currentDate) >= 0 && arrayData[positionOfName].equals(name)) {
                    salary = salary + (Integer.parseInt(arrayData[positionOfSalary])
                            * Integer.parseInt(arrayData[positionOfHours]));
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

}
