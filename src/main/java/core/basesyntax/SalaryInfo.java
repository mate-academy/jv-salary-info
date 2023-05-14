package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final String resultSeparator = " - ";
        final int indexDate = 0;
        final int indexName = 1;
        final int indexHours = 2;
        final int indexMoney = 3;
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(resultSeparator).append(dateTo);
        LocalDate startToWork = LocalDate.parse(dateFrom, formatter);
        LocalDate endToWork = LocalDate.parse(dateTo, formatter);
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(resultSeparator);
            int sum = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataSeparated = data[j].split(" ");
                LocalDate workingDay = LocalDate.parse(dataSeparated[indexDate], formatter);
                boolean isPresent = workingDay.isAfter(startToWork) && workingDay.isBefore(endToWork)
                        || workingDay.isEqual(startToWork) || workingDay.isEqual(endToWork);
                boolean isNameMatch = names[i].equals(dataSeparated[indexName]);
                if (isPresent && isNameMatch) {
                    sum += Integer.valueOf(dataSeparated[indexHours]) * Integer.valueOf(dataSeparated[indexMoney]);
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }
}
