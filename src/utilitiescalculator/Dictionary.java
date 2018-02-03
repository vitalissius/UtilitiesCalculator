package utilitiescalculator;

import java.util.*;

public enum Dictionary {
    INSTANCE;

    public enum Language {
        RUSSIAN, UKRAINIAN
    }

    private static Language language;

    public enum Keyword {
        PANEL_DATE, PANEL_ELEC, PANEL_GAS, PANEL_PAYMENT, PANEL_TOTAL,
        MONTH, YEAR,
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC,
        BEGIN, END, DIFF,
        KWH, MCUBIC, HRN,
        TOTAL,
        TARIFF, CALCULATE, VIEW_AND_PRINT, PERSONAL_DATA,
        ELEC, RENT, HEAT, HOT_WATER, COLD_WATER, SEVERAGE, GAS, GARBAGE, INTERCOM, TV,
        LANGUAGE, LANG_DESCRIPTION,
    }

    private static final Map<Keyword, String[]> DICTIONARY = new HashMap<Keyword, String[]>() {{
        put(Keyword.PANEL_DATE, new String[]{"Дата", "Дата"});
        put(Keyword.PANEL_ELEC, new String[]{"Показания электрического счётчика", "Показання електричного лічильника"});
        put(Keyword.PANEL_GAS, new String[]{"Показания газового счётчика", "Показання газового лічильника"});
        put(Keyword.PANEL_PAYMENT, new String[]{"Платежи", "Платежі"});
        put(Keyword.PANEL_TOTAL, new String[]{"Всего", "Всьго"});
        
        put(Keyword.MONTH, new String[]{"Месяц:", "Місяць:"});
        put(Keyword.YEAR, new String[]{"Год:", "Рік:"});
        
        put(Keyword.JAN, new String[]{"Январь", "Січень"});
        put(Keyword.FEB, new String[]{"Февраль", "Лютий"});
        put(Keyword.MAR, new String[]{"Март", "Березень"});
        put(Keyword.APR, new String[]{"Апрель", "Квітень"});
        put(Keyword.MAY, new String[]{"Май", "Травень"});
        put(Keyword.JUN, new String[]{"Июнь", "Червень"});
        put(Keyword.JUL, new String[]{"Июль", "Липень"});
        put(Keyword.AUG, new String[]{"Август", "Серпень"});
        put(Keyword.SEP, new String[]{"Сентябрь", "Вересень"});
        put(Keyword.OCT, new String[]{"Октябрь", "Жовтень"});
        put(Keyword.NOV, new String[]{"Ноябрь", "Листопад"});
        put(Keyword.DEC, new String[]{"Декабрь", "Грудень"});
        
        put(Keyword.BEGIN, new String[]{"Начальные:", "Початкові:"});
        put(Keyword.END, new String[]{"Конечные:", "Кінцеві:"});
        put(Keyword.DIFF, new String[]{"Разница:", "Різниця:"});
        
        put(Keyword.KWH, new String[]{"кВт" + (char) 0x2219 + "час", "кВт·год"});
        put(Keyword.MCUBIC, new String[]{"м куб", "м куб"});
        put(Keyword.HRN, new String[]{"грн", "грн"});
        
        put(Keyword.TOTAL, new String[]{"В целом:", "Загалом:"});
        
        put(Keyword.TARIFF, new String[]{"Тариф...", "Тариф..."});
        put(Keyword.CALCULATE, new String[]{"Рассчитать", "Розрахувати"});
        put(Keyword.VIEW_AND_PRINT, new String[]{"Просмотреть и распечатать...", "Переглянути і роздрукувати..."});
        put(Keyword.PERSONAL_DATA, new String[]{"Персональные данные плательщика...", "Персональні дані платника..."});
        
        put(Keyword.ELEC, new String[]{"01 Электроэнергия:", "01 Електроенергія:"});
        put(Keyword.RENT, new String[]{"02 Квартплата:", "02 Квартплата:"});
        put(Keyword.HEAT, new String[]{"03 Отопление:", "03 Опалення:"});
        put(Keyword.HOT_WATER, new String[]{"04 Горячая вода:", "04 Гаряча вода:"});
        put(Keyword.COLD_WATER, new String[]{"05 Холодная вода:", "05 Холодна вода:"});
        put(Keyword.SEVERAGE, new String[]{"06 Канализация:", "06 Каналізація:"});
        put(Keyword.GAS, new String[]{"07,08 Газ природный:", "07,08 Газ природній:"});
        put(Keyword.GARBAGE, new String[]{"33 Вывоз мусора:", "33 Вивіз сміття:"});
        put(Keyword.INTERCOM, new String[]{"35 Домофон:", "35 Домофон:"});
        put(Keyword.TV, new String[]{"49 Воля Т.П.:", "49 Воля Т.П.:"});
        put(Keyword.LANGUAGE, new String[]{"Язык", "Мова"});
        put(Keyword.LANG_DESCRIPTION, new String[]{"Рус", "Укр"});
    }};

    public void setLanguage(Language language) {
        Dictionary.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public String getWord(Keyword key) {
        return DICTIONARY.get(key)[language.ordinal()];
    }
    
    public String getText() {
        return INSTANCE.getWord(Keyword.LANGUAGE) + "(" + INSTANCE.getWord(Keyword.LANG_DESCRIPTION) + ")";
    }
    
    public Language getNextLanguage() {
        Language[] languages = Language.values();
        int next = (language.ordinal() + 1) % languages.length;
        language = languages[next];
        return language;
    }

    public static void main2(String[] args) {
        String[][] strings = new String[][] {
            {"Дата", "Дата"},
            {"Показания электрического счётчика", "Показання електричного лічильника"},
            {"Показания газового счётчика", "Показання газового лічильника"},
            {"Платежи", "Платежі"},
            {"Всего", "Всьго"},
            {"Месяц:", "Місяць:"},
            {"Год:", "Рік:"},
            {"Январь", "Січень"},
            {"Февраль", "Лютий"},
            {"Март", "Березень"},
            {"Апрель", "Квітень"},
            {"Май", "Травень"},
            {"Июнь", "Червень"},
            {"Июль", "Липень"},
            {"Август", "Серпень"},
            {"Сентябрь", "Вересень"},
            {"Октябрь", "Жовтень"},
            {"Ноябрь", "Листопад"},
            {"Декабрь", "Грудень"},
            {"Начальные:", "Початкові:"},
            {"Конечные:", "Кінцеві:"},
            {"Разница:", "Різниця:"},
            {"кВт" + (char) 0x2219 + "час", "кВт·год"},
            {"м куб", "м куб"},
            {"₴", "₴"},
            {"В целом:", "Загалом:"},
            {"Тариф...", "Тариф..."},
            {"Рассчитать", "Розрахувати"},
            {"Просмотреть и распечатать...", "Переглянути і роздрукувати..."},
            {"Персональные данные плательщика...", "Персональні дані платника..."},
            {"01 Электроэнергия:", "01 Електроенергія:"},
            {"02 Квартплата:", "02 Квартплата:"},
            {"03 Отопление:", "03 Опалення:"},
            {"04 Горячая вода:", "04 Гаряча вода:"},
            {"05 Холодная вода:", "05 Холодна вода:"},
            {"06 Канализация:", "06 Каналізація:"},
            {"07,08 Газ природный:", "07,08 Газ природній:"},
            {"33 Вывоз мусора:", "33 Вивіз сміття:"},
            {"35 Домофон:", "35 Домофон:"},
            {"49 Воля Т.П.:", "49 Воля Т.П.:"},
            {"Шрифт()", "Шрифт()"},
            {"Мова()", "Язык()"},
            
            // PERSONAL_ACCOUNT, FLM_NAMES, LAST_NAME, FIRST_NAME, MIDDLE_NAME, ADDRESS, STREET, HOUSE, APARTMENT, SAVE, CANCEL,
            // TARIFFS, BORDERED_ELEC_VOLUME, VOLUME, ELEC_TARIFF, BELOW_BORDER, ABOVE_BORDER, CENTS, PRIVILEGE, PRIVILEGE_VALUE, PERCENT, GRN,
            {"Плательщик", "Платник"},// -
            {"Персональный счёт:", "Особистий рахунок:"},
            {"Ф.И.О.", "П.І.Б."},
            {"Фамилия:", "Прізвище:"},
            {"Имя:", "Ім’я:"},
            {"Отчество:", "По батькові:"},
            {"Адрес", "Адреса"},
            {"Улица:", "Вулиця:"},
            {"Дом:", "Будинок:"},
            {"Квартира:", "Квартира:"},
            {"Сохранить", "Зберегти"},
            {"Отменить", "Скасувати"},
            {"Тарифы", "Тарифи"},// -
            {"Граничный объём электроэнергии (ГОЭ)", "Граничний обсяг електроенергії (ГОЕ)"},
            {"Объём:", "Обсяг:"},
            {"Тарифы на электроэнергию для населения", "Тарифи на електроенергію для населення"},
            {"За объём, потреблённый до ГОЭ", "За обсяг, спожитий до ГОЕ:"},
            {"За объём, потреблённый сверх ГОЭ", "За обсяг, спожитий понад ГОЕ:"},
            {"Копеек", "Копійок"},
            {"Льгота", "Пільга"},
            {"Размер льготы:", "Розмір пільги:"},
            {"%", "%"},
            {"грн", "₴"},
            // LINE00, LINE01, LINE02, LINE03, LINE04, LINE05, LINE06, LINE07, LINE08, LINE09, 
            // LINE10, LINE11, LINE12, LINE13, LINE14, LINE15, LINE16, LINE17, LINE18, LINE19, 
            // LINE20, LINE21, LINE22, LINE23, 
            {"СПЛАТА ЗА КОМУНАЛЬНІ ПОСЛУГИ", ""},
            {"ВАТ «Мегабанк» рахунок одержувача 290231 МФО 351629 ЄДРПОУ 09804119", ""},
            {"Особистий рахунок:", ""},
            {"Прізвище, ім’я, по батькові:", ""},
            {"Адреса:", ""},
            {"вул.", ""},
            {"буд.", ""},
            {"кв.", ""},
            {"Пільга, %", ""},
            {"Вид платежу", ""},
            {"Міс.", ""},
            {"Рік", ""},
            {"Сума", ""},
            {"Показання лічильників", ""},
            {"Кінцеві", ""},
            {"Початкові", ""},
            {"Різниця", ""},
            {"Тариф", ""},
            {"Інші", ""},
            {"Усього:", ""},
            {"Підпис платника:", ""},
            {"Переглянути", ""},
            {"Роздрукувати", ""},
            {"Вийти з діалогу", ""},
        };
        
        
        for (Keyword key : Keyword.values()) {
            System.out.println(strings[key.ordinal()][Language.RUSSIAN.ordinal()]);
        }
    }
}
