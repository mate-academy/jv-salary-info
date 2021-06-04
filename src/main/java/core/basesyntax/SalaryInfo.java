package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static final int INDEX_OF_SALARY_PER_HOUR = 2;
    private static final int HOURS = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        try {
            Date dateStart = dateFormat.parse(dateFrom);
            Date dateEnd = dateFormat.parse(dateTo);
            int[] salary = new int[names.length];
            for (int i = 0; i < salary.length; i++) {
                salary[i] = 0;
            }
            for (int i = 0; i < data.length; i++) {
                String[] distributedData = data[i].split(" ");
                Date inherimDate = dateFormat.parse(distributedData[0]);
                if ((inherimDate.compareTo(dateStart) >= 0)
                        && (inherimDate.compareTo(dateEnd) <= 0)) {
                    for (int j = 0; j < names.length; j++) {
                        if (names[j].equals(distributedData[1])) {
                            salary[j] += Integer.parseInt(distributedData[INDEX_OF_SALARY_PER_HOUR])
                                    * Integer.parseInt(distributedData[HOURS]);
                        }
                    }
                }
            }
            String s = "Report for period " + dateFrom + " - " + dateTo + "\n";
            for (int i = 0; i < names.length; i++) {
                s += names[i] + " - " + salary[i];
                if (i != (names.length - 1)) {
                    s += "\n";
                }
            }
            return s;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String [] names = new String[]{"Yura", "Yurka"};
        String [] data = {"26.04.2019 Yura 4 50", "05.04.2019 Yurka 3 200"};
        String dateFrom = "05.04.2019";
        String dateTo = "30.04.2021";
        String result = getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(result);
    }
}
