package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        try {
            int[] earned = new int[names.length];
            Date fromDate = DATE_FORMAT.parse(dateFrom);
            Date toDate = DATE_FORMAT.parse(dateTo);

            for (String line: data) {
                String[] split = line.split(" ");
                Date date = DATE_FORMAT.parse(split[0]);
                String name = split[1];
                int hours = Integer.parseInt(split[2]);
                int rate = Integer.parseInt(split[3]);

                if ((date.after(fromDate) || date.equals(fromDate))
                        && (date.before(toDate) || date.equals(toDate))) {
                    for (int i = 0; i < names.length; i++) {
                        if (name.equals(names[i])) {
                            earned[i] += hours * rate;
                        }
                    }
                }
            }

            for (int i = 0; i < names.length; i++) {
                result.append(System.lineSeparator())
                        .append(names[i]).append(" - ").append(earned[i]);
            }
        } catch (Exception e) {
            // don't know what you expect here, convert everything to runtime exceptions? not sure
            // Ok, let's return error in the report
            result.append(System.lineSeparator())
                    .append("Something went wrong. Report cannot be generated. Exception: ")
                    .append(e.getMessage());
            // and print the stacktrace
            e.printStackTrace();
        }

        return result.toString();
    }
}
