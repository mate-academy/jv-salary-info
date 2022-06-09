package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_OF_WORK_INDEX = 2;
    private static final int SALARY_FOR_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] arraysOfData = line.split(" ");
                LocalDate current = LocalDate.parse(arraysOfData[DATE_INDEX], FORMATTER);
                if (arraysOfData[NAME_INDEX].equals(name) && (current.isAfter(localDateFrom)
                        || current.isEqual(localDateFrom)) && (current.isBefore(localDateTo)
                        || current.isEqual(localDateTo))) {
                    salary += Integer.parseInt(arraysOfData[SALARY_FOR_HOUR_INDEX])
                            * Integer.parseInt(arraysOfData[HOURS_OF_WORK_INDEX]);
                }
            }
            String lineSeparator = System.lineSeparator();
            result.append(lineSeparator).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
