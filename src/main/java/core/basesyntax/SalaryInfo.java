package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int NUMBER_OF_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            report.append(System.lineSeparator());
            report.append(name).append(" - ");
            int salary = 0;
            for (String dates : data) {
                String[] arrayOfData = dates.split(" ");
                LocalDate startDate = LocalDate.parse(dateFrom, formatter);
                LocalDate lastDate = LocalDate.parse(dateTo, formatter);
                LocalDate currentDate = LocalDate.parse(arrayOfData[DATE], formatter);
                if (name.equals(arrayOfData[NAME])
                        && ((currentDate.isAfter(startDate)) || currentDate.isEqual(startDate))
                        && ((currentDate.isBefore(lastDate)) || currentDate.isEqual(lastDate))) {
                    salary += Integer.valueOf(arrayOfData[NUMBER_OF_HOURS])
                            * Integer.valueOf(arrayOfData[INCOME_PER_HOUR]);
                }
            }
            report.append(salary);
        }
        return report.toString();
    }
}
