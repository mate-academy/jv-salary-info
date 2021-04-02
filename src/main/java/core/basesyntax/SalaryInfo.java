package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, TIME_FORMATTER);

        StringBuilder builder = new StringBuilder("");
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (String name : names) {
            builder.append(name).append(" - ");
            int amountOfSalary = 0;

            for (String personalData : data) {
                if (personalData.contains(name)) {
                    String[] arrayPersonalData = personalData.split(" ");
                    LocalDate personDate = LocalDate.parse(arrayPersonalData[0], TIME_FORMATTER);
                    if ((localDateFrom.compareTo(personDate) <= 0
                            && localDateTo.compareTo(personDate) >= 0)) {
                        amountOfSalary += Integer.parseInt(arrayPersonalData[2])
                                * Integer.parseInt(arrayPersonalData[3]);
                    }
                }
            }
            builder.append(amountOfSalary).append("\n");
        }

        return builder.toString().strip();
    }
}
