package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            String[] salaryWorkers = new String[names.length];
            String pattern = "dd.MM.yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date startWork = sdf.parse(dateFrom);
            Date finishWork = sdf.parse(dateTo);
            String[] returnStrings = new String[names.length];
            int[] sums = new int[names.length];
            for (int a = 0; a < names.length; a++) {
                String someName = names[a];
                sums[a] = 0;
                for (int b = 0; b < data.length; b++) {
                    String dataString = data[b];
                    if (dataString.contains(someName)) {
                        String[] someData = dataString.split(" ");
                        Date dayWork = sdf.parse(someData[0]);
                        int ret1 = dayWork.compareTo(startWork);
                        int ret2 = dayWork.compareTo(finishWork);
                        if (ret1 >= 0 && ret2 <= 0) {
                            int c = Integer.parseInt(someData[2]);
                            int d = Integer.parseInt(someData[3]);
                            sums[a] = sums[a] + c * d;
                        }
                    }
                }
                String output1 = names[a];
                int output2 = sums[a];
                String outputString = output1.toString() + " - " + output2;
                returnStrings[a] = outputString;
            }
            String answer = "Report for period " + dateFrom + " - " + dateTo;
            for (int f = 0; f < returnStrings.length; f++) {
                answer = answer + System.lineSeparator() + returnStrings[f];
            }
            return answer;
        } catch (ParseException pe) {
            return "exception-wrecker" + pe;
        }
    }
}
