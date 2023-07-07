package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder monthReport = new StringBuilder();
        monthReport
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (String name : names) {
            int salary = 0;
            for (String dataItem : data) {
                String[] dataParts = dataItem.split(" ");

                try {
                    Date from = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
                    Date to = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
                    Date personDate = new SimpleDateFormat("dd.MM.yyyy").parse(dataParts[0]);

                    if (personDate.getTime() >= from.getTime()
                            && personDate.getTime() <= to.getTime()
                            && dataParts[1].equals(name)) {
                        salary += Integer.parseInt(dataParts[2]) * Integer.parseInt(dataParts[3]);
                    }
                } catch (Exception e) {
                    System.out.println("Can't format string to date");
                }
            }
            monthReport
                    .append(name)
                    .append(" - ")
                    .append(salary)
                    .append("\n");
        }

        monthReport.setLength(monthReport.length() - 1);

        return monthReport.toString();
    }
}
