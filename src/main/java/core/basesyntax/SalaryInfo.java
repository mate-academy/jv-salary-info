package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class SalaryInfo {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        Date startDate = parseDate(dateFrom);
        Date endDate = parseDate(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] dataPart = data[j].split(" ");
                if (dataPart[1].equals(names[i])) {
                    Date dateFromData = parseDate(dataPart[0]);
                    if ((dateFromData.equals(startDate) & dateFromData.equals(endDate))
                            || (dateFromData.after(startDate) & dateFromData.before(endDate))
                            || (dateFromData.equals(startDate) & dateFromData.before(endDate))
                            || (dateFromData.after(startDate) & dateFromData.equals(endDate))) {
                        salary += Integer.parseInt(dataPart[2]) * Integer.parseInt(dataPart[3]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }

    private Date parseDate(String date) {
        try {
            Date parsedDate = simpleDateFormat.parse(date);
            return parsedDate;
        } catch (ParseException e) {
            throw new RuntimeException("I can`t parse date" + date, e);
        }
    }
}
