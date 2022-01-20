package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        Date workDayDate;
        Date dateFrom1 = getDateFromString(dateFrom);
        Date dateTo1 = getDateFromString(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String dataUnit : data) {
                String[] splitDataUnit = dataUnit.split(" ");
                if (name.equals(splitDataUnit[1])) {
                    workDayDate = getDateFromString(splitDataUnit[0]);
                    if ((workDayDate.after(dateFrom1) | (workDayDate.equals(dateFrom1)))
                            && (workDayDate.before(dateTo1) | workDayDate.equals(dateTo1))) {
                        salary += (Integer.parseInt(splitDataUnit[2])
                                * Integer.parseInt(splitDataUnit[3]));
                    }
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }

    private static Date getDateFromString(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
