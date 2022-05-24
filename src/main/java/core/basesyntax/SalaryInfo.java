package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final byte dateIndex = 0;
    private static final byte nameIndex = 1;
    private static final byte workingHoursIndex = 2;
    private static final byte salaryPerHourIndex = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder result = new StringBuilder();

        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        String[] arrayOfData;

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[dateIndex], DATE_TIME_FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[nameIndex].equals(name)) {
                    salary = salary + (Integer.parseInt(arrayOfData[salaryPerHourIndex])
                            * Integer.parseInt(arrayOfData[workingHoursIndex]));
                }
            }
            result.append('\n').append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}