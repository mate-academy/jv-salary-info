package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Map<String, Integer> employeeSalaries = new HashMap<>();
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + System.lineSeparator());

        try {
            Date newDateFrom = parseDate(dateFrom);
            Date newDateTo = parseDate(dateTo);

            processSalaryData(data, employeeSalaries, newDateFrom, newDateTo);
            buildSalaryReport(result, names, employeeSalaries);

            return result.toString();
        } catch (ParseException e) {
            return "Caught ParseException";
        }
    }

    private Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    private void processSalaryData(String[] data, Map<String, Integer> employeeSalaries,
                                   Date newDateFrom, Date newDateTo) throws ParseException {
        for (String element : data) {
            String[] employeesData = element.split(" ");
            Date date = parseDate(employeesData[DATE_INDEX]);

            if (!date.before(newDateFrom) && !date.after(newDateTo)) {
                String name = employeesData[NAME_INDEX];
                int hoursWorked = Integer.parseInt(employeesData[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(employeesData[RATE_INDEX]);

                int salary = hoursWorked * hourlyRate;
                employeeSalaries.put(name, employeeSalaries.getOrDefault(name, 0) + salary);
            }
        }
    }

    private void buildSalaryReport(StringBuilder result, String[] names,
                                   Map<String, Integer> employeeSalaries) {
        String lineSeparator = System.lineSeparator();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            result.append(name).append(" - ").append(employeeSalaries.getOrDefault(name, 0));
            if (i != names.length - 1) {
                result.append(lineSeparator);
            }
        }
    }
}
