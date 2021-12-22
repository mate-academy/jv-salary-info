package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateF = parseDate(dateFrom);
        LocalDate dateT = parseDate(dateTo);
        for (String info : data) {
            String[] infoArr = info.split(" ");
            if ((parseDate(infoArr[0]).isAfter(dateF)
                    || parseDate(infoArr[0]).equals(dateF))
                    && (parseDate(infoArr[0]).isBefore(dateT)
                    || parseDate(infoArr[0]).equals(dateF))) {
                for (int i = 0; i < names.length; i++) {
                    String[] name = names[i].split(" ");
                    if (name[0].equals(infoArr[1])) {
                        names[i] = names[i] + " " + String.valueOf(Integer.valueOf(infoArr[2])
                                * Integer.valueOf(infoArr[3]));
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        int sum = 0;
        for (int i = 0; i < names.length; i++) {
            String[] money = names[i].split(" ");
            stringBuilder.append(money[0] + " - ");
            sum = 0;
            for (int j = 1; j < money.length; j++) {
                sum += Integer.valueOf(money[j]);
            }
            stringBuilder.append(String.valueOf(sum) + System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private LocalDate parseDate(String date) {
        String[] dates = date.split("\\.");
        return LocalDate.parse(dates[2] + "-" + dates[1] + "-" + dates[0]);
    }
}
