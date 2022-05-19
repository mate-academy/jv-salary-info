package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_PERIOD = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_SALARY = 2;
    private static final int INDEX_INCOME = 3;
    private static final String FORMAT_DATE = "dd.MM.yyyy";
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate startDate = LocalDate.parse(dateFrom, formatDate);
        LocalDate endDate = LocalDate.parse(dateTo, formatDate);
        StringBuilder stringPeriod = new StringBuilder();
        stringPeriod.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int resultSalary = 0;
            for (String rowData : data) {
                String[] rowSplit = rowData.split(" ");
                if (name.equals(rowSplit[INDEX_NAME])) {
                    LocalDate dateFromData = LocalDate.parse(rowSplit[INDEX_PERIOD], formatDate);
                    if ((dateFromData.isAfter(startDate) || dateFromData.isEqual(startDate))
                            && (dateFromData.isBefore(endDate) || dateFromData.isEqual(endDate))) {
                        resultSalary += Integer.parseInt(rowSplit[INDEX_SALARY])
                                * Integer.parseInt(rowSplit[INDEX_INCOME]);
                    }
                }
            }
            stringPeriod.append(System.lineSeparator()).append(name).append(" - ")
                    .append(resultSalary);
        }
        return stringPeriod.toString();
    }
}
