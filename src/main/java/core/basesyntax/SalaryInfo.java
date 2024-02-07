package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitedString = new String[4];
        LocalDate parseSplitedDate = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate parseDateTo = LocalDate.parse(dateTo, formatter);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String d : data) {
            splitedString = d.split(" ");
            parseSplitedDate = LocalDate.parse(splitedString[0], formatter);
            if ((parseSplitedDate.isAfter(parseDateFrom)
                    || parseSplitedDate.isEqual(parseDateFrom))
                    && (parseSplitedDate.isBefore(parseDateTo)
                    || parseSplitedDate.isEqual(parseDateTo))) {
                for (String name : names) {
                    if (name.equals(splitedString[1])) {
                        int perHour = Integer.parseInt(splitedString[2])
                                * Integer.parseInt(splitedString[3]);
                        stringBuilder.append("/n").append(name).append(" - ").append(perHour);
                    }
                }

            }
        }
        return stringBuilder.toString();
    }
}
