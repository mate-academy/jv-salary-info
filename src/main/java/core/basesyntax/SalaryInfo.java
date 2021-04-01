package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date[] workingDays = new Date[data.length];
        long[] salaryAllEmployees = new long[names.length];

        Date fromDate = null;
        try {
            fromDate = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            System.out.println("Date not valid");
        }

        Date toDate = null;
        try {
            toDate = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            System.out.println("Date not valid");
        }

        for (int i = 0; i < data.length; i++) {
            try {
                workingDays[i] = dateFormat.parse(data[i].substring(0, 10));
            } catch (ParseException e) {
                System.out.println("Date not valid");
            }
        }

        for (int i = 0; i < workingDays.length; i++) {
            for (int j = 0; j < names.length; j++) {
                if (workingDays[i].compareTo(fromDate) >= 0
                        && workingDays[i].compareTo(toDate) <= 0
                        && names[j].equals(data[i].split(" ")[1])) {
                    salaryAllEmployees[j] += Integer.parseInt(data[i].split(" ")[2])
                            * Integer.parseInt(data[i].split(" ")[3]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (int i = 0; i < names.length; i++) {
            sb = i == 0 ? sb
                    .append(names[i]).append(" - ")
                    .append(salaryAllEmployees[i])
                    : sb.append("\n").append(names[i]).append(" - ")
                    .append(salaryAllEmployees[i]);
        }

        return sb.toString();
    }
}
