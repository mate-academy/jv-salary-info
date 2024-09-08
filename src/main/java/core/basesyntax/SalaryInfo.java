package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int HOURS_WORKED = 2;
    private static final int HOURLY_FEE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder staffSalaryInformation = new StringBuilder("Report for period "
                                             + dateFrom + " - " + dateTo);
        for (String name : names) {
            int totalSalary = 0;
            staffSalaryInformation.append(System.lineSeparator());
            for (String dataItem : data) {
                String[] items = dataItem.split(" ");
                if (name.equals(items[1])) {
                    try {
                        if (dateCompare(items[0], dateTo, dateFrom)) {
                            totalSalary += salaryCounter(items);
                        }
                    } catch (DateTimeParseException e) {
                        throw new RuntimeException("Something went wrong with date parsing");
                    }
                }
            }
            staffSalaryInformation.append(name).append(" - ").append(totalSalary);
        }

        return staffSalaryInformation.toString();
    }

    public boolean dateCompare(String workDate, String dateTo, String dateFrom) {
        LocalDate localWorkDate = LocalDate.parse(workDate, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);

        return (localWorkDate.isBefore(localDateTo) || localWorkDate.equals(localDateTo))
                && (localWorkDate.isAfter(localDateFrom)
                || localWorkDate.equals(localDateFrom));
    }

    public int salaryCounter(String[] items) {
        return Integer.parseInt(items[HOURS_WORKED])
                * Integer.parseInt(items[HOURLY_FEE]);
    }
}
