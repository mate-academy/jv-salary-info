package core.basesyntax;

import java.util.ArrayList;
import java.util.Arrays;

public class SalaryDataParser {
    private ArrayList<String[]> splittedData;

    public void addData(ArrayList<Employee> employees) {
        Employee employee;
        String name;
        int[] date;
        int hoursPerDay;
        int dayIncome;
        for (String[] data : splittedData) {
            date = parseDate(data[0]);
            hoursPerDay = Integer.parseInt(data[2]);
            dayIncome = Integer.parseInt(data[3]);
            name = data[1];
            employee = getEmployee(name, employees);
            employee.addDailySalary(new DailySalaryData(date, hoursPerDay, dayIncome));
        }
    }

    // TODO: 15.01.2023  Exception no such employee
    private Employee getEmployee(String name, ArrayList<Employee> employees) {
        for (Employee employee: employees){
            if (employee.getName().equals(name)){
                return employee;
            }
        }return null;
    }

    public int[] parseDate(String date) {
        return Arrays.stream(date.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }

    public CalendarDay arrayToCalendarDay(int[] date) {
        return new CalendarDay(date[0], date[1], date[2]);
    }

    public void parseData(String[] data) {
        splittedData = new ArrayList<>();
        for (String singleData : data) {
            splittedData.add(singleData.split(" "));
        }
    }
}
