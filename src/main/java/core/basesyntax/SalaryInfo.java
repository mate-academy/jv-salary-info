package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int HOURS_WORKED = 2;
    private static final int HOURLY_FEE = 3;
    private static final int NAME_INDEX = 1;
    private static final int DATE_INDEX = 0;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder staffSalaryInformation = new StringBuilder("Report for period "
                                             + dateFrom + " - " + dateTo);
        for (String name : names) {
            int totalSalary = 0;
            staffSalaryInformation.append(System.lineSeparator());
            for (String dataItem : data) {
                String[] items = dataItem.split(" ");
                if (name.equals(items[NAME_INDEX])) {
                    if (dateCompare(items[DATE_INDEX], dateTo, dateFrom)) {
                        totalSalary += salaryCounter(items);
                    }
                }
            }
            staffSalaryInformation.append(name).append(" - ").append(totalSalary);
        }

        return staffSalaryInformation.toString();
    }

    private boolean dateCompare(String workDate, String dateTo, String dateFrom) {
        LocalDate localWorkDate = null;
        LocalDate localDateTo = null;
        LocalDate localDateFrom = null;

        try {
            localWorkDate = LocalDate.parse(workDate, formatter);
            localDateTo = LocalDate.parse(dateTo, formatter);
            localDateFrom = LocalDate.parse(dateFrom, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Something went wrong with date parsing",e);
        }

        return (localWorkDate.isBefore(localDateTo) || localWorkDate.equals(localDateTo))
                && (localWorkDate.isAfter(localDateFrom)
                || localWorkDate.equals(localDateFrom));
    }

    private int salaryCounter(String[] items) {
        return Integer.parseInt(items[HOURS_WORKED])
                * Integer.parseInt(items[HOURLY_FEE]);
    }
}
