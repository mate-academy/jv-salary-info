package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATT);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATT);
        StringBuilder info = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] arrayOfData;

        for (String name : names) {
            int salary = 0;
            for (String dat : data) {
                arrayOfData = dat.split(" ");
                LocalDate today = LocalDate.parse(arrayOfData[ZERO], FORMATT);
                if (localDateFrom.compareTo(today) <= 0
                        && localDateTo.compareTo(today) >= 0
                        && arrayOfData[ONE].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[THREE])
                            * Integer.parseInt(arrayOfData[TWO]));
                }
            }
            info.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return info.toString();
    }
}
