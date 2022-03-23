package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private Date date = new Date();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] arrayEmployee = new Employee[names.length];
        for (int i = 0; i < arrayEmployee.length; i++) {
            arrayEmployee[i] = new Employee(names[i]);
        }

        Date dateStart = getDate(dateFrom);
        Date dateEnd = getDate(dateTo);

        for (int i = 0; i < names.length; i++) {
            int sunSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] arrayData = data[j].split(" ");
                if (names[i].equals(arrayData[1])
                        && getDate(arrayData[0]).compareTo(dateStart) >= 0
                        && getDate(arrayData[0]).compareTo(dateEnd) <= 0) {

                    sunSalary += Integer.parseInt(arrayData[2])
                            * Integer.parseInt(arrayData[3]);
                }
                arrayEmployee[i].setSalary(sunSalary);
            }
        }
        return getOutputString(arrayEmployee, dateFrom, dateTo);
    }

    public Date getDate(String dateInString) {
        try {
            date = formatter.parse(dateInString);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("Can't convert " + dateInString + " in Date object");
        }
    }

    public String getOutputString(Employee[] arrayEmployee, String dateFrom, String dateTo) {
        String nextLine = System.lineSeparator();
        StringBuilder text = new StringBuilder("Report for period "
                                                + dateFrom
                                                + " - "
                                                + dateTo
                                                + nextLine);

        for (int i = 0; i < arrayEmployee.length; i++) {
            text.append(arrayEmployee[i].getName())
                    .append(" - ")
                    .append(arrayEmployee[i].getSalary())
                    .append(nextLine);
        }
        return text.substring(0,text.length() - 2);
    }
}
