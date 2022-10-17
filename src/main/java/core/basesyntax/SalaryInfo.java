package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String HYPHEN = " - ";
    private static final int INDEX_OF_DATE = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(HYPHEN)
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[INDEX_OF_DATE], FORMATTER);
                if (name.equals(arrayOfData[NAME_INDEX]) && (localDateFrom.equals(currentDate)
                        || localDateFrom.isBefore(currentDate) && (localDateTo.equals(currentDate)
                        || localDateTo.isAfter(currentDate)))) {
                    salary += Integer.parseInt(arrayOfData[WORKING_HOUR_INDEX])
                            * Integer.parseInt(arrayOfData[INCOME_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(HYPHEN).append(salary);
        }
        return result.toString();
    }
}
