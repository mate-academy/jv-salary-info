package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_COUNT_INDEX = 2;
    private static final int SALARY_VALUE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salarySum = 0;
            for (String dataRow : data) {
                if (inPeriod(dataRow.split(" ")[DATE_INDEX], dateFrom, dateTo)
                        && name.equals(dataRow.split(" ")[NAME_INDEX])) {
                    salarySum += Integer.parseInt(dataRow.split(" ")[HOUR_COUNT_INDEX])
                        * Integer.parseInt(dataRow.split(" ")[SALARY_VALUE_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salarySum);
        }
        return result.toString();
    }

    private boolean inPeriod(String date, String dateFrom, String dateTo) {
        LocalDate checkedDate = LocalDate.parse(date,
                DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
        LocalDate dateStart = LocalDate.parse(dateFrom,
                DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
        LocalDate dateEnd = LocalDate.parse(dateTo,
                DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
        return (checkedDate.isAfter(dateStart)
                && checkedDate.isBefore(dateEnd))
                || checkedDate.isEqual(dateStart)
                || checkedDate.isEqual(dateEnd);
    }
}
