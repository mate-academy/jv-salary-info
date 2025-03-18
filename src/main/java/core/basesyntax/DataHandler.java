package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataHandler {
    private DateParser dateParser;
    private UserInfo userInfo;
    private String[] names;

    public DataHandler(String[] names, String[] data, String dateFrom, String dateTo) {
        this.dateParser = new DateParser(dateFrom, dateTo);
        this.userInfo = new UserInfo();
        this.names = names;
        dataCheck(names, data);
    }

    public void dataCheck(String[] names,String[] data) {
        List<String> nameList = Arrays.asList(names);
        for (String value : data) {
            String[] s = value.split(" ");
            LocalDate date = dateParser.parseDate(s[0]);
            if (!date.isBefore(dateParser.getFromDate())
                    && !date.isAfter(dateParser.getToDate()) && nameList.contains(s[1])) {
                if (!userInfo.hasUser(s[1])) {
                    userInfo.addUserAndSalary(s[1], Integer.parseInt(s[2])
                            * Integer.parseInt(s[3]));
                } else {
                    userInfo.addSalary(s[1], Integer.parseInt(s[2]) * Integer.parseInt(s[3]));
                }
            }
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "Report for period " + dateParser.getFromDate().format(outputFormatter)
                + " - " + dateParser.getToDate().format(outputFormatter)
                + System.lineSeparator() + userInfo.getAllUsers(names);
    }
}
