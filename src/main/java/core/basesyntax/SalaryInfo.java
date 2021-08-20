package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATA_INDEX = 0;
    public static final int NAME_OF_EMPLOYEE = 1;
    public static final int WORKING_HOURS = 2;
    public static final int MONEY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER).minusDays(1);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER).plusDays(1);

        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String nameOfEmployee : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] infoArray = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(infoArray[DATA_INDEX],
                        DATE_TIME_FORMATTER);
                if ((nameOfEmployee.equals(infoArray[NAME_OF_EMPLOYEE]))
                        && currentDate.isAfter(localDateFrom)
                        && currentDate.isBefore(localDateTo)) {
                    totalSalary += Integer.parseInt(infoArray[WORKING_HOURS])
                            * Integer.parseInt(infoArray[MONEY_PER_HOUR]);
                }
            }
            stringBuilder.append(nameOfEmployee)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
