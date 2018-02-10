package utilitiescalculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public final class Settings {
    private static final Properties PROPERTIES = new Properties();
    private static final String USER_HOME = System.getProperty("user.home") + File.separator;
    private static final Settings INSTANCE = new Settings();

    private Settings() {
    }

    public static Settings getInstance() {
        return INSTANCE;
    }

    private static enum Vls {
        ElecMeter("elec.meter", "false"),
        ElecBegin("elec.begin", "0"),
        ElecEnd("elec.end", "0"),
        ElecBoundary("elec.boundary", "0"),
        ElecPriceBelowBoundary("elec.priceBelowBoundary", "0.00"),
        ElecPriceAboveBoundary("elec.priceAboveBoundary", "0.00"),
        ElecPrivilege("elec.privilege", "0"),
        ElecMeterMaxValue("elec.meterMaxValue", "9999"),
        GasMeter("gas.meter", "false"),
        GasBegin("gas.begin", "0"),
        GasEnd("gas.end", "0"),
        GasPrice("gas.price", "0.00"),
        GasMeterMaxValue("gas.meterMaxValue", "99999"),
        PaymentsElec("payments.elec", "0.00"),
        PaymentsRent("payments.rent", "0.00"),
        PaymentsHeating("payments.heating", "0.00"),
        PaymentsHotWater("payments.hotWater", "0.00"),
        PaymentsColdWater("payments.coldWater", "0.00"),
        PaymentsSeverage("payments.severage", "0.00"),
        PaymentsGas("payments.gas", "0.00"),
        PaymentsGarbage("payments.garbage", "0.00"),
        PaymentsIntercom("payments.intercom", "0.00"),
        PaymentsTv("payments.tv", "0.00"),
        UsedElec("used.elec", "false"),
        UsedRent("used.rent", "false"),
        UsedHeating("used.heating", "false"),
        UsedHotWater("used.hotWater", "false"),
        UsedColdWater("used.coldWater", "false"),
        UsedSeverage("used.severage", "false"),
        UsedGas("used.gas", "false"),
        UsedGarbage("used.garbage", "false"),
        UsedIntercom("used.intercom", "false"),
        UsedTv("used.tv", "false"),
        PersonalAccount("personal.account", "000000000"),
        PersonalSurname("personal.surname", "Unknown surname"),
        PersonalFirstName("personal.firstName", "Unknown first name"),
        PersonalPatronymic("personal.patronymic", "Unknown patronymic"),
        PersonalStreet("personal.street", "Unknown street"),
        PersonalBuilding("personal.building", "Unknown building"),
        PersonalApartment("personal.apartment", "Unknown apartment"),
        WindowPositionX("window.x", "0"),
        WindowPositionY("window.y", "0"),
        FontSize("font.size", Resizer.FontSize.ELEVEN.toString()),
        Language("language", Dictionary.Language.UKRAINIAN.toString());

        private final String key;
        private String value;

        private Vls(String key, String defaultValue) {
            this.key = key;
            this.value = defaultValue;
        }

        String get() {
            return Settings.PROPERTIES.getProperty(key, value);
        }

        void set(String value) {
            this.value = value;
            Settings.PROPERTIES.setProperty(key, this.value);
        }
    }

    private boolean usedElecMeter;
    private int elecBegin;
    private int elecEnd;
    private int elecBoundary;
    private double elecPriceBelowBoundary;
    private double elecPriceAboveBoundary;
    private int elecPrivilege;
    private int elecMeterMaxValue;
    private boolean usedGasMeter;
    private int gasBegin;
    private int gasEnd;
    private double gasPrice;
    private int gasMeterMaxValue;
    private String personalAccount = "";
    private String personalSurname = "";
    private String personalFirstName = "";
    private String personalPatronymic = "";
    private String personalStreet = "";
    private String personalBuilding = "";
    private String personalApartment = "";
    private double paymentsElec;
    private double paymentsRent;
    private double paymentsHeating;
    private double paymentsHotWater;
    private double paymentsColdWater;
    private double paymentsSeverage;
    private double paymentsGas;
    private double paymentsGarbage;
    private double paymentsIntercom;
    private double paymentsTv;
    private boolean usedElec;
    private boolean usedRent;
    private boolean usedHeating;
    private boolean usedHotWater;
    private boolean usedColdWater;
    private boolean usedSeverage;
    private boolean usedGas;
    private boolean usedGarbage;
    private boolean usedIntercom;
    private boolean usedTv;
    private int windowPositionX;
    private int windowPositionY;
    private Resizer.FontSize fontSize;
    private Dictionary.Language language;

    /*
     * Цель функций getInt(), getDouble(), getFontSize() и getLanguage() заключается в предотвращении
     * возникновения исключений из-за того, что данные в файле настроек могут быть испорчены.
     * Функции getBoolean() и getString() используются просто для поддержания общего стиля кодирования
     * с вышеупомянутыми функциями.
     */
    private static int getInt(Vls vls, int defaultValue) {
        try {
            return Integer.parseInt(vls.get());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static int getInt(Vls vls) {
        return getInt(vls, 0);
    }

    private static double getDouble(Vls vls) {
        try {
            return Double.parseDouble(vls.get());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private static Resizer.FontSize getFontSize(Vls vls) {
        try {
            return Resizer.FontSize.valueOf(vls.get());
        } catch (IllegalArgumentException e) {
            return Resizer.FontSize.ELEVEN;
        }
    }

    private static Dictionary.Language getLanguage(Vls vls) {
        try {
            return Dictionary.Language.valueOf(vls.get());
        } catch (IllegalArgumentException e) {
            return Dictionary.Language.UKRAINIAN;
        }
    }

    private static boolean getBoolean(Vls vls) {
        return Boolean.valueOf(vls.get());
    }

    private static String getString(Vls vls) {
        return vls.get();
    }

    public void loadProperties(final String propertiesFileName) {
        try (BufferedReader input = 
                new BufferedReader(new InputStreamReader(new FileInputStream(USER_HOME + propertiesFileName)))) {
            PROPERTIES.load(input);
        } catch (FileNotFoundException e) {
            /* ignored */
        } catch (IOException e) {
            throw new RuntimeException("Unknown I/O exception: " + e.getMessage());
        }

        usedElecMeter = getBoolean(Vls.ElecMeter);
        elecBegin = getInt(Vls.ElecBegin);
        elecEnd = getInt(Vls.ElecEnd);
        elecBoundary = getInt(Vls.ElecBoundary);
        elecPriceBelowBoundary = getDouble(Vls.ElecPriceBelowBoundary);
        elecPriceAboveBoundary = getDouble(Vls.ElecPriceAboveBoundary);
        elecPrivilege = getInt(Vls.ElecPrivilege);
        elecMeterMaxValue = getInt(Vls.ElecMeterMaxValue, 9999);

        usedGasMeter = getBoolean(Vls.GasMeter);
        gasBegin = getInt(Vls.GasBegin);
        gasEnd = getInt(Vls.GasEnd);
        gasPrice = getDouble(Vls.GasPrice);
        gasMeterMaxValue = getInt(Vls.GasMeterMaxValue, 99999);

        paymentsElec = getDouble(Vls.PaymentsElec);
        paymentsRent = getDouble(Vls.PaymentsRent);
        paymentsHeating = getDouble(Vls.PaymentsHeating);
        paymentsHotWater = getDouble(Vls.PaymentsHotWater);
        paymentsColdWater = getDouble(Vls.PaymentsColdWater);
        paymentsSeverage = getDouble(Vls.PaymentsSeverage);
        paymentsGas = getDouble(Vls.PaymentsGas);
        paymentsGarbage = getDouble(Vls.PaymentsGarbage);
        paymentsIntercom = getDouble(Vls.PaymentsIntercom);
        paymentsTv = getDouble(Vls.PaymentsTv);

        usedElec = getBoolean(Vls.UsedElec);
        usedRent = getBoolean(Vls.UsedRent);
        usedHeating = getBoolean(Vls.UsedHeating);
        usedHotWater = getBoolean(Vls.UsedHotWater);
        usedColdWater = getBoolean(Vls.UsedColdWater);
        usedSeverage = getBoolean(Vls.UsedSeverage);
        usedGas = getBoolean(Vls.UsedGas);
        usedGarbage = getBoolean(Vls.UsedGarbage);
        usedIntercom = getBoolean(Vls.UsedIntercom);
        usedTv = getBoolean(Vls.UsedTv);

        personalAccount = getString(Vls.PersonalAccount);
        personalSurname = getString(Vls.PersonalSurname);
        personalFirstName = getString(Vls.PersonalFirstName);
        personalPatronymic = getString(Vls.PersonalPatronymic);
        personalStreet = getString(Vls.PersonalStreet);
        personalBuilding = getString(Vls.PersonalBuilding);
        personalApartment = getString(Vls.PersonalApartment);

        windowPositionX = getInt(Vls.WindowPositionX);
        windowPositionY = getInt(Vls.WindowPositionY);

        fontSize = getFontSize(Vls.FontSize);
        language = getLanguage(Vls.Language);
    }

    public void storeProperties(final String propertiesFileName) {
        Vls.ElecMeter.set(Boolean.toString(usedElecMeter));
        Vls.ElecBegin.set(Integer.toString(elecBegin));
        Vls.ElecEnd.set(Integer.toString(elecEnd));
        Vls.ElecPriceBelowBoundary.set(Double.toString(elecPriceBelowBoundary));
        Vls.ElecPriceAboveBoundary.set(Double.toString(elecPriceAboveBoundary));
        Vls.ElecPrivilege.set(Integer.toString(elecPrivilege));
        Vls.ElecMeterMaxValue.set(Integer.toString(elecMeterMaxValue));

        Vls.GasMeter.set(Boolean.toString(usedGasMeter));
        Vls.GasBegin.set(Integer.toString(gasBegin));
        Vls.GasEnd.set(Integer.toString(gasEnd));
        Vls.GasPrice.set(Double.toString(gasPrice));
        Vls.GasMeterMaxValue.set(Integer.toString(gasMeterMaxValue));

        Vls.PersonalAccount.set(personalAccount);
        Vls.PersonalSurname.set(personalSurname);
        Vls.PersonalFirstName.set(personalFirstName);
        Vls.PersonalPatronymic.set(personalPatronymic);
        Vls.PersonalStreet.set(personalStreet);
        Vls.PersonalBuilding.set(personalBuilding);
        Vls.PersonalApartment.set(personalApartment);

        Vls.PaymentsElec.set(Double.toString(paymentsElec));
        Vls.PaymentsRent.set(Double.toString(paymentsRent));
        Vls.PaymentsHeating.set(Double.toString(paymentsHeating));
        Vls.PaymentsHotWater.set(Double.toString(paymentsHotWater));
        Vls.PaymentsColdWater.set(Double.toString(paymentsColdWater));
        Vls.PaymentsSeverage.set(Double.toString(paymentsSeverage));
        Vls.PaymentsGas.set(Double.toString(paymentsGas));
        Vls.PaymentsGarbage.set(Double.toString(paymentsGarbage));
        Vls.PaymentsIntercom.set(Double.toString(paymentsIntercom));
        Vls.PaymentsTv.set(Double.toString(paymentsTv));

        Vls.UsedElec.set(Boolean.toString(usedElec));
        Vls.UsedRent.set(Boolean.toString(usedRent));
        Vls.UsedHeating.set(Boolean.toString(usedHeating));
        Vls.UsedHotWater.set(Boolean.toString(usedHotWater));
        Vls.UsedColdWater.set(Boolean.toString(usedColdWater));
        Vls.UsedSeverage.set(Boolean.toString(usedSeverage));
        Vls.UsedGas.set(Boolean.toString(usedGas));
        Vls.UsedGarbage.set(Boolean.toString(usedGarbage));
        Vls.UsedIntercom.set(Boolean.toString(usedIntercom));
        Vls.UsedTv.set(Boolean.toString(usedTv));

        Vls.WindowPositionX.set(Integer.toString(windowPositionX));
        Vls.WindowPositionY.set(Integer.toString(windowPositionY));
        Vls.FontSize.set(fontSize.toString());
        Vls.Language.set(language.toString());

        try (OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(USER_HOME + propertiesFileName))) {
            PROPERTIES.store(output, "Utilities' properties file. Don't delete.");
        } catch (IOException e) {
            throw new RuntimeException("Unknown I/O exception: " + e.getMessage());
        }
    }

    public boolean getUsedElecMeter() {
        return usedElecMeter;
    }
    public int getElecBegin() {
        return elecBegin;
    }
    public int getElecEnd() {
        return elecEnd;
    }
    public int getElecTotal() {
        int result = elecEnd - elecBegin;
        if (result < 0) {
            result += (getElecMeterMaxValue() + 1);
        }
        return result;
    }
    public int getElecBoundary() {
        return elecBoundary;
    }
    public double getElecPriceBelowBoundary() {
        return elecPriceBelowBoundary;
    }
    public double getElecPriceAboveBoundary() {
        return elecPriceAboveBoundary;
    }
    public int getElecPrivilege() {
        return elecPrivilege;
    }
    public int getElecMeterMaxValue() {
        return elecMeterMaxValue;
    }
    public boolean getUsedGasMeter() {
        return usedGasMeter;
    }
    public int getGasBegin() {
        return gasBegin;
    }
    public int getGasEnd() {
        return gasEnd;
    }
    public int getGasTotal() {
        int result = gasEnd - gasBegin;
        if (result < 0) {
            result += (getGasMeterMaxValue() + 1);
        }
        return result;
    }
    public double getGasPrice() {
        return gasPrice;
    }
    public int getGasMeterMaxValue() {
        return gasMeterMaxValue;
    }
    public String getPersonalAccount() {
        return personalAccount;
    }
    public String getPersonalSurname() {
        return personalSurname;
    }
    public String getPersonalFirstName() {
        return personalFirstName;
    }
    public String getPersonalPatronymic() {
        return personalPatronymic;
    }
    public String getPersonalStreet() {
        return personalStreet;
    }
    public String getPersonalBuilding() {
        return personalBuilding;
    }
    public String getPersonalApartment() {
        return personalApartment;
    }
    public double getPaymentsElec() {
        return paymentsElec;
    }
    public double getPaymentsRent() {
        return paymentsRent;
    }
    public double getPaymentsHeating() {
        return paymentsHeating;
    }
    public double getPaymentsHotWater() {
        return paymentsHotWater;
    }
    public double getPaymentsColdWater() {
        return paymentsColdWater;
    }
    public double getPaymentsSeverage() {
        return paymentsSeverage;
    }
    public double getPaymentsGas() {
        return paymentsGas;
    }
    public double getPaymentsGarbage() {
        return paymentsGarbage;
    }
    public double getPaymentsIntercom() {
        return paymentsIntercom;
    }
    public double getPaymentsTv() {
        return paymentsTv;
    }
    public boolean getUsedElec() {
        return usedElec;
    }
    public boolean getUsedRent() {
        return usedRent;
    }
    public boolean getUsedHeating() {
        return usedHeating;
    }
    public boolean getUsedHotWater() {
        return usedHotWater;
    }
    public boolean getUsedColdWater() {
        return usedColdWater;
    }
    public boolean getUsedSeverage() {
        return usedSeverage;
    }
    public boolean getUsedGas() {
        return usedGas;
    }
    public boolean getUsedGarbage() {
        return usedGarbage;
    }
    public boolean getUsedIntercom() {
        return usedIntercom;
    }
    public boolean getUsedTv() {
        return usedTv;
    }
    public int getWindowPositionX() {
        return windowPositionX;
    }
    public int getWindowPositionY() {
        return windowPositionY;
    }
    public Resizer.FontSize getFontSize() {
        return fontSize;
    }
    public Dictionary.Language getLanguage() {
        return language;
    }

    public void setUsedElecMeter(boolean elecMeter) {
        this.usedElecMeter = elecMeter;
    }
    public void setElecBegin(int elecBegin) {
        this.elecBegin = elecBegin;
    }
    public void setElecEnd(int elecEnd) {
        this.elecEnd = elecEnd;
    }
    public void setElecBoundary(int elecBoundary) {
        this.elecBoundary = elecBoundary;
    }
    public void setElecPriceBelowBoundary(double elecPriceBelowBoundary) {
        this.elecPriceBelowBoundary = elecPriceBelowBoundary;
    }
    public void setElecPriceAboveBoundary(double elecPriceAboveBoundary) {
        this.elecPriceAboveBoundary = elecPriceAboveBoundary;
    }
    public void setElecPrivilege(int elecPrivilege) {
        this.elecPrivilege = elecPrivilege;
    }
    public void setElecMeterMaxValue(int elecMeterMaxValue) {
        this.elecMeterMaxValue = elecMeterMaxValue;
    }
    public void setUsedGasMeter(boolean gasMeter) {
        this.usedGasMeter = gasMeter;
    }
    public void setGasBegin(int gasBegin) {
        this.gasBegin = gasBegin;
    }
    public void setGasEnd(int gasEnd) {
        this.gasEnd = gasEnd;
    }
    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }
    public void setGasMeterMaxValue(int gasMeterMaxValue) {
        this.gasMeterMaxValue = gasMeterMaxValue;
    }
    public void setPersonalAccount(String personalAccount) {
        this.personalAccount = personalAccount;
    }
    public void setPersonalSurname(String personalSurname) {
        this.personalSurname = personalSurname;
    }
    public void setPersonalFirstName(String personalFirstName) {
        this.personalFirstName = personalFirstName;
    }
    public void setPersonalPatronymic(String personalPatronymic) {
        this.personalPatronymic = personalPatronymic;
    }
    public void setPersonalStreet(String personalStreet) {
        this.personalStreet = personalStreet;
    }
    public void setPersonalBuilding(String personalBuilding) {
        this.personalBuilding = personalBuilding;
    }
    public void setPersonalApartment(String personalApartment) {
        this.personalApartment = personalApartment;
    }
    public void setPaymentsElec(double paymentsElec) {
        this.paymentsElec = paymentsElec;
    }
    public void setPaymentsRent(double paymentsRent) {
        this.paymentsRent = paymentsRent;
    }
    public void setPaymentsHeating(double paymentsHeating) {
        this.paymentsHeating = paymentsHeating;
    }
    public void setPaymentsHotWater(double paymentsHotWater) {
        this.paymentsHotWater = paymentsHotWater;
    }
    public void setPaymentsColdWater(double paymentsColdWater) {
        this.paymentsColdWater = paymentsColdWater;
    }
    public void setPaymentsSeverage(double paymentsSeverage) {
        this.paymentsSeverage = paymentsSeverage;
    }
    public void setPaymentsGas(double paymentsGas) {
        this.paymentsGas = paymentsGas;
    }
    public void setPaymentsGarbage(double paymentsGarbage) {
        this.paymentsGarbage = paymentsGarbage;
    }
    public void setPaymentsIntercom(double paymentsIntercom) {
        this.paymentsIntercom = paymentsIntercom;
    }
    public void setPaymentsTv(double paymentsTv) {
        this.paymentsTv = paymentsTv;
    }
    public void setUsedElec(boolean usedElec) {
        this.usedElec = usedElec;
    }
    public void setUsedRent(boolean usedRent) {
        this.usedRent = usedRent;
    }
    public void setUsedHeating(boolean usedHeating) {
        this.usedHeating = usedHeating;
    }
    public void setUsedHotWater(boolean usedHotWater) {
        this.usedHotWater = usedHotWater;
    }
    public void setUsedColdWater(boolean usedColdWater) {
        this.usedColdWater = usedColdWater;
    }
    public void setUsedSeverage(boolean usedSeverage) {
        this.usedSeverage = usedSeverage;
    }
    public void setUsedGas(boolean usedGas) {
        this.usedGas = usedGas;
    }
    public void setUsedGarbage(boolean usedGarbage) {
        this.usedGarbage = usedGarbage;
    }
    public void setUsedIntercom(boolean usedIntercom) {
        this.usedIntercom = usedIntercom;
    }
    public void setUsedTv(boolean usedTv) {
        this.usedTv = usedTv;
    }
    public void setWindowPositionX(int position) {
        windowPositionX = position;
    }
    public void setWindowPositionY(int position) {
        windowPositionY = position;
    }
    public void setFontSize(Resizer.FontSize fontSize) {
        this.fontSize = fontSize;
    }
    public void setLanguage(Dictionary.Language language) {
        this.language = language;
    }
}
