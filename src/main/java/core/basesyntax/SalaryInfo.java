package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION = 0;
    private static final int QUANTITY_POSITION = 2;
    private static final int AMOUNT_POSITION = 3;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int totalMoney = 0;
            for (String dataLine : data) {
                if (!dataLine.contains(name)) {
                    continue;
                }
                String[] dataArray = dataLine.split(" ");
                if (LocalDate.parse(dataArray[DATE_POSITION], DTF).compareTo(LocalDate.parse(dateTo, DTF)) > 0
                        || LocalDate.parse(dataArray[DATE_POSITION], DTF)
                        .compareTo(LocalDate.parse(dateFrom, DTF)) < 0) {
                    continue;
                }
                totalMoney += Integer.parseInt(dataArray[QUANTITY_POSITION])
                        * Integer.parseInt(dataArray[AMOUNT_POSITION]);
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(totalMoney);
        }
        return sb.toString();
    }
}
