package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder dataResult = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            dataResult.append(System.lineSeparator()).append(name + " - ");
            int salarySum = 0;
            for (String datum : data) {
                String[] slitData = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(slitData[INDEX_DATE], DATE_TIME_FORMATTER);
                String currentName = slitData[INDEX_NAME];
                if (currentName.equals(name) && (fromDate.isBefore(currentDate)
                        && toDate.isAfter(currentDate)
                        || fromDate.isEqual(currentDate)
                        || toDate.isEqual(currentDate))) {
                    salarySum += Integer.parseInt(slitData[INDEX_SALARY])
                        * Integer.parseInt(slitData[INDEX_HOURS]);
                }
            }
            dataResult.append(salarySum);
        }
        return dataResult.toString();
    }
}
