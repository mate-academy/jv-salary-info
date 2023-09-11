package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";
    private static final String HEADER = "Report for period ";
    private static final String DELIMITER = " - ";
    private static final int DATE = 0;
    private static final int EMPLOYEE_NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;
    private static final int DATA_CHANGING = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = dateFormat(dateFrom);
        LocalDate toDate = dateFormat(dateTo);
        StringBuilder builder = new StringBuilder();
        builder.append(HEADER).append(dateFrom).append(DELIMITER).append(dateTo);
        LocalDate thisDate;
        for (String name : names) {
            int sum = 0;
            builder.append(System.getProperty("line.separator"))
                    .append(name)
                    .append(DELIMITER);
            for (String dat : data) {
                if (dat.isEmpty()) {
                    continue;
                } else {
                    String[] array = dat.split(" ");
                    thisDate = dateFormat(array[DATE]);
                    if (thisDate.isAfter(fromDate.minusDays(DATA_CHANGING))
                            && thisDate.isBefore(toDate.plusDays(DATA_CHANGING))
                            && name.equals(array[EMPLOYEE_NAME])) {
                        sum += (Integer.parseInt(array[HOURS]) * Integer.parseInt(array[SALARY]));
                    }
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }

    public static LocalDate dateFormat(String thisDate) {
        return LocalDate.parse(thisDate, DateTimeFormatter.ofPattern(FORMATTER));
    }
}
