package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Map<String, Integer> results = new HashMap<>();

        for (String name : names) {
            results.put(name, 0);
        }

        try {
            Date startDate = simpleDateFormat.parse(dateFrom);
            Date endDate = simpleDateFormat.parse(dateTo);

            for (String row : data) {
                String[] rowData = row.split(" ");

                Date parsedDate = simpleDateFormat.parse(rowData[0]);
                String name = rowData[1];
                int days = Integer.parseInt(rowData[2]);
                int salary = Integer.parseInt(rowData[3]);

                if (results.containsKey(name)
                        && parsedDate.after(startDate)
                        && (parsedDate.before(endDate) || parsedDate.equals(endDate))
                ) {
                    results.replace(name, results.get(name) + days * salary);
                }
            }
        } catch (ParseException e) {
            System.out.println("Wrong date format.");
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            stringBuilder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(results.get(name));
        }

        return stringBuilder.toString();
    }
}
