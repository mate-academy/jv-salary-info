package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date from;
        Date to;

        try {
            from = format.parse(dateFrom);
            to = format.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format");
        }

        int[] salaries = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            String employee = names[i];

            for (String record : data) {
                String[] parts = record.split(" ");

                if (parts.length == 4) {
                    try {
                        Date workDate = format.parse(parts[0]);
                        String recordName = parts[1];
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);

                        if (recordName.equals(employee) &&
                                !workDate.before(from) && !workDate.after(to)) {
                            salaries[i] += hours * rate;
                        }
                    } catch (ParseException | NumberFormatException ignored) {
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]).append("\n");
        }

        return report.toString().trim();
    }
}