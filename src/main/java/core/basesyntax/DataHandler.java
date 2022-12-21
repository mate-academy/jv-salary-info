package core.basesyntax;

public class DataHandler {

    private StringBuilder stb = new StringBuilder();
    private String[] dates;
    private String[] namesData;
    private String[] hours;
    private String[] income;

    public void handler(String[] data) {
        dates = new String[data.length];
        namesData = new String[data.length];
        hours = new String[data.length];
        income = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            char[] recordChars = data[i].toCharArray();
            dates[i] = date(recordChars);
            namesData[i] = name(recordChars, dates[i]);
            hours[i] = hours(recordChars, dates[i], namesData[i]);
            income[i] = income(recordChars, dates[i], namesData[i], hours[i]);
        }
    }

    public String[] getDates() {
        return dates;
    }

    public String[] getNamesData() {
        return namesData;
    }

    public String[] getHours() {
        return hours;
    }

    public String[] getIncome() {
        return income;
    }

    public String date(char[] recordChars) {
        for (int i = 0; i < recordChars.length; i++) {
            if (recordChars[i] == ' ') {
                break;
            }
            stb.append(recordChars[i]);
        }
        String date = stb.toString();
        stb.delete(0, recordChars.length);
        return date;
    }

    public String name(char[] recordChars, String date) {
        for (int i = date.length() + 1; i < recordChars.length; i++) {
            if (recordChars[i] == ' ') {
                break;
            }
            stb.append(recordChars[i]);
        }
        String name = stb.toString();
        stb.delete(0, recordChars.length);
        return name;
    }

    public String hours(char[] recordChars, String date, String name) {
        for (int i = date.length() + name.length() + 2; i < recordChars.length; i++) {
            if (recordChars[i] == ' ') {
                break;
            }
            stb.append(recordChars[i]);
        }
        String hours = stb.toString();
        stb.delete(0, recordChars.length);
        return hours;
    }

    public String income(char[] recordChars, String date, String name, String hours) {
        for (int i = date.length() + name.length() + hours.length() + 3;
                i < recordChars.length; i++) {
            stb.append(recordChars[i]);
        }
        String income = stb.toString();
        stb.delete(0, recordChars.length);
        return income;
    }
}
