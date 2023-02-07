package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String whiteSpace = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
        int[] salaryAll = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String dat : data) {
                String[] datPart = dat.split(whiteSpace);
                LocalDate dateData = LocalDate.parse(datPart[0], formatter);
                if ((names[i].equals(datPart[1])) && (dateFromLocal.isBefore(dateData)
                        || dateFromLocal.equals(dateData)) && ((dateData.isBefore(dateToLocal)
                        || (dateToLocal.equals(dateData))))) {
                    salaryAll[i] = salaryAll[i] + Integer.parseInt(datPart[2])
                            * Integer.parseInt(datPart[3]);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaryAll[i]);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}
