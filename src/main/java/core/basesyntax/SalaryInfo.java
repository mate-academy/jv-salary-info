package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public static final int DAY_POSITION = 0;
    public static final int MONTH_POSITION = 1;
    public static final int YEAR_POSITION = 2;
    public static final int DATE_POSITION = 0;
    public static final int NAME_POSITION = 1;
    public static final int HOUR_POSITION = 2;
    public static final int INCOME_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = getDate(dateFrom);
        LocalDate toDate = getDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataRow : data) {
                String[] rowElements = dataRow.split(" ");
                LocalDate date = getDate(rowElements[DATE_POSITION]);
                if (name.equals(rowElements[NAME_POSITION])
                        && (date.isEqual(fromDate) || date.isAfter(fromDate))
                        && (date.isEqual(toDate) || date.isBefore(toDate))) {
                    salary += Integer.parseInt(rowElements[HOUR_POSITION])
                            * Integer.parseInt(rowElements[INCOME_POSITION]);
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return result.toString();
    }

    private LocalDate getDate(String dateString) {
        String[] dateSplit = dateString.split("\\.");
        int day = Integer.parseInt(dateSplit[DAY_POSITION]);
        int mounse = Integer.parseInt(dateSplit[MONTH_POSITION]);
        int year = Integer.parseInt(dateSplit[YEAR_POSITION]);
        return LocalDate.of(year, mounse, day);
    }
}
