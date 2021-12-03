package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    //private SimpleDateFormat stringToDate = new SimpleDateFormat("dd.MM.yyyy");
    private DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        Employee[] employeesArray = new Employee[names.length];

        for (int i = 0; i < names.length; i++) {
            Employee employee = new Employee(names[i], 0);
            employeesArray[i] = employee;
        }

        for (String curent : data) {
            String[] content = curent.split(" ");
            int indexArray;
            if (checkDate(content[0], dateFrom, dateTo)) {
                int addSalar;
                addSalar = Integer.parseInt(content[2]) * Integer.parseInt(content[3]);
                indexArray = indexOfArray(employeesArray, content[1]);
                if (indexArray >= 0) {
                    employeesArray[indexArray].addSalary(addSalar);
                }
            }
        }
        for (Employee curent: employeesArray) {
            stringBuilder.append(curent.toString());
        }

        return stringBuilder.toString();
    }

    private boolean checkDate(String checkedDate, String from, String to) {
        try {
            LocalDate curentDate = LocalDate.parse(checkedDate, formatDate);
            LocalDate dateFrom = LocalDate.parse(from, formatDate);
            LocalDate dateTo = LocalDate.parse(to, formatDate);
            if (curentDate.compareTo(dateFrom) >= 0 && curentDate.compareTo(dateTo) <= 0) {
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("input data error");
        }
        return false;
    }

    private int indexOfArray(Employee[] stringArray, String element) {
        int index = -1;
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].getName().equals(element)) {
                index = i;
            }
        }
        return index;
    }
}
