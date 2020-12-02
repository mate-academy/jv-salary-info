package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Report for period ");
        stringBuilder.append(dateFrom);
        stringBuilder.append(" - ");
        stringBuilder.append(dateTo);
        stringBuilder.append("\n");

        int salary = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date firstDate = null;
        Date secondDate = null;
        try {
            firstDate = sdf.parse(dateFrom);
            secondDate = sdf.parse(dateTo);
        } catch (ParseException e) {
            System.out.println("Date parsing error");
        }

        String[] splitData;

        for (String name: names) {
            for (int i = 0; i < data.length; i++) {
                try {
                    splitData = data[i].split(" ");
                    if (splitData[1].equals(name)
                            && (firstDate.before(sdf.parse(splitData[0]))
                            || firstDate.equals(sdf.parse(splitData[0])))
                            && (secondDate.after(sdf.parse(splitData[0]))
                            || secondDate.equals(sdf.parse(splitData[0])))) {
                        salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                    }
                } catch (ParseException e) {
                    System.out.println("Date parsing error");
                }

            }
            stringBuilder.append(name);
            stringBuilder.append(" - ");
            stringBuilder.append(salary);
            stringBuilder.append("\n");
            salary = 0;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
