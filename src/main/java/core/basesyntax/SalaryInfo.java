package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];

        LocalDate dateDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateDateTo = LocalDate.parse(dateTo, formatter);

        for (int i = 0; i < data.length; i++) {
            String[] temp = data[i].split(" ");
            LocalDate currentDate = LocalDate.parse(temp[0], formatter);

            if (temp[1].equals(names[0])
                    && ((currentDate.isEqual(dateDateFrom) || currentDate.isEqual(dateDateTo))
                    || (currentDate.isAfter(dateDateFrom) && currentDate.isBefore(dateDateTo)))) {
                salaries[0] += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
            } else if (temp[1].equals(names[1])
                    && ((currentDate.isEqual(dateDateFrom) || currentDate.isEqual(dateDateTo))
                    || (currentDate.isAfter(dateDateFrom) && currentDate.isBefore(dateDateTo)))) {
                salaries[1] += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
            } else if (temp[1].equals(names[2])
                    && ((currentDate.isEqual(dateDateFrom) || currentDate.isEqual(dateDateTo))
                    || (currentDate.isAfter(dateDateFrom) && currentDate.isBefore(dateDateTo)))) {
                salaries[2] += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < salaries.length; i++) {
            builder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return builder.toString();
    }
}
