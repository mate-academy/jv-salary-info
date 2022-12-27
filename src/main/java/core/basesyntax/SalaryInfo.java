package core.basesyntax;

import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateParser dateParser = new DateParser();
        Date startDate = dateParser.date(dateFrom);
        Date stopDate = dateParser.date(dateTo);
        int [] salaries = new int[names.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                if (names[i].equals(datum.split(" ")[1])
                        && dateParser.date(datum.split(" ")[0]).compareTo(startDate) >= 0
                        && dateParser.date(datum.split(" ")[0]).compareTo(stopDate) <= 0) {
                    salaries[i] += Integer.parseInt(datum.split(" ")[2])
                            * Integer.parseInt(datum.split(" ")[3]);
                }
            }
            sb.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + System.lineSeparator() + sb;
    }
}
