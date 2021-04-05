package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final int zeroElement = 0;
    private final int firstElement = 1;
    private final int secondElement = 2;
    private final int thirdElement = 3;

    public String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {

        LocalDate date = null;
        LocalDate dateDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String stringWithAllData : data) {
                String stringDate = stringWithAllData.split(" ")[zeroElement];
                date = LocalDate.parse(stringDate, formatter);
                String nameFromData = stringWithAllData.split(" ")[firstElement];
                int hours = Integer.parseInt(stringWithAllData.split(" ")[secondElement]);
                int rate = Integer.parseInt(stringWithAllData.split(" ")[thirdElement]);

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
