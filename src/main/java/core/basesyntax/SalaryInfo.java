package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException, ParseException {
        StringBuilder resultString = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date formattedDateFrom = simpleDateFormat.parse(dateFrom);
        Date formattedDateTo = simpleDateFormat.parse(dateTo);

        if (formattedDateFrom.after(formattedDateTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        resultString
                .append("Отчёт за период ")
                .append(simpleDateFormat.format(formattedDateFrom))
                .append(" - ")
                .append(simpleDateFormat.format(formattedDateTo))
                .append("\n");

        for (String name : names) {
            int sumOfMoney = 0;
            resultString
                    .append(name)
                    .append(" - ");

            for (String personInfo : data) {
                String[] substringPersonInfo = personInfo.split(" ");
                Date personDate = simpleDateFormat.parse(substringPersonInfo[0]);

                if (!(personDate.before(formattedDateFrom) || personDate.after(formattedDateTo))
                        && substringPersonInfo[1].equals(name)) {
                    sumOfMoney += Integer.parseInt(substringPersonInfo[2])
                            * Integer.parseInt(substringPersonInfo[3]);
                }
            }
            resultString
                    .append(sumOfMoney)
                    .append("\n");
        }
        resultString.deleteCharAt(resultString.length() - 1);
        return resultString.toString();
    }
}
