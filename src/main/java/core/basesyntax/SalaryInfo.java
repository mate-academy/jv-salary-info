package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date from;
        Date to;

        try {
            from = formatter.parse(dateFrom);
            to = formatter.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format", e);
        }

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            try {
                Date workDate = formatter.parse(parts[0]);
                String name = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                if (!workDate.before(from) && !workDate.after(to)) {
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            salaries[i] += hours * rate;
                            break;
                        }
                    }
                }
            } catch (ParseException | NumberFormatException e) {
                System.err.println("Line skipped due to error: " + entry);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
