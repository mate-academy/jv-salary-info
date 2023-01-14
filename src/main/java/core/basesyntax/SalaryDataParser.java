package core.basesyntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SalaryDataParser {
    private final String [] data;
    private final ArrayList<String[]> splittedData = new ArrayList<>();

    public SalaryDataParser(String [] data) {
        this.data = data;
        parseData();
    }

    public void addData(HashMap<String, Employee> employees) {
        Employee employee;
        String name;
        int [] date;
        int hoursPerDay, dayIncome;
        for (String[] data : splittedData) {
            date = parseDate(data[0]);
            hoursPerDay = Integer.parseInt(data[2]);
            dayIncome = Integer.parseInt(data[3]);
            name = data[1];
            employee = employees.get(name);
            employee.addToSalaryData(new DailySalaryData(date, hoursPerDay, dayIncome));
        }
    }

    public int[] parseDate(String date){
        return Arrays.stream(date.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }
    public CalendarDay arrayToCalendarDay(int[] date){
        return new CalendarDay (date[0], date [1],date[2]);
    }

    private void parseData() {
        for (String singleData : data) {
            splittedData.add(singleData.split(" "));
        }
    }
}
