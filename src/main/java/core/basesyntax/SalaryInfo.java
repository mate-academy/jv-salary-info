package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder resultBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate currentDate = LocalDate.parse(splittedLine[DATE_INDEX],
                        FORMATTER);
                if ((currentDate.isAfter(localDateFrom)
                        || currentDate.isEqual(localDateFrom))
                        && (currentDate.isBefore(localDateTo)
                        || currentDate.isEqual(localDateTo))
                        && splittedLine[NAME_INDEX].equals(name)) {
                    salary += (Integer.parseInt(splittedLine[SALARY_INDEX])
                        * Integer.parseInt(splittedLine[WORKING_HOURS_INDEX]));
                }
            }
            resultBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return resultBuilder.toString();
    }
}

