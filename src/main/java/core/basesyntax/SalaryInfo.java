package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_OF_WORK_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splitLine = line.split(" ");
                LocalDate currentDate = LocalDate.parse(splitLine[DATE_INDEX], FORMATTER);
                if (splitLine[NAME_INDEX].equals(name)
                        && (currentDate.isAfter(localDateFrom)
                        || currentDate.isEqual(localDateFrom))
                        && (currentDate.isBefore(localDateTo)
                        || currentDate.isEqual(localDateTo))) {
                    salary += (Integer.parseInt(splitLine[INCOME_PER_HOUR_INDEX])
                            * Integer.parseInt(splitLine[HOURS_OF_WORK_INDEX]));
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
