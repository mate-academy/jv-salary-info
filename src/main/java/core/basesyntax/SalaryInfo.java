package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            resultBuilder.append(System.lineSeparator()).append(name).append(" - ");
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate checkedDate = LocalDate.parse(splittedLine[INDEX_OF_DATE], FORMATTER);
                if ((checkedDate.isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        || checkedDate.isEqual(LocalDate.parse(dateFrom, FORMATTER)))
                        && (checkedDate.isBefore(LocalDate.parse(dateTo, FORMATTER))
                        || checkedDate.isEqual(LocalDate.parse(dateTo, FORMATTER)))
                        && name.equals(splittedLine[INDEX_OF_NAME])) {
                    salary = Integer.parseInt(splittedLine[INDEX_OF_HOURS])
                            * Integer.parseInt(splittedLine[INDEX_OF_INCOME_PER_HOUR])
                            + salary;
                }
            }
            resultBuilder.append(salary);
        }
        return resultBuilder.toString();
    }
}
