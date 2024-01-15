package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String FORMAT = "dd.MM.yyyy";
    public static final String SEPARATOR = " ";
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT);
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int totalSalary;
        for (String name: names) {
            totalSalary = 0;
            for (String datum : data) {
                String[] splitDate = datum.split(SEPARATOR);
                if (name.equals(splitDate[NAME_INDEX])) {
                    LocalDate actualDate = LocalDate.parse(splitDate[DATE_INDEX], dateFormatter);
                    if (actualDate.isEqual(fromDate) || actualDate.isEqual(toDate)
                            || (actualDate.isAfter(fromDate) && actualDate.isBefore(toDate))) {
                        totalSalary += Integer.parseInt(splitDate[HOUR_INDEX])
                                * Integer.parseInt(splitDate[SALARY_INDEX]);
                    }
                }
            }
            result.append(System.lineSeparator());
            result.append(name).append(" - ").append(totalSalary);
        }
        return result.toString();
    }
}
