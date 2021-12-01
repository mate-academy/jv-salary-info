package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder();
        int addSalar = 0;
        int indexArray;
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        Employee[] employeesArray = new Employee[names.length];

        for (int i = 0; i < names.length; i++) {
            Employee employee = new Employee(names[i], 0);
            employeesArray[i] = employee;
        }

        for (String curent : data) {
            String[] content = curent.split(" ");

            if (checkDate(content[0], dateFrom, dateTo)) {

                int result = 0;
                try {
                    addSalar = Integer.parseInt(content[2]) * Integer.parseInt(content[3]);
                } catch (RuntimeException e) {
                    System.out.println("input data error");
                }

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
            Date curentDate = new SimpleDateFormat("dd.MM.yyyy").parse(checkedDate);
            Date dateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(from);
            Date dateTo = new SimpleDateFormat("dd.MM.yyyy").parse(to);

            if (curentDate.compareTo(dateFrom) >= 0 && curentDate.compareTo(dateTo) <= 0) {
                return true;
            }
        } catch (ParseException e) {
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
