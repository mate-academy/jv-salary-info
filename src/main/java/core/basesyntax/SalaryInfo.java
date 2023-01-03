package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final LocalDate dateLast = LocalDate.parse(dateTo, formatter);
        final LocalDate dateFirst = LocalDate.parse(dateFrom, formatter);
        StringBuilder sb = new StringBuilder();
        int[] income = new int[names.length];
        String[] temp = new String[4];
        sb.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                temp = data[i].split(" ");
                if ((temp[1].equals(names[j]))
                        && (LocalDate.parse(temp[0], formatter).isAfter(dateFirst)
                        || LocalDate.parse(temp[0], formatter).isEqual(dateFirst))
                        && (LocalDate.parse(temp[0], formatter).isBefore(dateLast)
                        || LocalDate.parse(temp[0], formatter).isEqual(dateLast))) {
                    income[j] += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                }
            }
            sb.append(names[j] + " - " + income[j]);
            if (j == names.length - 1) {
                break;
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
