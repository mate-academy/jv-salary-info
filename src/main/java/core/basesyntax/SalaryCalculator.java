package core.basesyntax;

import java.util.ArrayList;

public class SalaryCalculator {
    private ArrayList<DailySalaryData> employeeSalaryData;

    public int calculate(Employee employee, CalendarDay dateFrom, CalendarDay dateTo) {
        employeeSalaryData = employee.getSalaryData();
        int incomes = 0;
        for (int i = 0; i < employeeSalaryData.size(); i++) {
            if (isDateGreater(employeeSalaryData.get(i), dateFrom)
                    && isDateGreater(dateTo, employeeSalaryData.get(i))) {
                incomes += employeeSalaryData.get(i).getDayIncome() * employeeSalaryData.get(i).getHoursPerDay();
            }
        }
        return incomes;
    }

    private boolean isDateGreater(CalendarDay origin, CalendarDay compareTo) {
        return origin.getYear() > compareTo.getYear()
                || origin.getMonth() > compareTo.getMonth()
                || origin.getDay() >= compareTo.getDay();
    }
}
