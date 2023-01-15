package core.basesyntax;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryCalculator {
    private ArrayList<DailySalaryData> employeeSalaryData;

    public int calculate(Employee employee, LocalDate dateFrom, LocalDate dateTo) {
        employeeSalaryData = employee.getSalaryData();
        int incomes = 0;
        LocalDate date;
        for (int i = 0; i < employeeSalaryData.size(); i++) {
            date = employeeSalaryData.get(i).getCalendarDay();
            if (isDayInPeriod(date, dateFrom, dateTo)) {
                incomes += employeeSalaryData.get(i).getDayIncome()
                        * employeeSalaryData.get(i).getHoursPerDay();
            }
        }
        return incomes;
    }

    private boolean isDayInPeriod(LocalDate thisDate, LocalDate dateFrom, LocalDate dateTo) {
        return thisDate.isEqual(dateTo) || thisDate.isAfter(dateFrom) && thisDate.isBefore(dateTo);
    }
}
