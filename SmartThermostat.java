/**
 * SmartThermostat - Akıllı Termostat Sınıfı
 * GEREKSINIM: Kalıtım (Inheritance) - Device sınıfından türer
 * GEREKSINIM: Interface Implementation - RemoteControllable arayüzünü implement eder
 * GEREKSINIM: Inner Class - TemperatureSchedule iç sınıfı
 */
public class SmartThermostat extends Device implements RemoteControllable {
    // GEREKSINIM: Erişim Belirleyiciler (private)
    private double currentTemperature;
    private double targetTemperature;
    private String mode; // "Heating", "Cooling", "Auto"
    
    // GEREKSINIM: Constructor
    public SmartThermostat(String deviceId, String deviceName, double powerConsumption) {
        super(deviceId, deviceName, powerConsumption);
        this.currentTemperature = 20.0;
        this.targetTemperature = 22.0;
        this.mode = "Auto";
    }
    
    // GEREKSINIM: Constructor Overloading
    public SmartThermostat(String deviceId, String deviceName, double powerConsumption, double targetTemperature) {
        super(deviceId, deviceName, powerConsumption);
        this.currentTemperature = 20.0;
        this.targetTemperature = targetTemperature;
        this.mode = "Auto";
    }
    
    // Device sınıfından gelen soyut metodları implement etme
    @Override
    public void turnOn() throws DeviceOfflineException {
        if (!isOnline()) {
            throw new DeviceOfflineException(getDeviceId());
        }
        setOn(true);
        System.out.println(getDeviceName() + " açıldı. Hedef sıcaklık: " + targetTemperature + "°C");
    }
    
    @Override
    public void turnOff() {
        setOn(false);
        System.out.println(getDeviceName() + " kapatıldı.");
    }
    
    @Override
    public double calculateDailyEnergyConsumption() {
        if (!isOn()) {
            return 0.0;
        }
        // Termostat çalışma süresine göre tüketim hesaplama
        double baseConsumption = getPowerConsumption() / 1000.0; // kWh
        // Moda göre çarpan uygula
        double multiplier = mode.equals("Heating") ? 1.5 : (mode.equals("Cooling") ? 1.3 : 1.2);
        return baseConsumption * 24 * multiplier;
    }
    
    // RemoteControllable arayüzünden gelen metodlar
    @Override
    public void remoteTurnOn() throws DeviceOfflineException {
        if (!isOnline()) {
            throw new DeviceOfflineException(getDeviceId(), "Uzaktan kontrol başarısız: Cihaz çevrimdışı");
        }
        turnOn();
    }
    
    @Override
    public void remoteTurnOff() throws DeviceOfflineException {
        if (!isOnline()) {
            throw new DeviceOfflineException(getDeviceId(), "Uzaktan kontrol başarısız: Cihaz çevrimdışı");
        }
        turnOff();
    }
    
    @Override
    public boolean checkRemoteStatus() {
        return isOnline() && isOn();
    }
    
    // GEREKSINIM: Method Overloading - setTemperature metodları
    public void setTemperature(double temperature) {
        this.targetTemperature = temperature;
        System.out.println("Hedef sıcaklık " + temperature + "°C olarak ayarlandı.");
    }
    
    // GEREKSINIM: Method Overloading
    public void setTemperature(double temperature, String mode) {
        this.targetTemperature = temperature;
        this.mode = mode;
        System.out.println("Hedef sıcaklık " + temperature + "°C, Mod: " + mode + " olarak ayarlandı.");
    }
    
    // GEREKSINIM: Method Overloading
    public void setTemperature(double temperature, String mode, boolean immediate) {
        setTemperature(temperature, mode);
        if (immediate && !isOn()) {
            try {
                turnOn();
            } catch (DeviceOfflineException e) {
                System.out.println("Hata: " + e.getMessage());
            }
        }
    }
    
    // Getter metodları
    public double getCurrentTemperature() {
        return currentTemperature;
    }
    
    public double getTargetTemperature() {
        return targetTemperature;
    }
    
    public String getMode() {
        return mode;
    }
    
    // Setter metodları
    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
    
    public void setMode(String mode) {
        this.mode = mode;
    }
    
    // GEREKSINIM: Inner Class - Sıcaklık Programı
    public class TemperatureSchedule {
        private String dayOfWeek;
        private double morningTemp;
        private double afternoonTemp;
        private double eveningTemp;
        private double nightTemp;
        
        // GEREKSINIM: Constructor (Inner Class)
        public TemperatureSchedule(String dayOfWeek, double morningTemp, double afternoonTemp, 
                                  double eveningTemp, double nightTemp) {
            this.dayOfWeek = dayOfWeek;
            this.morningTemp = morningTemp;
            this.afternoonTemp = afternoonTemp;
            this.eveningTemp = eveningTemp;
            this.nightTemp = nightTemp;
        }
        
        // Günün saatine göre sıcaklık ayarlama
        public void applySchedule(int hour) {
            double tempToSet;
            if (hour >= 6 && hour < 12) {
                tempToSet = morningTemp;
            } else if (hour >= 12 && hour < 18) {
                tempToSet = afternoonTemp;
            } else if (hour >= 18 && hour < 22) {
                tempToSet = eveningTemp;
            } else {
                tempToSet = nightTemp;
            }
            
            // Dış sınıfın metodunu kullanma
            setTemperature(tempToSet);
            System.out.println(dayOfWeek + " günü " + hour + ". saat için sıcaklık " + tempToSet + "°C olarak ayarlandı.");
        }
        
        // Getter metodları
        public String getDayOfWeek() {
            return dayOfWeek;
        }
        
        public double getMorningTemp() {
            return morningTemp;
        }
        
        public double getAfternoonTemp() {
            return afternoonTemp;
        }
        
        public double getEveningTemp() {
            return eveningTemp;
        }
        
        public double getNightTemp() {
            return nightTemp;
        }
        
        public String getScheduleInfo() {
            return String.format("%s Programı: Sabah=%d°C, Öğle=%d°C, Akşam=%d°C, Gece=%d°C",
                dayOfWeek, (int)morningTemp, (int)afternoonTemp, (int)eveningTemp, (int)nightTemp);
        }
    }
    
    @Override
    public String getStatus() {
        return super.getStatus() + String.format(" | Mevcut: %.1f°C | Hedef: %.1f°C | Mod: %s", 
            currentTemperature, targetTemperature, mode);
    }
}

