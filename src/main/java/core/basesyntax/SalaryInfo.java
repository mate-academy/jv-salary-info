package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String JOHN = "John";
    private static final String ANDREW = "Andrew";
    private static final String KATE = "Kate";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int johnMoney = 0;
        int andrewMoney = 0;
        int kateMoney = 0;
        LocalDate dateFromObj = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToObj = LocalDate.parse(dateTo, formatter).plusDays(1);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String record : data) {
            String[] arr = record.split(" ");
            LocalDate dateObj = LocalDate.parse(arr[0], formatter);
            String employee = arr[1];
            int hours = Integer.parseInt(arr[2]);
            int money = Integer.parseInt(arr[3]);

            if (dateObj.isAfter(dateFromObj) && dateObj.isBefore(dateToObj)) {
                int allMoney = hours * money;
                switch (employee) {
                    case JOHN:
                        johnMoney += allMoney;
                        break;
                    case ANDREW:
                        andrewMoney += allMoney;
                        break;
                    default:
                        kateMoney += allMoney;
                        break;
                }
            }
        }
        builder.append(JOHN).append(" - ").append(johnMoney).append(System.lineSeparator());
        builder.append(ANDREW).append(" - ").append(andrewMoney).append(System.lineSeparator());
        builder.append(KATE).append(" - ").append(kateMoney);
        return builder.toString();
    }
}
