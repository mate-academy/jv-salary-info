package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";
    private static final String HEADER = "Report for period ";
    private static final String DELIMITER = " - ";
    private static final int DATE_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final int DATA_CHANGING = 1;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(FORMATTER);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = dateFormat(dateFrom);
        LocalDate toDate = dateFormat(dateTo);
        StringBuilder builder = new StringBuilder()
                .append(HEADER).append(dateFrom).append(DELIMITER).append(dateTo);
        LocalDate thisDate;
        for (String name : names) {
            int sum = 0;
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(DELIMITER);
            for (String line : data) {
                if (line.isEmpty()) {
                    continue;
                } else {
                    String[] array = line.split(" ");
                    thisDate = dateFormat(array[DATE_INDEX]);
                    if (thisDate.isAfter(fromDate.minusDays(DATA_CHANGING))
                            && thisDate.isBefore(toDate.plusDays(DATA_CHANGING))
                            && name.equals(array[EMPLOYEE_NAME_INDEX])) {
                        sum += (Integer.parseInt(array[HOURS_INDEX])
                                * Integer.parseInt(array[SALARY_INDEX]));
                    }
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }

    public static LocalDate dateFormat(String thisDate) {
        return LocalDate.parse(thisDate, DATE_FORMATTER);
    }
}
