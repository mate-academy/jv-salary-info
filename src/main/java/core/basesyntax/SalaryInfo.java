package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int AMOUNT_INDEX = 2;
    public static final int PAY_FOR_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int [] salary = new int[names.length];
        StringBuilder reportBuilder = new StringBuilder(
                String.format("Report for period %1$s - %2$s", dateFrom, dateTo))
                .append(System.lineSeparator());
        LocalDate localDateFrom = stringToLocalDate(dateFrom);
        LocalDate localDateTo = stringToLocalDate(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String [] recordSplitter = data[j].split(" ");
                if (recordSplitter[NAME_INDEX].equals(names[i])) {
                    if (isDateBetweenTwoDays(localDateFrom, localDateTo,
                            stringToLocalDate(recordSplitter[DATE_INDEX]))) {
                        salary[i] = salary[i] + (Integer.parseInt(recordSplitter[AMOUNT_INDEX])
                                * Integer.parseInt(recordSplitter[PAY_FOR_HOUR_INDEX]));
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            if (i + 1 < names.length) {
                reportBuilder.append(names[i])
                        .append(" - ")
                        .append(salary[i])
                        .append(System.lineSeparator());
            } else {
                reportBuilder.append(names[i])
                        .append(" - ")
                        .append(salary[i]);
            }
        }
        return reportBuilder.toString();
    }

    private boolean isDateBetweenTwoDays(LocalDate startDate, LocalDate endDate,
                                         LocalDate targetDate) {
        if (targetDate.equals(endDate)) {
            return true;
        }
        return (!targetDate.isBefore(startDate)) && (targetDate.isBefore(endDate));
    }

    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}
