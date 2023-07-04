package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FORMATTER);
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, dtf);
        LocalDate dateToParsed = LocalDate.parse(dateTo, dtf);

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            builder.append(name).append(" - ");
            int sum = 0;
            for (String nameData : data) {
                if (nameData.contains(name)) {
                    String[] splitedData = nameData.split(" ");
                    LocalDate currentDate = LocalDate.parse(splitedData[0], dtf);
                    if (currentDate.isAfter(dateFromParsed)
                            && currentDate.isBefore(dateToParsed)
                            || (currentDate.isEqual(dateToParsed)
                            || currentDate.isEqual(dateFromParsed))) {
                        sum += Integer.parseInt(splitedData[2]) * Integer.parseInt(splitedData[3]);
                    }
                }
            }
            builder.append(sum).append(System.lineSeparator());
        }
        return builder.substring(0, builder.toString().length() - 2);
    }
}
