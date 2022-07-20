package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_WAGE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int userSalary = 0;
            resultBuilder.append(System.lineSeparator()).append(name).append(" - ");
            for (String line : data) {
                String[] arrayDateLine = line.split(" ");
                LocalDate checkedDate = LocalDate.parse(arrayDateLine[INDEX_DATE], FORMATTER);
                if ((checkedDate.isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        || checkedDate.isEqual(LocalDate.parse(dateFrom, FORMATTER)))
                        && (checkedDate.isBefore(LocalDate.parse(dateTo, FORMATTER))
                        || checkedDate.isEqual(LocalDate.parse(dateTo, FORMATTER)))
                        && name.equals(arrayDateLine[INDEX_NAME])) {
                    userSalary = Integer.parseInt(arrayDateLine[INDEX_HOURS])
                            * Integer.parseInt(arrayDateLine[INDEX_WAGE])
                            + userSalary;
                }
            }
            resultBuilder.append(userSalary);
        }
        return resultBuilder.toString();
    }
}
