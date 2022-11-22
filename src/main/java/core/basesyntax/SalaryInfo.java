package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DAY = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int PAY_HOURS = 3;
    private static final DateTimeFormatter FORRMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder array = new StringBuilder();
        LocalDate dateFromlocal = LocalDate.parse(dateFrom, FORRMATTER);
        LocalDate dateTolocal = LocalDate.parse(dateTo, FORRMATTER);
        for (String name : names) {
            int salaryInfo = 0;
            for (String dateSplit : data) {
                String [] dataNew = dateSplit.split(" ");
                LocalDate dateLocal = LocalDate.parse(dataNew[DAY], FORRMATTER);
                String nameData = dataNew[NAME];
                if (name.equals(nameData)
                        && (dateLocal.isEqual(dateFromlocal)
                        || dateLocal.isAfter(dateFromlocal))
                        && (dateLocal.isEqual(dateTolocal)
                        || dateLocal.isBefore(dateTolocal))) {
                    salaryInfo += Integer.parseInt(dataNew[HOURS])
                            * Integer.parseInt(dataNew[PAY_HOURS]);
                }
            }
            array.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryInfo);
        }
        return "Report for period " + dateFrom + " - " + dateTo + array;
    }
}

