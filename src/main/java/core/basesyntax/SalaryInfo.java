package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate employeeWorkingDay;
        StringBuilder reportBuilder
                = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int result = 0;
            for (String date : data) {
                String [] dataSplit = date.split(" ");
                employeeWorkingDay = LocalDate.parse(dataSplit[DATE_INDEX], FORMATTER);

                if ((name.equals(dataSplit[NAME_INDEX]))
                        && (employeeWorkingDay.isEqual(fromDate)
                        || employeeWorkingDay.isAfter(fromDate))
                        && (employeeWorkingDay.isEqual(toDate)
                        || employeeWorkingDay.isBefore(toDate))) {
                    result += Integer.parseInt(dataSplit[WORKING_HOURS_INDEX])
                            * Integer.parseInt(dataSplit[SALARY_PER_HOUR_INDEX]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(result);
        }
        return reportBuilder.toString();
    }
}
