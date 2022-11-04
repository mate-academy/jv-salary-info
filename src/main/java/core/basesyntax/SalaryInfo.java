package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ARRAY_DATE = 0;
    private static final int ARRAY_NAMES = 1;
    private static final int ARRAY_HOURS = 2;
    private static final int ARRAY_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] arrayOfData;
                arrayOfData = line.split(" ");
                LocalDate localDateDateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate localDateDateTo = LocalDate.parse(dateTo, FORMATTER);
                LocalDate currentDate = LocalDate.parse(arrayOfData[ARRAY_DATE], FORMATTER);
                if (localDateDateFrom.compareTo(currentDate) <= 0
                        && localDateDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[ARRAY_NAMES].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[ARRAY_HOURS]))
                            * Integer.parseInt(arrayOfData[ARRAY_SALARY_PER_HOUR]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
