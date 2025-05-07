package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_NAME_IN_DATA = 1;
    private static final int INDEX_OF_WORKING_HOUR_IN_DATA = 2;
    private static final int INDEX_OF_PRICE_PER_HOUR_IN_DATA = 3;
    private static final String SPLIT_REGEXP = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate currentDay;
        for (String name : names) {
            result.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String elementData : data) {
                currentDay = LocalDate.parse(elementData.split(SPLIT_REGEXP)[0], formatter);
                if (currentDay.isAfter(localDateFrom)
                        && currentDay.isBefore(localDateTo)
                        || currentDay.isEqual(localDateFrom)
                        || currentDay.isEqual(localDateTo)) {

                    String[] splitData = elementData.split(SPLIT_REGEXP);
                    if (name.equals(splitData[INDEX_OF_NAME_IN_DATA])) {
                        salary += Integer.parseInt(splitData[INDEX_OF_WORKING_HOUR_IN_DATA])
                                * Integer.parseInt(splitData[INDEX_OF_PRICE_PER_HOUR_IN_DATA]);
                    }
                }
            }
            result.append(salary);
        }
        return result.toString();
    }
}
