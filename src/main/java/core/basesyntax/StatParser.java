package core.basesyntax;

public class StatParser {
    private static final String SEPARATOR = " ";

    public static InputStat parse(String stat) {
        String[] values = stat.split(SEPARATOR);
        InputStat inputStat = new InputStat(
                DateParser.parseDate(values[0]),
                values[1],
                Integer.parseInt(values[2]),
                Integer.parseInt(values[3]));
        return inputStat;
    }
}
