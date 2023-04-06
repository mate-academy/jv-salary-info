package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String pattern = "dd.MM.yyyy";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date startWork = sdf.parse(dateFrom);
            Date finishWork = sdf.parse(dateTo);
            String[] returnStrings = new String[names.length];
            int[] sums = new int[names.length];
            for (int a = 0; a < names.length; a++) {
                String nameAtIndex  = names[a];
                sums[a] = 0;
                for (int b = 0; b < data.length; b++) {
                    String dataString = data[b];
                    if (dataString.contains(nameAtIndex )) {
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
                StringBuilder outputStringBuilder = new StringBuilder();
                outputStringBuilder.append(output1).append(" - ").append(output2);
                returnStrings[a] = outputStringBuilder.toString();
            }
            StringBuilder outputAnswer = new StringBuilder();
            outputAnswer.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
            for (int f = 0; f < returnStrings.length; f++) {
                outputAnswer.append(System.lineSeparator()).append(returnStrings[f]);
            }
            return outputAnswer.toString();
        } catch (ParseException pe) {
            return "exception-wrecker" + pe;
        }
    }
}
