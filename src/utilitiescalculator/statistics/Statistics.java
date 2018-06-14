package utilitiescalculator.statistics;

public class Statistics {

    public static class Builder {
        private long timestamp;
        private int month;
        private int year;
        private Electricity electricity;
        private double rent;
        private double heating;
        private double hotWater;
        private double coldWater;
        private double sewerage;
        private Gas gas;
        private double garbage;
        private double intercom;
        private double tv;

        public Statistics build() {
            return new Statistics(this);
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder month(int month) {
            this.month = month;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder electricity(Electricity electricity) {
            this.electricity = electricity;
            return this;
        }

        public Builder electricity(int kwh, double price) {
            this.electricity = new Electricity(kwh, price);
            return this;
        }

        public Builder rent(double price) {
            this.rent = price;
            return this;
        }

        public Builder heating(double price) {
            this.heating = price;
            return this;
        }

        public Builder hotWater(double price) {
            this.hotWater = price;
            return this;
        }

        public Builder coldWater(double price) {
            this.coldWater = price;
            return this;
        }

        public Builder sewerage(double price) {
            this.sewerage = price;
            return this;
        }

        public Builder gas(Gas gas) {
            this.gas = gas;
            return this;
        }

        public Builder gas(int meterCubic, double price) {
            this.gas = new Gas(meterCubic, price);
            return this;
        }

        public Builder garbage(double price) {
            this.garbage = price;
            return this;
        }

        public Builder intercom(double price) {
            this.intercom = price;
            return this;
        }

        public Builder tv(double price) {
            this.tv = price;
            return this;
        }
    }

    private long timestamp;
    private int month;
    private int year;
    private Electricity electricity;
    private double rent;
    private double heating;
    private double hotWater;
    private double coldWater;
    private double sewerage;
    private Gas gas;
    private double garbage;
    private double intercom;
    private double tv;

    public Statistics() {
        electricity = new Electricity();
        gas = new Gas();
    }

    private Statistics(Builder builder) {
        this.timestamp = builder.timestamp;
        this.month = builder.month;
        this.year = builder.year;
        this.electricity = builder.electricity != null ? builder.electricity : new Electricity();
        this.rent = builder.rent;
        this.heating = builder.heating;
        this.hotWater = builder.hotWater;
        this.coldWater = builder.coldWater;
        this.sewerage = builder.sewerage;
        this.gas = builder.gas != null ? builder.gas : new Gas();
        this.garbage = builder.garbage;
        this.intercom = builder.intercom;
        this.tv = builder.tv;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Electricity getElectricity() {
        return electricity;
    }

    public double getRent() {
        return rent;
    }

    public double getHeating() {
        return heating;
    }

    public double getHotWater() {
        return hotWater;
    }

    public double getColdWater() {
        return coldWater;
    }

    public double getSewerage() {
        return sewerage;
    }

    public Gas getGas() {
        return gas;
    }

    public double getGarbage() {
        return garbage;
    }

    public double getIntercom() {
        return intercom;
    }

    public double getTv() {
        return tv;
    }

    @Override
    public String toString() {
        return "{" +
                "\"timestamp\":" + timestamp + "," +
                "\"month\":" + month + "," +
                "\"year\":" + year + "," +
                "\"electricity\":" + electricity + "," +
                "\"rent\":" + rent + "," +
                "\"heating\":" + heating + "," +
                "\"hotWater\":" + hotWater + "," +
                "\"coldWater\":" + coldWater + "," +
                "\"sewerage\":" + sewerage + "," +
                "\"gas\":" + gas + "," +
                "\"garbage\":" + garbage + "," +
                "\"intercom\":" + intercom + "," +
                "\"tv\":" + tv +
                "}";
    }

    public static class Electricity {
        private int kwh;
        private double price;

        public Electricity() {
        }

        public Electricity(int kwh, double price) {
            this.kwh = kwh;
            this.price = price;
        }

        public int getKwh() {
            return kwh;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "{\"kwh\":" + kwh + ",\"price\":" + price + "}";
        }
    }

    public static class Gas {
        private int meterCubic;
        private double price;

        public Gas() {
        }

        public Gas(int meterCubic, double price) {
            this.meterCubic = meterCubic;
            this.price = price;
        }

        public int getMeterCubic() {
            return meterCubic;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "{\"meterCubic\":" + meterCubic + ",\"price\":" + price + "}";
        }
    }
}
