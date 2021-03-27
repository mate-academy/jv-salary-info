package core.basesyntax;

import core.basesyntax.model.DateParser;
import core.basesyntax.model.DateValidator;
import core.basesyntax.model.exception.IncorrectDateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SalaryInfo {
    private DateValidator validator = new DateValidator();
    private DateParser parser = new DateParser();

    /**
     * Метод должен вернуть строку (информацию о зарплате сотрудника) в формате:
     * Report for period #date_1# - #date_2#
     * #Name of employee# - #money earned#
     * Проверяет валидность формата даты
     * и обрабатывает все исключения IncorrectDateFormat.
     * В цикле foreach получить имя текущего работника.
     * Создать два объекта класса Calendar:
     * - currentDate (будет равен переменной dateFrom)
     * - lastDate (будет равен переменной dateTo)
     * После выполнения метода getSalaryForEmployee() получить имя следующего работника.
     * Добавить результат выполнения в переменную report.
     * Пересоздать объекты класса Calendar.
     */

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        try {
            if (checkDate(dateFrom) && checkDate(dateTo)) {
                for (String name : names) {
                    Calendar currentDate = createCalendarDate(dateFrom);
                    Calendar lastDate = createCalendarDate(dateTo);
                    report.append(name)
                            .append(" - ")
                            .append(getSalaryForEmployee(data, currentDate, lastDate, name))
                            .append("\n");
                }
            }
        } catch (NullPointerException | IncorrectDateFormat ex) {
            System.out.println("Incorrect date format!\n"
                    + ex.getMessage()
                    + "\n"
                    + ex.getStackTrace().toString());
        }

        // TODO удалить последний символ '\n'

        return report.substring(0, report.length() - 1);
    }

    /**
     * Метод будет принимает массив data с данными работников.
     * Два объекта Calendar (currentDate и lastDate)
     * currentDate -> dateFrom
     * lastDate -> dateTo
     * Строку с именем работника.
     * Вернет переменную salary с доходом текущего работника
     * за период от dateFrom(currentDate) до dateTo(LastDate).
     * В метод calculateSalary() передается уже разделенная строка.
     * Пример:
     * arg => [11.08.2019 Andrew 8 100]
     * parser.parseEmployeeDate(arg, date) вернет [8 100].
     */

    private int getSalaryForEmployee(String[] data,
                                     Calendar currentDate,
                                     Calendar lastDate,
                                     String name) {
        int salary = 0;
        while (!currentDate.after(lastDate)) {
            String date = concatDateWithName(name, currentDate);
            for (String arg : data) {
                if (arg.contains(date)) {
                    salary += calculateSalary(parser.parseEmployeeDate(arg, date));
                }
            }
            currentDate.add(Calendar.DAY_OF_MONTH, +1);
        }
        return salary;
    }

    /**
     * Этот метод выполнит конкатенацию строки name и объекта date преобразованного в строку.
     * Оъект formatter будет иметь определенный шаблон даты (dd.MM.yyyy)
     * и создавать строку с правильным форматом даты.
     * Пример:
     * name => John
     * date.getTimeInMillis() => 1212121212121L
     * return [30.05.2008 John ]
     */

    private String concatDateWithName(String name, Calendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(date.getTimeInMillis()) + " " + name + " ";
    }

    /**
     * Метод создает объект класса Calendar.
     * Принимает строку date.
     * Разделяет ее на год, месяц и день.
     * И помещает эти значание в новый объект класса
     * new GregorianCalendar(year, month, day).
     */

    private Calendar createCalendarDate(String date) {
        int year = parser.getYear(date);
        int month = parser.getMonth(date) - 1;
        int day = parser.getDay(date);
        return new GregorianCalendar(year, month, day);
    }

    /**
     * Этот метод рассчитывает зарплату.
     * Принимает уже обработаную строку из массива date.
     * Строкой будет только кол-во часов и доход работника за час.
     */

    private int calculateSalary(String hoursAndRate) {
        String[] args = hoursAndRate.split(" ");
        return Integer.parseInt(args[0]) * Integer.parseInt(args[1]);
    }

    /**
     * Метод в который помещенны проверки из класса DateValidator.
     */

    private boolean checkDate(String date) throws IncorrectDateFormat {
        return validator.isDateNumber(date)
                && validator.isCorrectDateFormat(date)
                && validator.isCorrectDateLength(date);
    }

}
