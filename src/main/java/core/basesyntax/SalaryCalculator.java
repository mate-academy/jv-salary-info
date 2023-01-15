package core.basesyntax;

import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryCalculator {
    private ArrayList<DailySalaryData> employeeSalaryData;

    public int calculate(Employee employee, LocalDate dateFrom, LocalDate dateTo) {
        employeeSalaryData = employee.getSalaryData();
        int incomes = 0;
        for (int i = 0; i < employeeSalaryData.size(); i++) {
            if (isDateGreater(employeeSalaryData.get(i).getCalendarDay(), dateFrom)
                    && isDateGreater(dateTo, employeeSalaryData.get(i).getCalendarDay())) {
                incomes += employeeSalaryData.get(i).getDayIncome()
                        * employeeSalaryData.get(i).getHoursPerDay();
            }
        }
        return incomes;
    }

    // TODO: 15.01.2023 implement 
    private boolean isDateGreater(LocalDate origin, LocalDate compareTo) {
        return true;
    }
}
