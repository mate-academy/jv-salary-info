package core.basesyntax;

import java.time.LocalDate;
import java.util.stream.Stream;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        for (String name : names) {
            builder.append(getIndividualReport(name, data, dateFrom, dateTo));
        }
        return builder.toString().strip();
    }

    private StringBuilder getIndividualReport(String name, String[] data,
                                              String dateFrom, String dateTo) {
        LocalDate from = stringDateToLocale(dateFrom);
        LocalDate to = stringDateToLocale(dateTo);
        StringBuilder builder = new StringBuilder(name + " - ");
        int salaryForPeriod = getSalaryForPeriod(name, data, from, to);
        builder.append(salaryForPeriod)
                .append(System.lineSeparator());
        return builder;
    }

    private int getSalaryForPeriod(String name, String[] data, LocalDate from, LocalDate to) {
        return Stream.of(data)
                .filter(x -> x.contains(name))
                .mapToInt(x -> {
                    String[] tmp = x.split(" ");
                    LocalDate tempDate = stringDateToLocale(tmp[0]);
                    if ((tempDate.isAfter(from) || tempDate.equals(from))
                            && (tempDate.isBefore(to) || tempDate.equals(to))) {
                        return Integer.parseInt(tmp[2]) * Integer.parseInt(tmp[3]);
                    }
                    return 0;
                })
                .sum();
    }

    private LocalDate stringDateToLocale(String date) {
        String[] splitDate = date.split("\\.");
        int day = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        return LocalDate.of(year, month, day);
    }
}
