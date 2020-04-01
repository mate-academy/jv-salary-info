package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */

    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {

        long dateFromInMillis = new SimpleDateFormat(DATE_PATTERN).parse(dateFrom).getTime();
        long dateToInMillis = new SimpleDateFormat(DATE_PATTERN).parse(dateTo).getTime();

        if (dateFromInMillis > dateToInMillis) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        long[] arrayDatesInMillis = new long[data.length];

        for (int i = 0; i < data.length; i++) {
            arrayDatesInMillis[i] = new SimpleDateFormat(DATE_PATTERN)
                    .parse(data[i].split(" ")[0]).getTime();
        }

        int[] salaryOfNames = new int[names.length];

        for (int i = 0; i < salaryOfNames.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (dateFromInMillis <= arrayDatesInMillis[j]
                        && dateToInMillis >= arrayDatesInMillis[j]) {
                    if (data[j].contains(names[i])) {
                        String[] dataLineArray = data[j].split(" ");
                        salaryOfNames[i] += Integer.parseInt(dataLineArray[2])
                                * Integer.parseInt(dataLineArray[3]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");

        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(salaryOfNames[i]);
            if (i != names.length - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
