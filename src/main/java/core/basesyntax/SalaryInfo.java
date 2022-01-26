package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPLETERATOR = " ";
    private static final int INDEX_WORKING_HOUR = 2;
    private static final int INDEX_INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryBilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        int paimant = 0;
        for (String name : names) {
            for (String datum : data) {
                int workingHour = Integer.parseInt(datum.split(SPLETERATOR)[INDEX_WORKING_HOUR ]);
                int incomePerHour = Integer.parseInt(datum.split(SPLETERATOR)[INDEX_INCOME_PER_HOUR]);
                boolean found = Arrays.asList(datum.split(SPLETERATOR)).contains(name);
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
        String onlyDate = value.split(SPLETERATOR)[0];
        return LocalDate.from(DATE_TIME_FORMATTER.parse(onlyDate));
    }

}


