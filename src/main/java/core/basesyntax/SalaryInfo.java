package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo extends CheckNotNull {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        checkNotNull(names, data, dateFrom, dateTo);
        String[] fullInformation = new String[names.length];
        StringBuilder builder;
        StringBuilder finalBuilder = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        short builderCounter = 0;
        for (String name : names) {
            int sum = 0;
            builder = new StringBuilder();
            for (String note : data) {
                if (!note.isEmpty()) {
                    try {
                        String[] dataSeparated = note.split(" ");
                        Date parsedNoteDate = format.parse(dataSeparated[0]);
                        Date parsedDateFrom = format.parse(dateFrom);
                        Date parsedDateTo = format.parse(dateTo);
                        if ((parsedNoteDate.after(parsedDateFrom)
                                && parsedNoteDate.before(parsedDateTo))
                                || (parsedNoteDate.equals(parsedDateFrom)
                                || parsedNoteDate.equals((parsedDateTo)))) {
                            if (dataSeparated[1].equals(name)) {
                                sum += Integer.parseInt(dataSeparated[2])
                                        * Integer.parseInt(dataSeparated[3]);
                            }
                        }
                    } catch (ParseException e) {
                        System.out.println("Cannot Parse Date: ParseException");
                    }
                }
            }
            fullInformation[builderCounter] = builder.append(name).append(" - ")
                    .append(sum).toString();
            builderCounter++;
        }
        finalBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String employee : fullInformation) {
            finalBuilder.append(System.lineSeparator()).append(employee);
        }
        return finalBuilder.toString();
    }
}
