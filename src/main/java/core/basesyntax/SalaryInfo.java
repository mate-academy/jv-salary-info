package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DataChecker dataChecker = new DataChecker();
    private static final String DATA_SEPARATOR = " ";

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

                    final String [] splitedData = data[j].split(DATA_SEPARATOR);
                    final String zero_data_index = splitedData[0];
                    final LocalDate localDate = LocalDate.parse(zero_data_index, FORMATTER);
                    final int workingHour = Integer.parseInt(splitedData[splitedData.length - 1]);
                    final int incomePerHour = Integer.parseInt(splitedData[splitedData.length - 2]);

                    if (dataChecker.isWithinRange(formattedDateFrom,localDate,formattedDateTo)) {
                        sum += workingHour * incomePerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator() + names[i] + " - " + sum);
        }
        return stringBuilder.toString();
    }
}
