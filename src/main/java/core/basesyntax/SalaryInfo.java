package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String HEADER_TO_START_MESSAGE = "Report for period ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String WRONG_DATE_FORMAT_MESSAGE = "Incorrect date format. "
            + "The date format should be " + DATE_FORMAT;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder(HEADER_TO_START_MESSAGE)
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            salaryInfo
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(getSalaryByName(name, data, dateFrom, dateTo));
        }
        return salaryInfo.toString();
    }

    private int getSalaryByName(String name, String[] data, String dateFrom, String dateTo) {
        int sumSalary = 0;
        for (String itemData : data) {
            EmployeeWorkingTimeData employeeData =
                    new EmployeeWorkingTimeData(itemData.split(" "));
            if (name.equals(employeeData.getName())
                    && isDateInRange(employeeData.getWorkingDay(), dateFrom, dateTo)) {
                sumSalary += employeeData.getRatePerHour() * employeeData.getNumberOfHours();
            }
        }
        return sumSalary;
    }

    private boolean isDateInRange(String date, String dateFrom, String dateTo) {
        LocalDate parseDate = getDateFromString(date);
        LocalDate parseDateFrom = getDateFromString(dateFrom);
        LocalDate parseDateTo = getDateFromString(dateTo);
        return (parseDate.isAfter(parseDateFrom) || parseDate.equals(parseDateFrom))
                && (parseDate.isBefore(parseDateTo) || parseDate.equals(parseDateTo));
    }

    private LocalDate getDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            return LocalDate.parse(stringDate, formatter);
        } catch (Exception e) {
            throw new DateFormatValidException(WRONG_DATE_FORMAT_MESSAGE, e);
        }
    }
}
