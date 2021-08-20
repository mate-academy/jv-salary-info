package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder salaryInfo =
                new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        try {
            Date dateF = simpleDateFormat.parse(dateFrom);
            Date dateT = simpleDateFormat.parse(dateTo);
            for (String name : names) {
                int sumOfSalary = 0;
                for (String datum : data) {
                    String[] parseDatum = datum.split(" ");
                    int salary = 0;
                    for (String datum1 : data) {
                        String[] parseDatum1 = datum1.split(" ");
                        try {
                            Date tempDate = simpleDateFormat.parse(parseDatum[0]);
                            Date tempDate1 = simpleDateFormat.parse(parseDatum1[0]);
                            if (tempDate.after(dateF) && tempDate.before(dateT)
                                    && tempDate.equals(tempDate1) || tempDate.equals(dateF)
                                    || tempDate.equals(dateT)) {
                                if (name.equals(parseDatum[1])) {
                                    salary += name.equals(parseDatum1[1])
                                            ? Integer.parseInt(parseDatum1[2])
                                            * Integer.parseInt(parseDatum1[3])
                                            : 0;
                                }
                            }
                        } catch (ParseException e) {
                            continue;
                        }
                    }
                    sumOfSalary += salary;
                }
                salaryInfo.append("\n").append(name).append(" - ").append(sumOfSalary);
            }
        } catch (ParseException e) {
            return "Invalid input dates";
        }
        return salaryInfo.toString();
    }
}
