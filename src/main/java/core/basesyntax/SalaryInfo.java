package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DELIMITER = " ";
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_SALARY_PER_HOUR = 3;
    private static final String DELIMITER_FOR_BUILDER = " - ";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + DELIMITER_FOR_BUILDER + dateTo);
        LocalDate datFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate datTo = LocalDate.parse(dateTo, DATE_FORMAT);
        for (String name : names) {
            int salary = 0;
            for (String date : data) {
                String[] split = date.split(DELIMITER);
                LocalDate parseData = LocalDate.parse(split[INDEX_DATE], DATE_FORMAT);
                if (name.equals(split[INDEX_NAME]) && (parseData.isAfter(datFrom)
                        || parseData.equals(datFrom)) && (parseData.isBefore(datTo)
                        || parseData.equals(datTo))) {
                    salary = salary + Integer.valueOf(split[INDEX_HOUR])
                        * Integer.valueOf(split[INDEX_SALARY_PER_HOUR]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(DELIMITER_FOR_BUILDER)
                    .append(Integer.valueOf(salary).toString());
        }
        return salaryInfo.toString();
    }
}
