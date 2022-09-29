package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DataChecker dataChecker = new DataChecker();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());

        int sum;
        for (int i = 0; i < names.length; i++) {
            sum = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String [] splitedData = data[j].split(" ");
                    LocalDate localDate = LocalDate.parse(splitedData[0], FORMATTER);
                    if (dataChecker.isWithinRange(formattedDateFrom,localDate,formattedDateTo)) {
                        sum += Integer.parseInt(splitedData[splitedData.length - 1])
                                * Integer.parseInt(splitedData[splitedData.length - 2]);
                    }

                }
            }
            stringBuilder.append(names[i] + " - " + sum + System.lineSeparator());
        }
        return new String(stringBuilder).substring(0,stringBuilder.length() - 1);
    }
}
