package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            for (String name : names) {
                int earnedMoney = 0;
                for (String workerInfo : data) {

                    String[] workersList = workerInfo.split(" ");
                    Date entryDate = dateFormat.parse(workersList[0]);


                    if (workersList[1].equals(name) && entryDate.compareTo(fromDate) >= 0 && entryDate.compareTo(toDate) <= 0) {
                        int hours = Integer.parseInt(workersList[2]);
                        int hourlyRate = Integer.parseInt(workersList[3]);
                        earnedMoney = earnedMoney + hours * hourlyRate;
                    }
                }
                report.append(name).append(" - ").append(earnedMoney).append("\n");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return report.toString();
    }
}

