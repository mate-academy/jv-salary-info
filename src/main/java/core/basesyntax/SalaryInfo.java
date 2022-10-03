package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DataChecker dataChecker = new DataChecker();
    private static final String DATA_SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int WORKING_HOURS_INDEX = 1;
    private static final int INCOME_PER_HOUR_INDEX = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate formattedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo);

        int sum;
        for (int i = 0; i < names.length; i++) {
            sum = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String [] splitedData = data[j].split(DATA_SEPARATOR);
                    String targetDateStr = splitedData[DATE_INDEX];
                    LocalDate localDate = LocalDate.parse(targetDateStr, FORMATTER);
                    int workingHours = Integer.parseInt(splitedData[splitedData.length - WORKING_HOURS_INDEX]);
                    int incomePerHour = Integer.parseInt(splitedData[splitedData.length - INCOME_PER_HOUR_INDEX]);

                    if (dataChecker.isWithinRange(formattedDateFrom,localDate,formattedDateTo)) {
                        sum += workingHours * incomePerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator() + names[i] + " - " + sum);
        }
        return stringBuilder.toString();
    }
}
