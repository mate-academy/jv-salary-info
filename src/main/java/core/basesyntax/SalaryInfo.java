package core.basesyntax;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        Period period = Period.between(startDate, endDate);
        int a = period.getDays() + 1;
        String[] dateArray = new String[a];
        for (int i = 0; i < a; i++) {
            dateArray[i] = startDate.plusDays(i).format(formatter);
        }

        String strData = String.join(" ", data);

        String[] data1 = strData.split(" ");

        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < dateArray.length; j++) {
            for (int x = 0; x < data1.length; x++) {
                if (dateArray[j].equals(data1[x])) {
                    int income = Integer.parseInt(data1[x + 2]) * Integer.parseInt(data1[x + 3]);

                    stringBuilder.append(data1[x + 1]).append(income);
                }
            }
        }

        String[] data2 = stringBuilder.toString().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int k = 0; k < names.length; k++) {
            int summary = 0;
            stringBuilder1.append(names[k]).append(" - ");
            for (int y = 0; y < data2.length; y++) {

                if (names[k].equals(data2[y])) {
                    summary += Integer.parseInt(data2[y + 1]);
                }
            }
            stringBuilder1.append(summary).append(System.lineSeparator());
        }
        String result = stringBuilder1.toString().trim();
        return result;

    }

}


