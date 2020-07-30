package core.basesyntax;

public class DataLineInfo {
    private String date;
    private String name;
    private int hours;
    private int payment;

    public DataLineInfo(String data) {
        this.date = data.split(" ")[0];
        this.name = data.split(" ")[1];
        this.hours = Integer.parseInt(data.split(" ")[3]);
        this.payment = Integer.parseInt(data.split(" ")[2]);
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getPayment() {
        return payment;
    }
}

