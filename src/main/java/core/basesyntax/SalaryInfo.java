package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryAll = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String datas : data) {
                String[] datasPart = datas.split(" ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
                LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
                LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
                LocalDate dateData = LocalDate.parse(datasPart[0], formatter);
                if ((names[i].equals(datasPart[1])) && (dateFromLocal.isBefore(dateData)
                        || dateFromLocal.equals(dateData)) && ((dateData.isBefore(dateToLocal)
                        || (dateToLocal.equals(dateData))))) {
                    salaryAll[i] = salaryAll[i] + Integer.parseInt(datasPart[2])
                            * Integer.parseInt(datasPart[3]);
                }
            }
        }
        return "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator()
                + names[0] + " - " + salaryAll[0] + System.lineSeparator()
                + names[1] + " - " + salaryAll[1] + System.lineSeparator()
                + names[2] + " - " + salaryAll[2];
    }
}
