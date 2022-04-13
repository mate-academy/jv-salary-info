package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_RATE_INDEX = 3;
    public static final int TOTAL_PARAMETERS = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            date2 = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //      Parse data into 3D Array
        String[][] dataParsed = new String[data.length][TOTAL_PARAMETERS];
        for (int i = 0; i < data.length; i++) {
            dataParsed[i] = data[i].split(" ");
        }

        int[] salaryTotal = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (int k = 0; k < data.length; k++) {
                Date date = new Date();
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy").parse(dataParsed[k][DATE_INDEX]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date.after(date1)
                        && (date.equals(date2) || date.before(date2))
                        && names[i].equals(dataParsed[k][NAME_INDEX])) {
                    salaryTotal[i] += Integer.parseInt(dataParsed[k][HOURS_INDEX])
                            * Integer.parseInt(dataParsed[k][SALARY_RATE_INDEX]);
                }
            }
        }

        //      Creating resulting StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]);
            stringBuilder.append(" - ");
            stringBuilder.append(salaryTotal[i]);
            if (i != names.length - 1) {
                stringBuilder.append("\n");
            }
        }

        String result = (
                "Report for period "
                + dateFrom
                + " - "
                + dateTo
                + "\n"
                + stringBuilder.toString()
        );

        return result;
    }
}
