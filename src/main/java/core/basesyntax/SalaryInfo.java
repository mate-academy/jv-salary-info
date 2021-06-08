package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate sdateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate sdateTo = LocalDate.parse(dateTo, formatter);
        int[] totalSalaryForPerson = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] dates = data[i].split(" ");
            LocalDate inputDateString = LocalDate.parse(dates[0], formatter);
            if ((inputDateString.isBefore(sdateTo) && inputDateString.isAfter(sdateFrom))
            || inputDateString.isEqual(sdateTo) || inputDateString.isEqual(sdateFrom)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(getName(data[i]))) {
                        totalSalaryForPerson[j] +=
                                getPayPerDay(data[i]) * getDaysOfWork(data[i]);
                        break;
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                stringBuilder.append(names[i]).append(" - ")
                        .append(totalSalaryForPerson[i]);
            } else {
                stringBuilder.append(names[i]).append(" - ")
                        .append(totalSalaryForPerson[i]).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private String getName(String string) {
        String name = string.substring(string.indexOf(SPACE) + 1,
                string.indexOf(SPACE, string.indexOf(SPACE) + 1));
        return name;
    }

    private int getPayPerDay(String string) {
        int daysOfWork = Integer.parseInt(string.substring(
                string.lastIndexOf(SPACE) + 1));
        return daysOfWork;
    }

    private int getDaysOfWork(String string) {
        String[] array = string.split(" ");
        int payPerDay = Integer.parseInt(array[2]);
        return payPerDay;
    }

}
