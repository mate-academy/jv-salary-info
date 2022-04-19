package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        long start = System.nanoTime();
        final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.y");
        int[] salaries = new int[names.length];
        try {
            Date dateFromDataType = formatter.parse(dateFrom);
            Date dateToDataType = formatter.parse(dateTo);

            for (int i = 0; i < data.length; i++) {
                String[] separateData = data[i].split(" ");
                if ((formatter.parse(separateData[0]).after(dateFromDataType)
                        || formatter.parse(separateData[0]).equals(dateFromDataType))
                        && (formatter.parse(separateData[0]).before(dateToDataType)
                        || formatter.parse(separateData[0]).equals(dateToDataType))) {
                    for (int j = 0; j < names.length; j++) {
                        if (names[j].equals(separateData[1])) {
                            salaries[j] += Integer.parseInt(separateData[2])
                                    * Integer.parseInt(separateData[3]);
                        }
                    }
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException("Sorry, unparseable date");
        }
        StringBuilder answer = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            answer.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaries[i]);
        }
        long end = System.nanoTime();
        System.out.println(end - start);
        return answer.toString();
    }
}
