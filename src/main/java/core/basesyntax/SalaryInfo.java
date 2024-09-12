package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        StringBuilder salaryInformation = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int totalSalary = 0;
            salaryInformation.append(System.lineSeparator());
            for (String dataElement : data) {
                String[] element = dataElement.split(" ");
                if (name.equals(element[NAME_INDEX])) {
                    if (dataCompare(element[DATE_INDEX], dateTo, dateFrom)) {
                        totalSalary += salaryCounter(element);
                    }
                }
            }
            salaryInformation.append(name).append(" - ").append(totalSalary);
        }
        return salaryInformation.toString();
    }

    private boolean dataCompare(String workDate, String dateTo, String dateFrom) {
        LocalDate localWorkDate;
        LocalDate localDateTo;
        LocalDate localDateFrom;

        localWorkDate = LocalDate.parse(workDate, formatter);
        localDateTo = LocalDate.parse(dateTo, formatter);
        localDateFrom = LocalDate.parse(dateFrom, formatter);
        return (localWorkDate.isBefore(localDateTo) || localWorkDate.equals(localDateTo))
             && (localWorkDate.isAfter(localDateFrom)
             || localWorkDate.equals(localDateFrom));
    }

    private int salaryCounter(String[] element) {
        return Integer.parseInt(element[HOURS_WORKED_INDEX])
                * Integer.parseInt(element[HOURLY_RATE_INDEX]);
    }
}
