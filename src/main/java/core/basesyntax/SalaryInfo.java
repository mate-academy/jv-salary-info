package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter fomater = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localeFrom = LocalDate.parse(dateFrom, fomater);
        LocalDate localeTo = LocalDate.parse(dateTo, fomater);
        int salary = 0;
        StringBuilder report = new StringBuilder(" Report for period "
                + dateFrom + " - "
                + dateTo + System.lineSeparator());


        if (localeFrom.isAfter(localeTo)) {
            throw new RuntimeException("Invalid dates");
        }
        for (String name : names) {
            for (String temp : data) {
                String[] dateName = temp.split(" ");
                LocalDate tempData = LocalDate.parse(dateName[0], fomater);
                if (((tempData.isBefore(localeTo)
                        && tempData.isAfter(localeFrom))
                        || tempData.equals(localeTo))
                    && dateName[1].equals(name)) {


                    salary += Integer.parseInt(dateName[2]) * Integer.parseInt(dateName[3]);
                }
            }
            report.append(name).append(" - ").append(salary).append(System.lineSeparator());
            salary = 0;
        }

        return report.toString().trim();
    }
}
