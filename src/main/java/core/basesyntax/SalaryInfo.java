package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        final int days = 0;
        final int workerName = 1;
        final int hours = 2;
        final int hourlyPay = 3;
        String regex = " ";
        for (String employee : names) {
            int salary = 0;
            for (String actualData : data) {
                String[] dateSplit = actualData.split(regex);
                if (employee.equals(dateSplit[workerName])
                        && getCompare(dateFrom, dateTo, dateSplit[days])) {
                    salary += parseInt(dateSplit[hourlyPay]) * parseInt(dateSplit[hours]);
                }
            }
            builder.append(employee).append(" - ").append(salary).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private boolean getCompare(String dateFrom, String dateTo, String forCompare) {
        LocalDate start = LocalDate.parse(dateFrom, formatter);
        LocalDate end = LocalDate.parse(dateTo, formatter);
        LocalDate actual = LocalDate.parse(forCompare, formatter);
        return actual.compareTo(start) >= 0 && actual.compareTo(end) <= 0;
    }
}
