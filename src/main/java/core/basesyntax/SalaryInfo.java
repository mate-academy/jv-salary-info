package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromD = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToD = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder result = new StringBuilder();
        String[] arrayOfData;
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            result.append(System.lineSeparator()).append(name).append(" - ");
            for (String datum : data) {
                arrayOfData = datum.split(" ");
                LocalDate dateNow = LocalDate.parse(arrayOfData[DATE_INDEX],
                        DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                if (name.equals(arrayOfData[NAME_INDEX])
                        && dateNow.isAfter(dateFromD)
                        && dateNow.isBefore(dateToD.plusDays(1))) {
                    salary = salary + (Integer.parseInt(arrayOfData[HOUR_INDEX])
                            * Integer.parseInt(arrayOfData[SALARY_PER_HOUR_INDEX]));
                }
            }
            result.append(salary);
        }
        return result.toString();
    }
}
