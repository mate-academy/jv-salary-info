package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final short DATE_INDEX = 0;
    private static final short NAME_INDEX = 1;
    private static final short WORKING_HOURS_INDEX = 2;
    private static final short SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splitLine = line.split(" ");
                LocalDate currentDate = LocalDate.parse(splitLine[DATE_INDEX], DATE_TIME_FORMATTER);
                if ((currentDate.isAfter(localDateFrom) || currentDate.isEqual(localDateFrom))
                    && (currentDate.isBefore(localDateTo) || currentDate.isEqual(localDateTo))
                        && splitLine[NAME_INDEX].equals(name)) {
                    salary += (Integer.parseInt(splitLine[SALARY_PER_HOUR_INDEX])
                            * Integer.parseInt(splitLine[WORKING_HOURS_INDEX]));
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
