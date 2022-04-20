package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.y");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        try {
            Date dateFromDataType = FORMATTER.parse(dateFrom);
            Date dateToDataType = FORMATTER.parse(dateTo);

            for (int i = 0; i < data.length; i++) {
                String[] separateData = data[i].split(" ");
                Date workingDate = FORMATTER.parse(separateData[0]);
                if ((workingDate.after(dateFromDataType)
                        || workingDate.equals(dateFromDataType))
                        && (workingDate.before(dateToDataType)
                        || workingDate.equals(dateToDataType))) {
                    for (int j = 0; j < names.length; j++) {
                        if (names[j].equals(separateData[1])) {
                            salaries[j] += Integer.parseInt(separateData[2])
                                    * Integer.parseInt(separateData[3]);
                        }
                    }
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException("Sorry, unparseable date" + e);
        }
        StringBuilder answer = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            answer.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaries[i]);
        }
        return answer.toString();
    }
}
