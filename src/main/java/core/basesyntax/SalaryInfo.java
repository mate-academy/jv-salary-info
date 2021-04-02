package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = null;
        LocalDate dateDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (String name : names) {
            int salary = 0;
            for (String stringWithAllData : data) {
                String stringDate = stringWithAllData.split(" ")[0];
                date = LocalDate.parse(stringDate, formatter);
                String nameFromData = stringWithAllData.split(" ")[1];
                int hours = Integer.parseInt(stringWithAllData.split(" ")[2]);
                int rate = Integer.parseInt(stringWithAllData.split(" ")[3]);

                if ((date.compareTo(dateDateFrom) >= 0
                        && date.compareTo(dateDateTo) <= 0)
                        && name.equals(nameFromData)) {
                    salary = salary + hours * rate;
                }

            }
            stringBuilder.append(name).append(" - ").append(salary).append("\n");
        }

        return stringBuilder.toString().trim();
    }
}
