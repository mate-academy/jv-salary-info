package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder finalReport = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());


        LocalDate dateF = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo, FORMATTER);

        for (String name : names) {
            int salary = 0;

            for (String employee : data) {
                String[] infoDivided = employee.split(" ");
                LocalDate period = LocalDate.parse(infoDivided[DATE_INDEX], FORMATTER);
                if (name.equals(infoDivided[NAME_INDEX])) {

                    int hours = Integer.parseInt(infoDivided[HOURS_INDEX]);
                    int hourlyRate = Integer.parseInt(infoDivided[RATE_INDEX]);

                    if (!dateT.isBefore(period) && !dateF.isAfter(period)) {
                        salary += hours * hourlyRate;
                    }
                }
            }
            finalReport.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        finalReport.setLength(finalReport.length() - 2);

        return finalReport.toString();
    }
}
