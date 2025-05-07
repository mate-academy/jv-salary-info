package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int START_WORK = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_OF_WORK = 2;
    private static final int SALARY_FOR_HOUR = 3;
    private static final String SEPARATOR = System.lineSeparator();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        String[] arrayOfData;

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                arrayOfData = line.split(" ");
                LocalDate current = LocalDate.parse(arrayOfData[START_WORK], FORMATTER);
                if (arrayOfData[NAME_INDEX].equals(name) && (current.isAfter(localDateFrom)
                        || current.isEqual(localDateFrom)) && (current.isBefore(localDateTo)
                        || current.isEqual(localDateTo))) {
                    salary += Integer.parseInt(arrayOfData[SALARY_FOR_HOUR])
                            * Integer.parseInt(arrayOfData[HOURS_OF_WORK]);
                }
            }
            result.append(SEPARATOR).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
