package core.basesyntax;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int INDEX_OF_INCOME_PER_HOUR = 3;



    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo){
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_PATTERN);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_PATTERN);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            result.append(System.getProperty("line.separator"));
            int salaryEmployee = 0;
            result.append(name).append(" - ");
            for (String recordData : data) {
                String[] arrayData = recordData.split(" ");
                LocalDate date = LocalDate.parse(arrayData[INDEX_OF_DATE], DATE_PATTERN);
                boolean isRequiredName = name.equals(arrayData[INDEX_OF_NAME]);
                boolean isRequiredDate = ((date.isEqual(startDate) || date.isAfter(startDate))
                        && (date.isBefore(endDate) || date.isEqual(endDate)));
                if (isRequiredName && isRequiredDate) {
                    salaryEmployee += (Integer.parseInt(arrayData[INDEX_OF_HOUR])
                            * Integer.parseInt(arrayData[INDEX_OF_INCOME_PER_HOUR]));
                }
            }
            result.append(salaryEmployee);
        }
        return result.toString();
    }
}