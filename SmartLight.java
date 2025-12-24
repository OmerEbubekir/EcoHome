/**
 * SmartLight - Akıllı Ampul Sınıfı
 * GEREKSINIM: Kalıtım (Inheritance) - Device sınıfından türer
 * GEREKSINIM: Interface Implementation - RemoteControllable ve EnergySaver arayüzlerini implement eder
 */
public class SmartLight extends Device implements RemoteControllable, EnergySaver {
    // GEREKSINIM: Erişim Belirleyiciler (private)
    private int brightness; // 0-100 arası
    private String colorTemperature; // "Warm", "Cool", "Daylight"
    private boolean energySavingMode;
    
    // GEREKSINIM: Constructor
    public SmartLight(String deviceId, String deviceName, double powerConsumption) {
        super(deviceId, deviceName, powerConsumption);
        this.brightness = 50;
        this.colorTemperature = "Warm";
        this.energySavingMode = false;
    }
    
    // GEREKSINIM: Constructor Overloading
    public SmartLight(String deviceId, String deviceName, double powerConsumption, int brightness) {
        super(deviceId, deviceName, powerConsumption);
        this.brightness = brightness;
        this.colorTemperature = "Warm";
        this.energySavingMode = false;
    }
    
    // GEREKSINIM: Constructor Overloading
    public SmartLight(String deviceId, String deviceName, double powerConsumption, int brightness, String colorTemperature) {
        super(deviceId, deviceName, powerConsumption);
        this.brightness = brightness;
        this.colorTemperature = colorTemperature;
        this.energySavingMode = false;
    }
    
    // Device sınıfından gelen soyut metodları implement etme
    @Override
    public void turnOn() throws DeviceOfflineException {
        if (!isOnline()) {
            throw new DeviceOfflineException(getDeviceId());
        }
        setOn(true);
        System.out.println(getDeviceName() + " açıldı. Parlaklık: " + brightness + "%");
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
        // Günlük tüketim = (Güç * Saat) / 1000 (kWh'a çevirmek için)
        double hourlyConsumption = getPowerConsumption() / 1000.0;
        return hourlyConsumption * 24; // 24 saatlik tüketim
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
    
    // EnergySaver arayüzünden gelen metodlar
    @Override
    public void enableEnergySavingMode() {
        energySavingMode = true;
        if (brightness > 50) {
            brightness = 50; // Enerji tasarrufu için parlaklığı düşür
        }
        System.out.println(getDeviceName() + " için enerji tasarruf modu aktif edildi.");
    }
    
    @Override
    public void disableEnergySavingMode() {
        energySavingMode = false;
        System.out.println(getDeviceName() + " için enerji tasarruf modu kapatıldı.");
    }
    
    @Override
    public boolean isEnergySavingModeActive() {
        return energySavingMode;
    }
    
    @Override
    public double calculateEnergySaved() {
        if (energySavingMode && isOn()) {
            // Enerji tasarruf modunda %30 tasarruf
            return getPowerConsumption() * 0.3;
        }
        return 0.0;
    }
    
    // GEREKSINIM: Method Overloading - setBrightness metodları
    public void setBrightness(int brightness) {
        if (brightness >= 0 && brightness <= 100) {
            this.brightness = brightness;
        }
    }
    
    // GEREKSINIM: Method Overloading
    public void setBrightness(int brightness, boolean adjustEnergyMode) {
        setBrightness(brightness);
        if (adjustEnergyMode && brightness > 70) {
            disableEnergySavingMode();
        } else if (adjustEnergyMode && brightness <= 50) {
            enableEnergySavingMode();
        }
    }
    
    // GEREKSINIM: Method Overloading
    public void setBrightness(int brightness, String colorTemperature) {
        setBrightness(brightness);
        this.colorTemperature = colorTemperature;
    }
    
    // Getter metodları
    public int getBrightness() {
        return brightness;
    }
    
    public String getColorTemperature() {
        return colorTemperature;
    }
    
    // Setter metodları
    public void setColorTemperature(String colorTemperature) {
        this.colorTemperature = colorTemperature;
    }
    
    @Override
    public String getStatus() {
        return super.getStatus() + String.format(" | Parlaklık: %d%% | Renk Sıcaklığı: %s", 
            brightness, colorTemperature);
    }
}

