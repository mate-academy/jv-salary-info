package core.basesyntax;

import java.time.LocalDate;

public class PersonalSalaryCalc {
    public String getPersonalSalary(String[] data, String name, 
                                    LocalDate dateFrom, LocalDate dateTo) {
        int salary = 0;
        Getter get = new Getter();
        for (String element: data) {
            if (get.getName(element).equals(name) 
                    && get.getDate(element).isAfter(dateFrom) 
                    && !get.getDate(element).isAfter(dateTo)) {
                salary += get.getSalary(element);
            }
        }
        return name + " - " + salary;
    }
}
