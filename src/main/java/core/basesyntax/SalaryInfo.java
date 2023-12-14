package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        try {
            Date startDate = formatDate.parse(dateFrom);
            Date endDate = formatDate.parse(dateTo);

            for (String name : names) {
                int totalEarnings = 0;

                for (String lines : data) {
                    String[] parts = lines.split(" ");
                    Date infoDate = formatDate.parse(parts[0]);

                    if (startDate.compareTo(infoDate) <= 0 && endDate.compareTo(infoDate)
                            >= 0 && name.equals(parts[1])) {
                        int hoursWorked = Integer.parseInt(parts[2]);
                        int incomePerHour = Integer.parseInt(parts[3]);
                        totalEarnings += hoursWorked * incomePerHour;
                    }
                }
                report.append("\n").append(name).append(" - ").append(totalEarnings);
            }
        } catch (ParseException e) {
            System.out.println("Invalid data type");
        }

        return report.toString();
    }
}
