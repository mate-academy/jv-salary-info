package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);;
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataPart = data[j].split(" ");
                if (dataPart[NAME_INDEX]
                        .equals(names[i])) {
                    LocalDate dateFromData = LocalDate
                            .parse(dataPart[DATE_INDEX], DATE_TIME_FORMATTER);
                    if ((dateFromData.equals(startDate) & dateFromData.equals(endDate))
                            || ((dateFromData.isAfter(startDate) & dateFromData.isBefore(endDate))
                            || (dateFromData.isEqual(startDate) & dateFromData.isBefore(endDate))
                            || (dateFromData.isAfter(startDate) & dateFromData.isEqual(endDate)))) {
                        salary += Integer.parseInt(dataPart[WORKING_HOURS_INDEX])
                                * Integer.parseInt(dataPart[INCOME_INDEX]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
