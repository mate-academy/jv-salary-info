package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryBilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        int paimant = 0;
        for (String name : names) {
            for (String datum : data) {
                int workingHour = Integer.parseInt(datum.split(" ")[2]);
                int incomePerHour = Integer.parseInt(datum.split(" ")[3]);
                boolean found = Arrays.asList(datum.split(" ")).contains(name);
                if (found) {
                    if (getDay(datum).compareTo(getDay(dateFrom)) >= 0
                            & getDay(dateTo).compareTo(getDay(datum)) >= 0) {
                        paimant += (workingHour * incomePerHour);
                    }
                }
            }
            salaryBilder.append(System.lineSeparator()).append(name).append(" - ").append(paimant);
            paimant = 0;
        }
        return salaryBilder.toString();
    }

    private LocalDate getDay(String value) {
        String onlyDate = value.split(" ")[0];
        return LocalDate.from(DATE_TIME_FORMATTER.parse(onlyDate));
    }

}


