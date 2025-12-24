/**
 * Device - Soyut Cihaz Sınıfı
 * Tüm akıllı cihazların temel sınıfıdır.
 * GEREKSINIM: Abstract Class
 */
public abstract class Device {
    // GEREKSINIM: Erişim Belirleyiciler (private)
    private String deviceId;
    private String deviceName;
    private boolean isOn;
    private double powerConsumption; // Watt cinsinden
    private boolean isOnline;
    
    // GEREKSINIM: Constructor
    public Device(String deviceId, String deviceName, double powerConsumption) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.powerConsumption = powerConsumption;
        this.isOn = false;
        this.isOnline = true;
    }
    
    // GEREKSINIM: Getter Metodları
    public String getDeviceId() {
        return deviceId;
    }
    
    public String getDeviceName() {
        return deviceName;
    }
    
    public boolean isOn() {
        return isOn;
    }
    
    public double getPowerConsumption() {
        return powerConsumption;
    }
    
    public boolean isOnline() {
        return isOnline;
    }
    
    // GEREKSINIM: Setter Metodları
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    
    protected void setOn(boolean on) {
        isOn = on;
    }
    
    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }
    
    public void setOnline(boolean online) {
        isOnline = online;
    }
    
    // Soyut metodlar - alt sınıflar implement edecek
    public abstract void turnOn() throws DeviceOfflineException;
    public abstract void turnOff();
    public abstract double calculateDailyEnergyConsumption();
    
    // Genel metodlar
    public String getStatus() {
        return String.format("Cihaz: %s | Durum: %s | Çevrimiçi: %s | Güç: %.2fW", 
            deviceName, 
            isOn ? "Açık" : "Kapalı",
            isOnline ? "Evet" : "Hayır",
            isOn ? powerConsumption : 0.0
        );
    }
}

