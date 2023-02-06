package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static String whiteSpace = " ";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
        int[] salaryAll = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String datas : data) {
                String[] datasPart = datas.split(whiteSpace);
                LocalDate dateData = LocalDate.parse(datasPart[0], formatter);
                if ((names[i].equals(datasPart[1])) && (dateFromLocal.isBefore(dateData)
                        || dateFromLocal.equals(dateData)) && ((dateData.isBefore(dateToLocal)
                        || (dateToLocal.equals(dateData))))) {
                    salaryAll[i] = salaryAll[i] + Integer.parseInt(datasPart[2])
                            * Integer.parseInt(datasPart[3]);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salaryAll[i]);
        }
        return "Report for period" + dateFrom + " - " + dateTo + builder.toString();
    }
}
