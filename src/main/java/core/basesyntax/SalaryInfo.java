package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date date = new Date();
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int salaryCalculator = 0;
        for (String name : names) {
            salaryCalculator = 0;
            result.append(System.lineSeparator()).append(name)
                    .append(" - ");
            for (String employeeData : data) {

                String[] splitedDate = employeeData.split("\\s+");
                try {
                    date = stringToDate(splitedDate[0]);
                    if (name.equals(splitedDate[1])
                            && date.after(stringToDate(dateFrom))
                            | stringToDate(dateFrom).equals(date)
                            && date.before(stringToDate(dateTo))
                            | stringToDate(dateTo).equals(date)) {
                        salaryCalculator += Integer.parseInt(splitedDate[2])
                                * Integer.parseInt(splitedDate[3]);
                    }
                } catch (ParseException e) {
                    System.out.println("Date parsing exception");
                }

            }
            result.append(salaryCalculator);
        }
        return result.toString();
    }

    public Date stringToDate(String string) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date date = formatter.parse(string);
        return date;
    }
}
