package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
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
                        salary += Integer.parseInt(valuesParts[valuesParts.length - 1])
                                * Integer.parseInt(valuesParts[valuesParts.length - 2]);
                    }
                }
            }
            builder.append("\n").append(name).append(" - ").append(salary);
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
                .append(dateParts[2])
                .append("-")
                .append(dateParts[1])
                .append("-")
                .append(dateParts[0]);
        return builder.toString();
    }
}
