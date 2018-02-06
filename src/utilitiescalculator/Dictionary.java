package utilitiescalculator;

import java.util.*;

public enum Dictionary {
    INSTANCE;

    public enum Language {
        UKRAINIAN, RUSSIAN
    }

    private static Language language;

    public enum Keyword {
        TITLE_MAIN, TITLE_ELEC, TITLE_GAS, TITLE_PERSONAL, TITLE_PRINT,
        PANEL_DATE, PANEL_ELEC, PANEL_GAS, PANEL_PAYMENT, PANEL_TOTAL, PANEL_BORDERED_ELEC_VOLUME, PANEL_ELEC_PRICE,
            PANEL_PRIVILEGE, PANEL_ELEC_MAX_VALUE, PANEL_GAS_PRICE, PANEL_GAS_MAX_VALUE, PANEL_NAMES, PANEL_ADDRESS,
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC,
        BUTTON_TARIFF, BUTTON_CALCULATE, BUTTON_PERSONAL_DATA, BUTTON_VIEW_AND_PRINT, BUTTON_SAVE, BUTTON_CANCEL,
            BUTTON_FONT, BUTTON_LANGUAGE, BUTTON_LANG_DESCRIPTION, BUTTON_PRINT,
        ELEC, RENT, HEAT, HOT_WATER, COLD_WATER, SEVERAGE, GAS, GARBAGE, INTERCOM, TV,
        MONTH, YEAR, BEGIN, END, DIFF, TOTAL, VOLUME, BELOW_BORDER, ABOVE_BORDER, PRIVILEGE_VALUE, PRICE, PERSONAL_ACCOUNT, 
            SURNAME, FIRST_NAME, PATRONYMIC, STREET, HOUSE, APARTMENT, 
        KWH, MCUBIC, HRN, CENTS, PERCENT, GRN_PER_MCUBIC,
        LINE_00, LINE_01, LINE_02, LINE_03, LINE_04, LINE_05, LINE_06, LINE_07, LINE_08, LINE_09, LINE_10, LINE_11,
            LINE_12, LINE_13, LINE_14, LINE_15, LINE_16, LINE_17, LINE_18, LINE_19, LINE_20
    }

    private static final Map<Keyword, String[]> DICTIONARY = new EnumMap<Keyword, String[]>(Keyword.class) {{
        // frames
        put(Keyword.TITLE_MAIN, new String[]{"Сплата комунальних послуг", "Оплата коммунальных услуг"});
        put(Keyword.TITLE_ELEC, new String[]{"Тариф на електроенергію", "Тариф на электроенергию"});
        put(Keyword.TITLE_GAS, new String[]{"Тариф на газ", "Тариф на газ"});
        put(Keyword.TITLE_PERSONAL, new String[]{"Персональні дані платника", "Персональные данные плательщика"});
        put(Keyword.TITLE_PRINT, new String[]{"Перегляд і роздруківка", "Просмотр и печать"});
        // titled borders
        put(Keyword.PANEL_DATE, new String[]{"Дата", "Дата"});
        put(Keyword.PANEL_ELEC, new String[]{"Показання електричного лічильника", "Показания электрического счётчика"});
        put(Keyword.PANEL_GAS, new String[]{"Показання газового лічильника", "Показания газового счётчика"});
        put(Keyword.PANEL_PAYMENT, new String[]{"Платежі", "Платежи"});
        put(Keyword.PANEL_TOTAL, new String[]{"Всьго", "Всего"});
        put(Keyword.PANEL_BORDERED_ELEC_VOLUME, new String[]{"Граничний обсяг електроенергії", "Граничный объём электроенергии"});
        put(Keyword.PANEL_ELEC_PRICE, new String[]{"Ціна на електроенергію", "Цена на электроенергию"});
        put(Keyword.PANEL_PRIVILEGE, new String[]{"Пільга", "Льгота"});
        put(Keyword.PANEL_ELEC_MAX_VALUE, new String[]{"Максимальне значення електролічильника", "Максимальное значение электросчётчика"});
        put(Keyword.PANEL_GAS_PRICE, new String[]{"Ціна на газ", "Цена на газ"});
        put(Keyword.PANEL_GAS_MAX_VALUE, new String[]{"Максимальне значення газового лічильника", "Максимальное значение электрического счётчика"});
        put(Keyword.PANEL_NAMES, new String[]{"Прізвище, ім’я та по батькові", "Фамилия, имя и отчество"});
        put(Keyword.PANEL_ADDRESS, new String[]{"Адреса", "Адрес"});
        // combo boxes
        put(Keyword.JAN, new String[]{"Січень", "Январь"});
        put(Keyword.FEB, new String[]{"Лютий", "Ферваль"});
        put(Keyword.MAR, new String[]{"Березень", "Март"});
        put(Keyword.APR, new String[]{"Квітень", "Апрель"});
        put(Keyword.MAY, new String[]{"Травень", "Май"});
        put(Keyword.JUN, new String[]{"Червень", "Июнь"});
        put(Keyword.JUL, new String[]{"Липень", "Июль"});
        put(Keyword.AUG, new String[]{"Серпень", "Август"});
        put(Keyword.SEP, new String[]{"Вересень", "Сентябрь"});
        put(Keyword.OCT, new String[]{"Жовтень", "Октябрь"});
        put(Keyword.NOV, new String[]{"Листопад", "Ноябрь"});
        put(Keyword.DEC, new String[]{"Грудень", "Декабрь"});
        // buttons
        put(Keyword.BUTTON_TARIFF, new String[]{"Тариф...", "Тариф..."});
        put(Keyword.BUTTON_CALCULATE, new String[]{"Розрахувати", "Рассчитать"});
        put(Keyword.BUTTON_PERSONAL_DATA, new String[]{"Персональні дані...", "Персональные данные..."});
        put(Keyword.BUTTON_VIEW_AND_PRINT, new String[]{"Роздрукувати...", "Распечатать"});
        put(Keyword.BUTTON_SAVE, new String[]{"Зберегти", "Сохранить"});
        put(Keyword.BUTTON_CANCEL, new String[]{"Відмінити", "Отменить"});
        put(Keyword.BUTTON_FONT, new String[]{"Шрифт", "Шрифт"});
        put(Keyword.BUTTON_LANGUAGE, new String[]{"Мова", "Язык"});
        put(Keyword.BUTTON_LANG_DESCRIPTION, new String[]{"Укр", "Рус"});
        put(Keyword.BUTTON_PRINT, new String[]{"Роздрукувати", "Распечатать"});
        // check boxes
        put(Keyword.ELEC, new String[]{"01 Електроенергія:", "01 Электроэнергия:"});
        put(Keyword.RENT, new String[]{"02 Квартплата:", "02 Квартплата:"});
        put(Keyword.HEAT, new String[]{"03 Опалення:", "03 Отопление:"});
        put(Keyword.HOT_WATER, new String[]{"04 Гаряча вода:", "04 Горячая вода:"});
        put(Keyword.COLD_WATER, new String[]{"05 Холодна вода:", "05 Холодная вода:"});
        put(Keyword.SEVERAGE, new String[]{"06 Каналізація:", "06 Канализация:"});
        put(Keyword.GAS, new String[]{"07,08 Газ природній:", "07,08 Газ природный:"});
        put(Keyword.GARBAGE, new String[]{"33 Вивіз сміття:", "33 Вывоз мусора:"});
        put(Keyword.INTERCOM, new String[]{"35 Домофон:", "35 Домофон:"});
        put(Keyword.TV, new String[]{"49 Воля Т.П.:", "49 Воля Т.П.:"});
        // labels (before)
        put(Keyword.MONTH, new String[]{"Місяць:", "Месяц:"});
        put(Keyword.YEAR, new String[]{"Рік:", "Год:"});
        put(Keyword.BEGIN, new String[]{"Початкові:", "Начальные:"});
        put(Keyword.END, new String[]{"Кінцеві:", "Конечные:"});
        put(Keyword.DIFF, new String[]{"Різниця:", "Разница:"});
        put(Keyword.TOTAL, new String[]{"Загалом:", "Итого:"});
        put(Keyword.VOLUME, new String[]{"Граничний обсяг електроенергії (ГОЕ):", "Граничный объём электроэнергии (ГОЭ):"});
        put(Keyword.BELOW_BORDER, new String[]{"За обсяг, спожитий до ГОЕ:", "За объём, потреблённый до ГОЭ:"});
        put(Keyword.ABOVE_BORDER, new String[]{"За обсяг, спожитий понад ГОЕ:", "За объём, потреблённый сверх ГОЭ:"});
        put(Keyword.PRIVILEGE_VALUE, new String[]{"Розмір пільги:", "Размер льготы:"});
        put(Keyword.PRICE, new String[]{"Ціна:", "Цена:"});
        put(Keyword.PERSONAL_ACCOUNT, new String[]{"Особистий рахунок:", "Персональный счёт:"});
        put(Keyword.SURNAME, new String[]{"Прізвище:", "Фамилия:"});
        put(Keyword.FIRST_NAME, new String[]{"Ім’я:", "Имя:"});
        put(Keyword.PATRONYMIC, new String[]{"По батькові:", "Отчество:"});
        put(Keyword.STREET, new String[]{"Вулиця:", "Улица:"});
        put(Keyword.HOUSE, new String[]{"Будинок:", "Дом:"});
        put(Keyword.APARTMENT, new String[]{"Квартира:", "Квартира:"});
        // labels (after)
        put(Keyword.KWH, new String[]{"кВт·год", "кВт·час"});
        put(Keyword.MCUBIC, new String[]{"м куб", "м куб"});
        put(Keyword.HRN, new String[]{"грн", "грн"});
        put(Keyword.CENTS, new String[]{"копійок", "копеек"});
        put(Keyword.PERCENT, new String[]{"%", "%"});
        put(Keyword.GRN_PER_MCUBIC, new String[]{"грн за м куб", "грн за м куб"});
        // print lines
        put(Keyword.LINE_00, new String[]{"СПЛАТА ЗА КОМУНАЛЬНІ ПОСЛУГИ", null});
        put(Keyword.LINE_01, new String[]{"ВАТ «Мегабанк» рахунок одержувача 290231 МФО 351629 ЄДРПОУ 09804119", null});
        put(Keyword.LINE_02, new String[]{"Особистий рахунок:", null});//+
        put(Keyword.LINE_03, new String[]{"Прізвище, ім’я, по батькові:", null});
        put(Keyword.LINE_04, new String[]{"Адреса:", null});
        put(Keyword.LINE_05, new String[]{"вул.", null});
        put(Keyword.LINE_06, new String[]{"буд.", null});
        put(Keyword.LINE_07, new String[]{"кв.", null});
        put(Keyword.LINE_08, new String[]{"Пільга, %", null});
        put(Keyword.LINE_09, new String[]{"Вид платежу", null});
        put(Keyword.LINE_10, new String[]{"Міс.", null});
        put(Keyword.LINE_11, new String[]{"Рік", null});
        put(Keyword.LINE_12, new String[]{"Сума", null});
        put(Keyword.LINE_13, new String[]{"Показання лічильників", null});
        put(Keyword.LINE_14, new String[]{"Кінцеві", null});
        put(Keyword.LINE_15, new String[]{"Початкові", null});
        put(Keyword.LINE_16, new String[]{"Різниця", null});
        put(Keyword.LINE_17, new String[]{"Тариф", null});
        put(Keyword.LINE_18, new String[]{"Інші", null});
        put(Keyword.LINE_19, new String[]{"Усього:", null});
        put(Keyword.LINE_20, new String[]{"Підпис платника:", null});
    }};

    public void setLanguage(Language language) {
        Dictionary.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public String getWord(Keyword key) {
        return DICTIONARY.get(key)[language.ordinal()] != null ? 
                DICTIONARY.get(key)[language.ordinal()] :           // use current language
                DICTIONARY.get(key)[Language.UKRAINIAN.ordinal()];  // use ukrainian
    }

    public String getText() {
        return INSTANCE.getWord(Keyword.BUTTON_LANGUAGE) + "(" + INSTANCE.getWord(Keyword.BUTTON_LANG_DESCRIPTION) + ")";
    }

    public Language getNextLanguage() {
        Language[] languages = Language.values();
        int next = (language.ordinal() + 1) % languages.length;
        language = languages[next];
        return language;
    }

    public static void main(String[] args) {
        Dictionary dict = Dictionary.INSTANCE;
        
        EnumSet.allOf(Dictionary.Keyword.class).forEach((key) -> {
            dict.setLanguage(Dictionary.Language.UKRAINIAN);
            System.out.format("%-26s -> %-68s -> ", key, dict.getWord(key));
            dict.setLanguage(Dictionary.Language.RUSSIAN);
            System.out.format("%s\n", dict.getWord(key));
        });
    }
}
