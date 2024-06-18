package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(lineSeparator);

        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (int i = 0; i < names.length; i++) {
            try {
                int income = 0;
                for (String employeeInfo : data) {
                    String[] employeeInfoArray = employeeInfo.split(" ");

                    String nameFromEmployeeInfo = employeeInfoArray[NAME_INDEX];

                    if (nameFromEmployeeInfo.equals(names[i])) {
                        LocalDate dateFromEmployeeInfo =
                                LocalDate.parse(employeeInfoArray[DATE_INDEX], DATE_TIME_FORMATTER);
                        if (dateFromEmployeeInfo.isEqual(parsedDateFrom)
                                || (dateFromEmployeeInfo.isAfter(parsedDateFrom)
                                && dateFromEmployeeInfo.isBefore(parsedDateTo))
                                || dateFromEmployeeInfo.isEqual(parsedDateTo)) {
                            income += Integer.parseInt(employeeInfoArray[HOUR_INDEX])
                                    * Integer.parseInt(employeeInfoArray[INCOME_PER_HOUR_INDEX]);
                        }
                    }
                }
                result.append(names[i]).append(" - ").append(income);
                if (i < names.length - 1) {
                    result.append(lineSeparator);
                }
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("Invalid input data in employee info for "
                        + names[i] + ", skip it and continue...");
            } catch (Exception exception) {
                throw new RuntimeException("Something went wrong" + exception);
            }
        }
        return result.toString();
    }
}
