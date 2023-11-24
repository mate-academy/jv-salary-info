package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String dataElement : data) {
                String[] dataPerDataElementArray = dataElement.split(" ");
                LocalDate localData = LocalDate.parse(dataPerDataElementArray[DATE_INDEX],
                        formatter);
                if (localData.isAfter(localDateFrom) && localData.isBefore(localDateTo)
                        ||
                        localData.isEqual(localDateFrom)
                        ||
                        localData.isEqual(localDateTo)) {

                    if (name.equals(dataPerDataElementArray[NAME_INDEX])) {
                        salary += Integer.parseInt(dataPerDataElementArray[WORKING_HOUR_INDEX])
                                *
                                Integer.parseInt(dataPerDataElementArray[INCOME_PER_HOUR_INDEX]);
                    }
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
