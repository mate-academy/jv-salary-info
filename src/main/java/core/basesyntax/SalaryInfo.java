package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final int HOUR_INDEX = 2;
    private static final int YEAR_INDEX = 2;
    private static final int MONTH_INDEX = 1;
    private static final int DATE_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder
                .append("Report for period")
                .append(" ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String values : data) {
                if (values.contains(name)) {
                    String[] valuesParts = values.split(" ");
                    boolean isDateInRange = isDateInRange(dateFrom, dateTo, valuesParts[0]);
                    if (isDateInRange) {
                        salary += Integer.parseInt(valuesParts[SALARY_PER_HOUR_INDEX])
                                * Integer.parseInt(valuesParts[HOUR_INDEX]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }

    private boolean isDateInRange(String dateFrom, String dateTo, String comparingDate) {
        LocalDate startDate = LocalDate.parse(getValidDateString(dateFrom));
        LocalDate endDate = LocalDate.parse(getValidDateString(dateTo));
        LocalDate dateToCompare = LocalDate.parse(getValidDateString(comparingDate));
        return (startDate.equals(dateToCompare)
                || endDate.equals(dateToCompare))
                || (dateToCompare.isAfter(startDate)
                && dateToCompare.isBefore(endDate));
    }

    private String getValidDateString(String date) {
        StringBuilder builder = new StringBuilder();
        String[] dateParts = date.split("\\.");
        builder
                .append(dateParts[YEAR_INDEX])
                .append("-")
                .append(dateParts[MONTH_INDEX])
                .append("-")
                .append(dateParts[DATE_INDEX]);
        return builder.toString();
    }
}
