package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Double> salaryInfo = new HashMap<>();
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat reportDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Date startDate;
        Date endDate;

        try {
            startDate = inputDateFormat.parse(dateFrom);
            endDate = inputDateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'dd.MM.yyyy'.");
        }

        for (String name : names) {
            salaryInfo.put(name, 0.0);
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            String dateStr = parts[0];
            String name = parts[1];
            double hoursWorked = Double.parseDouble(parts[2]);
            double incomePerHour = Double.parseDouble(parts[3]);

            Date entryDate;
            try {
                entryDate = inputDateFormat.parse(dateStr);
            } catch (ParseException e) {
                continue;
            }

            if (!entryDate.before(startDate) && !entryDate.after(endDate)) {
                double currentSalary = salaryInfo.get(name);
                currentSalary += hoursWorked * incomePerHour;
                salaryInfo.put(name, currentSalary);
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(reportDateFormat.format(startDate)).append(" - ")
                .append(reportDateFormat.format(endDate)).append("\n");

        for (Map.Entry<String, Double> entry : salaryInfo.entrySet()) {
            report.append(entry.getKey()).append(" - ").append(entry.getValue().intValue()).append("\n");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();

        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"
        };
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";

        String result = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(result);
    }
}

