package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.valueOf;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        String resultArray[][] = new String[names.length][2];
        for (int i = 0; i < names.length; i++) {    //feel names in result array
            resultArray[i][0] = names[i];
            resultArray[i][1] = "0";
        }
        for (int i = 0; i < data.length; i++) {
            String[] divideByWhitespace = data[i].split(" ");
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            try {
                Date dDate = formatter.parse(divideByWhitespace[0]);
                Date dDateFrom = formatter.parse(dateFrom);
                Date dDateTo = formatter.parse(dateTo);
                if ((dDate.after(dDateFrom) && dDate.before(dDateTo)) ||
                        dDate.equals(dDateFrom) || dDate.equals(dDateTo)) {
                    String name = divideByWhitespace[1];
                    for (int j = 0; j < names.length; j++) {
                        if (name.equals(resultArray[j][0])) {
                            resultArray[j][1] = valueOf(Integer.parseInt(resultArray[j][1])
                                    + Integer.parseInt(divideByWhitespace[2])
                                    *Integer.parseInt(divideByWhitespace[3]));
                        }
                    }
                }
            } catch (ParseException e) {
                System.out.println("Invalid Date format");
                throw new RuntimeException(e);
            }
        }
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).
                append(System.lineSeparator());
        for (int i = 0; i < names.length - 1; i++){
            resultBuilder.append(resultArray[i][0]).append(" - ").append(resultArray[i][1]).append(System.lineSeparator());
        }
        resultBuilder.append(resultArray[names.length-1][0]).append(" - ").append(resultArray[names.length - 1][1]);
        String resultString = resultBuilder.toString();
        return resultString;
    }
}
