package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int HOURS_POSITION = 2;
    private static final int HOUR_INCOME_POSITION = 3;

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, format);
        LocalDate toDate = LocalDate.parse(dateTo, format);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dat : data) {
                String[] splittedData = dat.split(" ");
                LocalDate currentDate = LocalDate.parse(splittedData[DATE_POSITION], format);
                if (splittedData[NAME_POSITION].equals(name)) {
                    if (currentDate.isAfter(fromDate) && currentDate.isBefore(toDate)
                            || currentDate.isEqual(toDate)
                            || currentDate.isEqual(fromDate)) {
                        salary += Integer.parseInt(splittedData[HOURS_POSITION])
                                * Integer.parseInt(splittedData[HOUR_INCOME_POSITION]);
                    }
                }

            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}

