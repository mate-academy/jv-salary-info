
package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws ParseException {
        Date dateFromdate = getDate(dateFrom);
        Date dateTodate = getDate(dateTo);
        String salaryInfo = "";
        for (String name: names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                WorkingDay day = getInfoAboutWork(data[i]);
                String nameFromData = day.getName();
                int workinghours = day.getWorkingHours();
                int income = day.getIncome();
                Date date = day.getWorkingDay();
                if (name.equals(nameFromData) && (date.after(dateFromdate)
                        || date.equals(dateFromdate)) && (date.before(dateTodate)
                        || date.equals(dateTodate))) {
                    salary = salary + workinghours * income;
                }
            }
            salaryInfo = salaryInfo + System.lineSeparator() + name + " - " + salary;
        }
        return "Report for period " + dateFrom + " - " + dateTo + salaryInfo;
    }

    private WorkingDay getInfoAboutWork(String data) throws ParseException {
        String[] day = data.split(" ");
        Date date = getDate(day[0]);
        String name = day[1];
        int hours = Integer.parseInt(day[2]);
        int income = Integer.parseInt(day[3]);

        return new WorkingDay(date,name,hours,income);
    }

    private Date getDate(String date) throws ParseException {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (java.text.ParseException e) {
            throw new ParseException("Please Entry data in dd.MM.yyyy format");
        }
    }
}
