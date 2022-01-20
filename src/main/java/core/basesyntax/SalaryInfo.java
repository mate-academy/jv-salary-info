package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryBilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        int paimant = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                Boolean found = Arrays.asList(data[j].split(" ")).contains(names[i]);
                if (found) {
                    if (getDay(data[j]).after(getDay(dateFrom))
                            | getDay(data[j]).equals(getDay(dateFrom))
                            && getDay(data[j]).before(getDay(dateTo))
                            | getDay(data[j]).equals(getDay(dateTo))) {
                        paimant += (getWorkingHour(data[j]) * getIncomePerHour(data[j]));
                    }
                }
            }
            salaryBilder.append(System.lineSeparator() + names[i]).append(" - " + paimant);
            paimant = 0;
        }
        return salaryBilder.toString();
    }

    public int getWorkingHour(String data) {
        return Integer.parseInt(data.split(" ")[2].replaceAll("[^0-9]",""));
    }

    public int getIncomePerHour(String data) {
        return Integer.parseInt(data.split(" ")[3].replaceAll("[^0-9]",""));
    }

    private static Date getDay(String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}


