package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {

        LocalDate date = null;
        LocalDate dateDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String stringWithAllData : data) {
                String stringDate = stringWithAllData.split(" ")[DATE_INDEX];
                date = LocalDate.parse(stringDate, FORMATTER);
                String nameFromData = stringWithAllData.split(" ")[NAME_INDEX];
                int hours = Integer.parseInt(stringWithAllData.split(" ")[HOURS_INDEX]);
                int rate = Integer.parseInt(stringWithAllData.split(" ")[RATE_INDEX]);

                if ((date.compareTo(dateDateFrom) >= 0
                        && date.compareTo(dateDateTo) <= 0)
                        && name.equals(nameFromData)) {
                    salary = salary + hours * rate;
                }

            }
            stringBuilder.append("\n").append(name).append(" - ").append(salary);
        }

        return stringBuilder.toString();
    }
}
