package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA_TO_CHECK = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;
    private static final String PATTERN_OF_DATE = "dd.MM.yyyy";
    private static final String DELIMITER = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_OF_DATE);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        LocalDate timeFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate timeTo = LocalDate.parse(dateTo, formatter);
        for (int i = 0; i < names.length; i++) {
            int totalSalaryOfAPerson = 0;
            for (int j = 0; j < data.length; j++) {
                String[] oneLineOfData = data[j].split(DELIMITER);
                if (oneLineOfData[NAME].equals(names[i])
                        && isDateRelevant(oneLineOfData[DATA_TO_CHECK], timeFrom, timeTo)) {
                    totalSalaryOfAPerson += Integer.parseInt(oneLineOfData[HOURS])
                            * Integer.parseInt(oneLineOfData[INCOME_PER_HOUR]);
                }
            }
            builder.append(names[i]).append(" - ").append(totalSalaryOfAPerson);
            if (i != names.length - 1) {
                builder.append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    private boolean isDateRelevant(String dataToCheck, LocalDate timeFrom, LocalDate timeTo) {
        LocalDate timeToCheck = LocalDate.parse(dataToCheck, formatter);
        return (timeToCheck.compareTo(timeFrom) < 0 || timeToCheck.compareTo(timeTo) > 0)
                ? false : true;
    }
}
