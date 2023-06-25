package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int [] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    if (isDateBetweenTwoDays(stringToLocalDate(dateFrom),stringToLocalDate(dateTo),
                            stringToLocalDate(data[j].substring(0, 10)))) {
                        String[] tmp = data[j].split(" ");
                        salary[i] = salary[i] + (Integer.parseInt(tmp[2])
                                * Integer.parseInt(tmp[3]));
                    }
                }
            }
        }
        StringBuilder builder = new StringBuilder(
                String.format("Report for period %1$s - %2$s", dateFrom, dateTo))
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i + 1 < names.length) {
                builder.append(names[i]).append(" - ").append(salary[i])
                        .append(System.lineSeparator());
            } else {
                builder.append(names[i]).append(" - ").append(salary[i]);
            }
        }
        return builder.toString();
    }

    private boolean isDateBetweenTwoDays(LocalDate startDate, LocalDate endDate,
                                         LocalDate targetDate) {
        if (targetDate.equals(endDate)) {
            return true;
        }
        return (!targetDate.isBefore(startDate)) && (targetDate.isBefore(endDate));
    }

    private LocalDate stringToLocalDate(String date) {
        date = date.replaceAll("\\.","-");
        String formatting = date.substring(6) + date.substring(2, 6) + date.substring(0, 2);
        return LocalDate.parse(formatting);
    }
}
