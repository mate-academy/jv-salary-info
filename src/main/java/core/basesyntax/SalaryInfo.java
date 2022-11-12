package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private LocalDate fromDate;
    private LocalDate toDate;
    private StringBuilder builder = new StringBuilder();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int result = 0;
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate employeeWorkingDay;
        fromDate = LocalDate.parse(dateFrom, formatter);
        toDate = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            for (String date : data) {
                String [] dataSplit = date.split(" ");
                employeeWorkingDay = LocalDate.parse(dataSplit[0], formatter);

                if ((name.equals(dataSplit[1]))
                        && (employeeWorkingDay.isEqual(fromDate)
                        || employeeWorkingDay.isAfter(fromDate))
                        && (employeeWorkingDay.isEqual(toDate)
                        || employeeWorkingDay.isBefore(toDate))) {
                    result += Integer.parseInt(dataSplit[2]) * Integer.parseInt(dataSplit[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(result);
            result = 0;
        }
        return builder.toString();
    }
}
