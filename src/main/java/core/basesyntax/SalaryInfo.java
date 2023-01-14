package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final String DELIMITER = " ";
    private static final String DASH = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] arrayData;

        for (String nameWorker : names) {
            int salary = 0;
            for (String datum : data) {
                arrayData = datum.split(DELIMITER);
                String name = arrayData[NAME_INDEX];
                LocalDate date = LocalDate.parse(arrayData[DATE_INDEX], FORMATTER);
                LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
                int salaryInDay = (Integer.parseInt(arrayData[HOURS_INDEX])
                        * Integer.parseInt(arrayData[PER_HOUR_INDEX]));

                if (nameWorker.equals(name)) {
                    if ((date.isAfter(localDateFrom) || date.isEqual(localDateFrom))
                            && (date.isBefore(localDateTo) || date.isEqual(localDateTo))) {
                        salary += salaryInDay;
                    }
                }
            }
            result.append(System.lineSeparator())
                    .append(nameWorker)
                    .append(DASH)
                    .append(salary);
        }
        return result.toString();
    }
}
