package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate firstDate = LocalDate.parse(dateFrom, formatter);
        LocalDate secondDate = LocalDate.parse(dateTo, formatter);

        String[] temp;
        LocalDate dataDate;
        int firstIncome;
        int[] incomeInfo = new int[3];

        for (int c = 0; c < names.length; c++) {
            firstIncome = 0;

            for (String datum : data) {
                temp = datum.split(" ");
                dataDate = LocalDate.parse(temp[0], formatter);

                if (temp[1].equals(names[c])) {
                    if ((dataDate.isEqual(firstDate) || dataDate.isEqual(secondDate))
                            || (dataDate.isAfter(firstDate) && dataDate.isBefore(secondDate))) {
                        firstIncome += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                        incomeInfo[c] = firstIncome;
                    } else {
                        incomeInfo[c] += 0;
                    }
                }
            }
        }

        StringBuilder formattedInfo = new StringBuilder();
        formattedInfo.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            formattedInfo.append(names[i]).append(" - ")
                    .append(incomeInfo[i]).append(System.lineSeparator());
        }

        return formattedInfo.toString().trim();
    }
}
