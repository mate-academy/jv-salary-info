package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String[] countData = new String[data.length];
        int[] wages = new int[names.length];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateTimeFormatter);
        LocalDate compareDate;
        String withoutName;
        int hours;
        int money;
        int wage = 0;
        int i = 0;
        for (String date : data) {
            compareDate = LocalDate.parse(date.substring(0, 10), dateTimeFormatter);
            if ((fromDate.isBefore(compareDate) || fromDate.isEqual(compareDate))
                    && (toDate.isAfter(compareDate) || toDate.isEqual(compareDate))) {
                countData[i] = date.substring(10);
                i++;
            }
        }
        i = 0;
        for (String name : names) {
            for (String s : countData) {
                if (s != null && s.contains(name)) {
                    withoutName = s.substring(name.length() + 1).trim();
                    hours = Integer.parseInt(withoutName.substring(0, withoutName.indexOf(" ")));
                    money = Integer.parseInt(withoutName
                            .substring(withoutName.indexOf(" ")).trim());
                    wage += hours * money;
                }
            }
            wages[i] = wage;
            wage = 0;
            i++;
        }
        StringBuilder output = new StringBuilder();
        output.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (int j = 0; j < names.length; j++) {
            output.append('\n').append(names[j]).append(" - ").append(wages[j]);
        }
        return output.toString();
    }
}
