package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int PER_HOUR_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataStr : data) {
                String[] splitStrings = dataStr.split(SEPARATOR);
                LocalDate dateFromData = LocalDate.parse(splitStrings[DATE],FORMATTER);
                if (name.equals(splitStrings[NAME])) {
                    if ((dateFromData.isAfter(localDateFrom)
                            || dateFromData.isEqual(localDateFrom))
                            && (dateFromData.isBefore(localDateTo)
                            || dateFromData.isEqual(localDateTo))) {
                        salary += Integer.parseInt(splitStrings[WORKING_HOURS])
                                * Integer.parseInt(splitStrings[PER_HOUR_SALARY]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
