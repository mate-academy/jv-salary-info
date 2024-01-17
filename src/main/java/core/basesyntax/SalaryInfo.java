package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            Map<String, Integer> salaryMap = new HashMap<>();

            for (String name : names) {
                salaryMap.put(name, 0);
            }

            for (String entry : data) {
                String[] fields = entry.split(" ");
                Date entryDate = dateFormat.parse(fields[0]);

                if (!entryDate.before(fromDate) && !entryDate.after(toDate)) {
                    String name = fields[1];
                    int hoursWorked = Integer.parseInt(fields[2]);
                    int incomePerHour = Integer.parseInt(fields[3]);
                    int totalIncome = hoursWorked * incomePerHour;

                    salaryMap.put(name, salaryMap.get(name) + totalIncome);
                }
            }

            StringBuilder result = new StringBuilder("Report for period ")
                    .append(dateFormat.format(fromDate))
                    .append(" - ")
                    .append(dateFormat.format(toDate))
                    .append(System.lineSeparator());

            for (String name : names) {
                result.append(name).append(" - ").append(salaryMap.get(name))
                        .append(System.lineSeparator());
            }

            return result.toString().trim();
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid date format";
        }
    }
}
