package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int HOURS_WORKED = 2;
    private static final int HOURLY_WAGE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            builder.append(System.lineSeparator());
            for (String line : data) {
                String[] arrayOfData = line.split(" ");
                if (name.equals(arrayOfData[EMPLOYEE_NAME])) {
                    LocalDate currentDate = LocalDate.parse(arrayOfData[DATE], FORMATTER);
                    if (localDateFrom.compareTo(currentDate) <= 0
                            && localDateTo.compareTo(currentDate) >= 0
                            && arrayOfData[EMPLOYEE_NAME].equals(name)) {
                        salary += Integer.parseInt(arrayOfData[HOURS_WORKED])
                                * Integer.parseInt(arrayOfData[HOURLY_WAGE]);
                    }
                }
            }
            builder.append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
