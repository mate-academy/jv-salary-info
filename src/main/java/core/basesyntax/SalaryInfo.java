package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryReport = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo + "\n");

        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] splitDatum = datum.split(" ");
                if (datum.contains(name) && checkDate(LocalDate.parse(dateFrom, formatter),
                        LocalDate.parse(dateTo, formatter), splitDatum[DATE_INDEX])) {
                    salary += Integer.parseInt(splitDatum[HOURS_INDEX])
                            * Integer.parseInt(splitDatum[INCOME_PER_HOUR_INDEX]);
                }
            }
            salaryReport.append(name).append(" - ").append(salary).append("\n");
        }

        return salaryReport.toString().trim();
    }

    private boolean checkDate(LocalDate startDate, LocalDate endDate, String date) {
        LocalDate dateToCheck = LocalDate.parse(date, formatter);
        return (dateToCheck.isEqual(startDate) || dateToCheck.isEqual(endDate))
                || (dateToCheck.isAfter(startDate) && dateToCheck.isBefore(endDate));
    }
}
