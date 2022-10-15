package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
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
                if (name.equals(dataFromFile[ONE])) {
                    LocalDate dateCurrent = LocalDate.parse(dataFromFile[ZERO], FORMATTER);
                    if ((dateFromDate.equals(dateCurrent) || dateFromDate.isBefore(dateCurrent))
                            && (dateToDate.equals(dateCurrent)
                            || dateToDate.isAfter(dateCurrent))) {
                        salary = salary + (Integer.parseInt(dataFromFile[TWO])
                                * Integer.parseInt(dataFromFile[THREE]));
                    }
                }
            }
            result.append(System.lineSeparator()).append(name).append(HYPHEN).append(salary);
        }
        return result.toString();
    }
}
