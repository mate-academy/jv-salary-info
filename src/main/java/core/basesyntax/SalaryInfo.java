package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String whiteSpace = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        //int[] salaryAll = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String dat : data) {
                String[] datPart = dat.split(whiteSpace);
                LocalDate dateData = LocalDate.parse(datPart[0], formatter);
                if ((names[i].equals(datPart[1])) && (dateFromLocal.isBefore(dateData)
                        || dateFromLocal.equals(dateData)) && ((dateData.isBefore(dateToLocal)
                        || (dateToLocal.equals(dateData))))) {
                    salary = salary + Integer.parseInt(datPart[2])
                            * Integer.parseInt(datPart[3]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i] + " - " + salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}
