package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final short DATE_INDEX = 0;
    private static final short NAME_INDEX = 1;
    private static final short WORKING_HOURS_INDEX = 2;
    private static final short SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder result = new StringBuilder();

        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(data[DATE_INDEX],
                        DATE_TIME_FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[NAME_INDEX].equals(name)) {
                    salary = salary + (Integer.parseInt(arrayOfData[SALARY_PER_HOUR_INDEX])
                            * Integer.parseInt(arrayOfData[WORKING_HOURS_INDEX]));
                }
            }
            result.append('\n').append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
