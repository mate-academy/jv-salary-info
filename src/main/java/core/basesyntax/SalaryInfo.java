package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private static final String HYPHEN = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(HYPHEN)
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] dataFromFile = line.split(" ");
                LocalDate dateCurrent = LocalDate.parse(dataFromFile[INDEX_OF_DATE], FORMATTER);
                if (name.equals(dataFromFile[INDEX_OF_NAME]) && ((dateFromDate.equals(dateCurrent)
                        || dateFromDate.isBefore(dateCurrent)) && (dateToDate.equals(dateCurrent)
                        || dateToDate.isAfter(dateCurrent)))) {
                    salary = salary + (Integer.parseInt(dataFromFile[INDEX_OF_HOURS])
                                * Integer.parseInt(dataFromFile[INDEX_OF_SALARY]));
                }
            }
            result.append(System.lineSeparator()).append(name).append(HYPHEN).append(salary);
        }
        return result.toString();
    }
}
