package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, df);
        LocalDate localDateTo = LocalDate.parse(dateTo, df);

        int[] salaries = new int[names.length];
        Arrays.fill(salaries, 0);

        for (int i = 0; i < names.length; i++) {
            for (String dataItem : data) {
                String[] splitData = dataItem.split(" ");
                LocalDate localDateFromData = LocalDate.parse(splitData[0], df);

                if (names[i].equals(splitData[1])
                        && !localDateFromData.isBefore(localDateFrom)
                        && !localDateFromData.isAfter(localDateTo)) {
                    salaries[i] += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }

            sb.append(System.lineSeparator()).append(names[i]).append(" - ").append(salaries[i]);
        }

        return sb.toString();
    }
}
