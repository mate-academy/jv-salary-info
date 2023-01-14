package core.basesyntax;

import java.util.Arrays;
import java.util.HashMap;

public class SalaryDataParser {
    private final String[] data;
    private final HashMap<String, Employee> employees;

    public SalaryDataParser(String[] data, HashMap<String, Employee> employees) {
        this.data = data;
        this.employees = employees;
    }

    public void parseData() {
        for (String singleData : data) {
            String[] splittedData = singleData.split(" ");
            addData(splittedData);
        }
    }

    private void addData(String[] splittedData) {
        int [] date = Arrays.stream(splittedData[0].split("\\."))
                .mapToInt(Integer::parseInt).toArray();
        String name = splittedData[1];
        int hoursPerDay = Integer.parseInt(splittedData[2]);
        int dayIncome = Integer.parseInt(splittedData[3]);
        Employee employee =  employees.get(name);
        employee.setDate(date);
        employee.setDayIncome(dayIncome);
        employee.setHoursPerDay(hoursPerDay);
    }
}
