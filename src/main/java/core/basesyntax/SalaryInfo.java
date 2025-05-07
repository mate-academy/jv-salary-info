package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter TIME_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom,TIME_PATTERN);
        LocalDate endDate = LocalDate.parse(dateTo,TIME_PATTERN);

        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String dateElement : data) {
                String[] dataSplitted = dateElement.split(" ");
                String staffName = dataSplitted[NAME_INDEX];
                LocalDate currentDate = LocalDate.parse(dataSplitted[DATE_INDEX],TIME_PATTERN);
                if (staffName.equals(name) && inRange(startDate,endDate,currentDate)) {
                    totalSalary += Integer.parseInt(dataSplitted[WORKED_HOURS_INDEX])
                                * Integer.parseInt(dataSplitted[SALARY_PER_HOUR_INDEX]);
                }
            }
            result.append(System.lineSeparator() + name + " - " + totalSalary);
        }

        return result.toString();
    }

    private boolean inRange(LocalDate dateFrom, LocalDate dateTo, LocalDate date) {
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }
}
