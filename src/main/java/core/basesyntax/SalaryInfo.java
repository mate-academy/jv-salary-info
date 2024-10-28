
package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException, ParseException {

        Date startDate = DATE_FORMAT.parse(dateFrom);
        Date endDate = DATE_FORMAT.parse(dateTo);

        if (startDate.after(endDate)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            Date workDate = DATE_FORMAT.parse(parts[0]);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if (!workDate.before(startDate) && !workDate.after(endDate)) {
                int salaryForDay = hoursWorked * hourlyRate;
                salaryMap.put(employeeName, salaryMap.getOrDefault(employeeName, 0) + salaryForDay);
            }
        }

        StringBuilder report = new StringBuilder("Отчёт за период ")
                .append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (String name : names) {
            report.append(name).append(" - ").append(salaryMap.get(name)).append("\n");
        }

        return report.toString().trim() + "\n";
    }

    public String generateReports(String[] names, String[] data, String[] dates)
            throws IllegalDateParametersException, ParseException {
        StringBuilder allReports = new StringBuilder();

        for (int i = 0; i < dates.length; i++) {
            // Используем dates[i] как начальную и конечную дату
            String report = getSalaryInfo(names, data, dates[i], dates[i]);
            allReports.append(report).append("\n");
        }

        return allReports.toString().trim();
    }
}
