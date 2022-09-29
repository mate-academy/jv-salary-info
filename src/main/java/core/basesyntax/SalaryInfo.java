package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period " + dateFrom + " - " + dateTo);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date beginDate;
        Date endDate;
        try {
            beginDate = dateFormat.parse(dateFrom);
            endDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Wrong date format", e);
        }

        for (String name : names) {
            sb.append(System.lineSeparator());
            int salary = 0;
            for (String s : data) {
                String[] info = s.split(" ");

                Date workDay;
                try {
                    workDay = dateFormat.parse(info[0]);
                } catch (ParseException e) {
                    throw new RuntimeException("Wrong date format", e);
                }

                if (info[1].equals(name)
                        && (workDay.compareTo(beginDate) >= 0 && workDay.compareTo(endDate) <= 0)) {
                    int hours = Integer.parseInt(info[2]);
                    int paymentPerHour = Integer.parseInt(info[3]);
                    salary += hours * paymentPerHour;
                }
            }
            sb.append(name + " - " + salary);
        }
        return sb.toString();
    }
}
