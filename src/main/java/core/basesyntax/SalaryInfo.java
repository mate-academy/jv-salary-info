package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] tempData;
        int salarySumm;
        Date firstDate = new Date();
        Date secondDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            firstDate = formatter.parse(dateFrom);
            secondDate = formatter.parse(dateTo);
        } catch (ParseException e) {
            System.out.println("Incorrect data");
        }

        Date workDate = null;
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator());
            salarySumm = 0;
            for (int j = 0; j < data.length; j++) {
                tempData = data[j].split(" ");
                try {
                    workDate = formatter.parse(tempData[0]);
                } catch (ParseException e) {
                    System.out.println("Incorrect data");
                }

                if (names[i].equals(tempData[1])
                        && workDate.compareTo(firstDate) >= 0
                        && workDate.compareTo(secondDate) <= 0) {
                    salarySumm = salarySumm
                            + Integer.parseInt(tempData[2]) * Integer.parseInt(tempData[3]);
                }
            }
            result.append(names[i]).append(" - ").append(salarySumm);
        }
        return result.toString();
    }
}
