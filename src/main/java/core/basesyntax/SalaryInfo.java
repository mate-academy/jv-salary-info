package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            result.append(System.lineSeparator());
            int salaryEmployee = 0;
            result.append(name).append(" - ");
            for (String recordData : data) {
                String[] splittedRecord = recordData.split(" ");
                LocalDate date = LocalDate.parse(splittedRecord[INDEX_OF_DATE], DATE_FORMATTER);
                boolean isRequiredName = name.equals(splittedRecord[INDEX_OF_NAME]);
                boolean isRequiredDate = ((date.isEqual(startDate) || date.isAfter(startDate))
                        && (date.isBefore(endDate) || date.isEqual(endDate)));
                if (isRequiredName && isRequiredDate) {
                    salaryEmployee += (Integer.parseInt(splittedRecord[INDEX_OF_HOUR])
                            * Integer.parseInt(splittedRecord[INDEX_OF_INCOME_PER_HOUR]));
                }
            }
            result.append(salaryEmployee);
        }
        return result.toString();
    }
}
