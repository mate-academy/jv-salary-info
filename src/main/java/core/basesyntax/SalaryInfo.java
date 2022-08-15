package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int ORDER_DATE_OF_STRING = 0;
    private static final int ORDER_NAME_OF_STRING = 1;
    private static final int ORDER_WORKING_HOURS_OF_STRING = 2;
    private static final int ORDER_SALARY_OF_HOUR_OF_STRING = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] arrayOfData;

        for (String name : names) {
            int salary = 0;
            for (String strings : data) {
                arrayOfData = strings.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[ORDER_DATE_OF_STRING],
                        FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[ORDER_NAME_OF_STRING].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[ORDER_SALARY_OF_HOUR_OF_STRING])
                        * Integer.parseInt(arrayOfData[ORDER_WORKING_HOURS_OF_STRING]));
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}

