package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String fromDate, String toDate) {
        try {
            Date from = dateFormat.parse(fromDate);
            Date to = dateFormat.parse(toDate);
            String[] salaries = new String[names.length];

            for (String entry : data) {
                String[] parts = entry.split(" ");
                Date entryDate = dateFormat.parse(parts[0]);
                String name = parts[1];
                int hoursWorked = Integer.parseInt(parts[2]);
                int hourlyRate = Integer.parseInt(parts[3]);

                if (entryDate.after(from) && entryDate.before(to)
                        || entryDate.equals(from)
                        || entryDate.equals(to)) {
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            int salary = Integer.parseInt(salaries[i] == null ? "0" : salaries[i]);
                            salary += hoursWorked * hourlyRate;
                            salaries[i] = String.valueOf(salary);
                            break;
                        }
                    }
                }
            }

            StringBuilder report = new StringBuilder("Report for period "
                    + fromDate + " - "
                    + toDate
                    + System.lineSeparator());
            for (int i = 0; i < names.length; i++) {
                report.append(names[i])
                        .append(" - ")
                        .append(salaries[i] == null ? "0" : salaries[i])
                        .append(System.lineSeparator());
            }

            return report.toString().trim();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format in input data", e);
        }
    }
}
