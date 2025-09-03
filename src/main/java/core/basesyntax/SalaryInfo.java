package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int NUMBER_OF_ELEMENTS_IN_INPUT = 4;
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_PRICE = 3;
    private static final DateTimeFormatter DATE_FORMATTER  = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder answer = new StringBuilder();
        answer.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        for ( String name : names) {
            int salary = 0;
            answer.append(System.lineSeparator()).append(name).append(" - ");
            for (int i = 0; i < data.length; i++) {
                String[] inputData = data[i].split(" ");
                LocalDate date = LocalDate.parse(inputData[INDEX_OF_DATE], DATE_FORMATTER);
                if (inputData[INDEX_OF_NAME].equals(name) && ((date.isEqual(from) || date.isAfter(from)) &&
                        (date.isEqual(to) || date.isBefore(to)))) {
                    salary += Integer.parseInt(inputData[INDEX_OF_PRICE]) * Integer.parseInt(inputData[INDEX_OF_HOURS]);

                }
            }
            answer.append(salary);
        }
        return answer.toString();
    }

}
