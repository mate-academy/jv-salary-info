package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA_POSITION_0_DATES = 0;
    private static final int DATA_POSITION_1_NAMES = 1;
    private static final int DATA_POSITION_2_HOURS = 2;
    private static final int DATA_POSITION_3_PER_HOURS = 3;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate limitFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate limitTo = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] dates = datum.split(" ");
                if (name.equals(dates[DATA_POSITION_1_NAMES])) {
                    LocalDate salaryDate = LocalDate.parse(dates[DATA_POSITION_0_DATES], formatter);
                    if (salaryDate.isAfter(limitFrom) && salaryDate.isBefore(limitTo)
                            || salaryDate.isEqual(limitFrom) || salaryDate.isEqual(limitTo)) {
                        salary += Integer.parseInt(dates[DATA_POSITION_2_HOURS])
                                * Integer.parseInt(dates[DATA_POSITION_3_PER_HOURS]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
