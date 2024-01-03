package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder builderOfValidRange = new StringBuilder();

        for (String dataItem : data) {
            String[] parts = dataItem.split(" ");
            try {
                Date date = dateFormat.parse(parts[0]);
                Date fromDate = dateFormat.parse(dateFrom);
                Date toDate = dateFormat.parse(dateTo);
                if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                    builderOfValidRange.append(parts[1] + " ")
                            .append(Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]) + ",")
                            .toString();
                }
                if (date.compareTo(fromDate) == date.compareTo(toDate)) {
                    builderOfValidRange.append(parts[1] + " ")
                            .append(0).append(",").toString();
                }
            } catch (ParseException e) {
                System.out.println("date format exception");
            }
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] arr = builderOfValidRange.toString().split(",");
        int[] sumsForResultBuilder = new int[names.length];
        for (String record : arr) {
            String[] parts = record.split(" ");
            String nameInData = parts[0];
            int amount = Integer.parseInt(parts[1]);

            for (int i = 0; i < names.length; i++) {
                if (names[i].equalsIgnoreCase(nameInData)) {
                    sumsForResultBuilder[i] += amount;
                    break;
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append("\r\n").append(names[i] + " - ").append(sumsForResultBuilder[i]);
        }
        return result.toString();
    }
}

