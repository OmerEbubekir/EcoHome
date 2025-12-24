/**
 * SmartTV - Akıllı Televizyon Sınıfı
 * GEREKSINIM: Kalıtım (Inheritance) - Device sınıfından türer
 * GEREKSINIM: Interface Implementation - RemoteControllable ve EnergySaver arayüzlerini implement eder
 */
public class SmartTV extends Device implements RemoteControllable, EnergySaver {
    // GEREKSINIM: Erişim Belirleyiciler (private)
    private int volume; // 0-100 arası
    private int channel;
    private String currentApp; // "Netflix", "YouTube", vb.
    private boolean energySavingMode;
    
    // GEREKSINIM: Constructor
    public SmartTV(String deviceId, String deviceName, double powerConsumption) {
        super(deviceId, deviceName, powerConsumption);
        this.volume = 30;
        this.channel = 1;
        this.currentApp = "TV";
        this.energySavingMode = false;
    }
    
    // GEREKSINIM: Constructor Overloading
    public SmartTV(String deviceId, String deviceName, double powerConsumption, int volume) {
        super(deviceId, deviceName, powerConsumption);
        this.volume = volume;
        this.channel = 1;
        this.currentApp = "TV";
        this.energySavingMode = false;
    }
    
    // Device sınıfından gelen soyut metodları implement etme
    @Override
    public void turnOn() throws DeviceOfflineException {
        if (!isOnline()) {
            throw new DeviceOfflineException(getDeviceId());
        }
        setOn(true);
        System.out.println(getDeviceName() + " açıldı. Kanal: " + channel + ", Ses: " + volume);
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
        // TV günlük tüketim hesaplama (ortalama 6 saat kullanım)
        double hourlyConsumption = getPowerConsumption() / 1000.0;
        return hourlyConsumption * 6; // 6 saatlik tüketim
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
        if (volume > 50) {
            volume = 50; // Enerji tasarrufu için ses seviyesini düşür
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
            // Enerji tasarruf modunda %25 tasarruf
            return getPowerConsumption() * 0.25;
        }
        return 0.0;
    }
    
    // GEREKSINIM: Method Overloading - setChannel metodları
    public void setChannel(int channel) {
        if (channel > 0) {
            this.channel = channel;
            System.out.println("Kanal " + channel + " olarak değiştirildi.");
        }
    }
    
    // GEREKSINIM: Method Overloading
    public void setChannel(int channel, boolean mute) {
        setChannel(channel);
        if (mute) {
            volume = 0;
            System.out.println("Kanal değiştirildi ve ses kapatıldı.");
        }
    }
    
    // GEREKSINIM: Method Overloading
    public void setChannel(int channel, String app) {
        this.channel = channel;
        this.currentApp = app;
        System.out.println("Kanal " + channel + " (" + app + ") olarak değiştirildi.");
    }
    
    // Getter metodları
    public int getVolume() {
        return volume;
    }
    
    public int getChannel() {
        return channel;
    }
    
    public String getCurrentApp() {
        return currentApp;
    }
    
    // Setter metodları
    public void setVolume(int volume) {
        if (volume >= 0 && volume <= 100) {
            this.volume = volume;
        }
    }
    
    public void setCurrentApp(String currentApp) {
        this.currentApp = currentApp;
    }
    
    @Override
    public String getStatus() {
        return super.getStatus() + String.format(" | Kanal: %d | Ses: %d | Uygulama: %s", 
            channel, volume, currentApp);
    }
}

