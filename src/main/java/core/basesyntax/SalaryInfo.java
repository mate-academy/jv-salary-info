package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> employeeSalaries = new HashMap<>();
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());
        String lineSeparator = System.lineSeparator();

        try {
            Date newDateFrom = dateFormat.parse(dateFrom);
            Date newDateTo = dateFormat.parse(dateTo);

            for (String element : data) {
                String[] employeesData = element.split(" ");
                Date date = dateFormat.parse(employeesData[0]);

                if (!date.before(newDateFrom) && !date.after(newDateTo)) {
                    String name = employeesData[1];
                    int hoursWorked = Integer.parseInt(employeesData[2]);
                    int hourlyRate = Integer.parseInt(employeesData[3]);

                    int salary = hoursWorked * hourlyRate;
                    employeeSalaries.put(name, employeeSalaries.getOrDefault(name, 0) + salary);
                }
            }

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                result.append(name).append(" - ").append(employeeSalaries.getOrDefault(name, 0));
                if (i != names.length - 1) {
                    result.append(lineSeparator);
                }
            }
            return result.toString();
        } catch (ParseException e) {
            return "Caught ParseException";
        }
    }
}
