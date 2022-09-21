package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] temp;
        StringBuilder sb = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int totalMoney = 0;
            for (String dataLine : data) {
                if (!dataLine.contains(name)) {
                    continue;
                }
                temp = dataLine.split(" ");
                if (LocalDate.parse(temp[0], DTF).compareTo(LocalDate.parse(dateTo, DTF)) > 0
                        || LocalDate.parse(temp[0], DTF)
                        .compareTo(LocalDate.parse(dateFrom, DTF)) < 0) {
                    continue;
                }
                totalMoney += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(totalMoney);
        }
        return sb.toString();
    }
}
