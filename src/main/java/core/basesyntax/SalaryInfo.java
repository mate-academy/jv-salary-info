package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            for (String name : names) {
                int salary = 0;
                for (String employeeData : data) {
                    String[] separatedData = employeeData.split(" ");
                    Date localDate = dateFormat.parse(separatedData[0]);
                    Date fromDate = dateFormat.parse(dateFrom);
                    Date toDate = dateFormat.parse(dateTo);
                    if (!localDate.before(fromDate) && !localDate.after(toDate)) {
                        if (separatedData[1].equals(name)) {
                            int localSalary = Integer.parseInt(separatedData[2])
                                    * Integer.parseInt(separatedData[3]);
                            salary += localSalary;
                        }
                    }
                }
                stringBuilder.append(System.lineSeparator()).append(name)
                        .append(" - ").append(salary);
            }
        } catch (ParseException e) {
            System.out.println("Incorrect date format!");
        }
        return stringBuilder.toString();
    }
}
