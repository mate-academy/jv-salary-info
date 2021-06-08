package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPACE = " ";
    private static final String DASH = " - ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate sdateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate sdateTo = LocalDate.parse(dateTo, formatter);
        int[] totalSalaryForPerson = new int[names.length];
        for (String inputData : data) {
            String[] dates = inputData.split(SPACE);
            LocalDate inputDateString = LocalDate.parse(dates[0], formatter);
            if ((inputDateString.isBefore(sdateTo) && inputDateString.isAfter(sdateFrom))
                    || inputDateString.isEqual(sdateTo) || inputDateString.isEqual(sdateFrom)) {
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(getName(inputData))) {
                        totalSalaryForPerson[j] +=
                                getPayPerDay(inputData) * getDaysOfWork(inputData);
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
                stringBuilder.append(names[i]).append(DASH)
                        .append(totalSalaryForPerson[i]);
            } else {
                stringBuilder.append(names[i]).append(DASH)
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
        String[] array = string.split(SPACE);
        int payPerDay = Integer.parseInt(array[2]);
        return payPerDay;
    }

}
