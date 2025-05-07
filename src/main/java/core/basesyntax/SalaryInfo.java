package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date fromDate = dateFormat.parse(dateFrom);
            Date toDate = dateFormat.parse(dateTo);

            for (String employeeData : data) {
                String[] parts = employeeData.split(" ");
                String employeeDateStr = parts[0];
                Date employeeDate = dateFormat.parse(employeeDateStr);

                if (employeeDate.compareTo(fromDate) >= 0 && employeeDate.compareTo(toDate) <= 0) {
                    String employeeName = parts[1];
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);

                    for (int i = 0; i < names.length; i++) {
                        if (Objects.equals(names[i], employeeName)) {
                            salaries[i] += hours * rate;
                            break;
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String lineSeparator = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(lineSeparator).append(names[i]).append(" - ").append(salaries[i]);
        }
        return stringBuilder.toString();
    }
}
