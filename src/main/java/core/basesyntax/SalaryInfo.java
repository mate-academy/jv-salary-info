package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salary = 0;
            builder.append(System.lineSeparator()).append(name).append(" - ");
            for (String info : data) {

                if ((formatter(getDate(info)).isAfter(formatter(dateFrom))
                        || formatter(getDate(info)).equals(formatter(dateFrom)))
                        && (formatter(getDate(info)).equals(formatter(dateTo))
                        || formatter(getDate(info)).isBefore(formatter(dateTo)))
                        && nameFromInfo(info).equals(name)) {
                    salary += getSalaryFromInfo(info);
                }
            }
            builder.append(salary);

        }
        return builder.toString();
    }

    private String getDate(String info) {
        return info.substring(0, info.indexOf(' '));
    }

    private LocalDate formatter(String info) {
        return LocalDate.parse(info, formatter);
    }

    private String nameFromInfo(String info) {
        return info.substring(info.indexOf(' ') + 1, info.indexOf(' ', info.indexOf(' ') + 1));
    }

    private int getSalaryFromInfo(String info) {
        String hoursAndValue = info.substring(info.indexOf(' ',
                info.indexOf(' ', info.indexOf(' ') + 1)) + 1);

        int hours = Integer.parseInt(hoursAndValue.substring(0, hoursAndValue.indexOf(' ')));
        int value = Integer.parseInt(hoursAndValue.substring(hoursAndValue.indexOf(' ') + 1));
        return hours * value;
    }
}
