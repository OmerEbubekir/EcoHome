import java.util.ArrayList;
import java.util.List;

/**
 * CentralControlUnit - Merkezi Kontrol Ünitesi
 * Tüm cihazları yöneten ve enerji tüketimini kontrol eden sınıf
 */
public class CentralControlUnit {
    // GEREKSINIM: Erişim Belirleyiciler (private)
    private List<Device> devices;
    private double maxTotalPower; // Maksimum toplam güç (Watt)
    private String unitName;
    
    // GEREKSINIM: Constructor
    public CentralControlUnit(String unitName, double maxTotalPower) {
        this.unitName = unitName;
        this.maxTotalPower = maxTotalPower;
        this.devices = new ArrayList<>();
    }
    
    // Cihaz ekleme
    public void addDevice(Device device) {
        devices.add(device);
        System.out.println(device.getDeviceName() + " sisteme eklendi.");
    }
    
    // Cihaz çıkarma
    public void removeDevice(Device device) {
        if (devices.remove(device)) {
            System.out.println(device.getDeviceName() + " sistemden çıkarıldı.");
        }
    }
    
    // Tüm cihazları açma - GEREKSINIM: Exception Handling
    public void turnOnAllDevices() {
        System.out.println("\n=== Tüm Cihazları Açma İşlemi ===");
        for (Device device : devices) {
            try {
                device.turnOn();
            } catch (DeviceOfflineException e) {
                System.out.println("HATA: " + e.getMessage());
            }
        }
    }
    
    // Tüm cihazları kapatma
    public void turnOffAllDevices() {
        System.out.println("\n=== Tüm Cihazları Kapatma İşlemi ===");
        for (Device device : devices) {
            device.turnOff();
        }
    }
    
    // Toplam güç tüketimini hesaplama - GEREKSINIM: Exception Handling
    public double calculateTotalPowerConsumption() throws PowerOverloadException {
        double totalPower = 0.0;
        
        for (Device device : devices) {
            if (device.isOn()) {
                totalPower += device.getPowerConsumption();
            }
        }
        
        if (totalPower > maxTotalPower) {
            throw new PowerOverloadException(totalPower, maxTotalPower);
        }
        
        return totalPower;
    }
    
    // Güvenli cihaz açma - güç kontrolü ile
    public void safeTurnOnDevice(Device device) {
        try {
            // Mevcut güç tüketimini kontrol et
            double currentPower = 0.0;
            for (Device d : devices) {
                if (d.isOn()) {
                    currentPower += d.getPowerConsumption();
                }
            }
            
            // Yeni cihazı açarsak aşım olur mu kontrol et
            if (currentPower + device.getPowerConsumption() > maxTotalPower) {
                throw new PowerOverloadException(
                    currentPower + device.getPowerConsumption(), 
                    maxTotalPower
                );
            }
            
            device.turnOn();
            System.out.println("Cihaz güvenli şekilde açıldı.");
            
        } catch (DeviceOfflineException e) {
            System.out.println("HATA: " + e.getMessage());
        } catch (PowerOverloadException e) {
            System.out.println("HATA: " + e.getMessage());
            System.out.println("Cihaz açılamadı - Güç limiti aşıldı!");
        }
    }
    
    // Günlük toplam enerji tüketimini hesaplama
    public double calculateTotalDailyEnergyConsumption() {
        double totalEnergy = 0.0;
        for (Device device : devices) {
            totalEnergy += device.calculateDailyEnergyConsumption();
        }
        return totalEnergy;
    }
    
    // Çevrimdışı cihazları listeleme
    public List<Device> getOfflineDevices() {
        List<Device> offlineDevices = new ArrayList<>();
        for (Device device : devices) {
            if (!device.isOnline()) {
                offlineDevices.add(device);
            }
        }
        return offlineDevices;
    }
    
    // Açık cihazları listeleme
    public List<Device> getOnlineDevices() {
        List<Device> onlineDevices = new ArrayList<>();
        for (Device device : devices) {
            if (device.isOnline()) {
                onlineDevices.add(device);
            }
        }
        return onlineDevices;
    }
    
    // Tüm cihazların durumunu yazdırma
    public void printAllDeviceStatus() {
        System.out.println("\n=== " + unitName + " - Tüm Cihaz Durumları ===");
        for (Device device : devices) {
            System.out.println(device.getStatus());
        }
    }
    
    // Enerji tasarrufu modunu aktif etme
    public void enableEnergySavingModeForAll() {
        System.out.println("\n=== Enerji Tasarruf Modu Aktif Ediliyor ===");
        for (Device device : devices) {
            if (device instanceof EnergySaver) {
                ((EnergySaver) device).enableEnergySavingMode();
            }
        }
    }
    
    // Getter metodları
    public List<Device> getDevices() {
        return devices;
    }
    
    public double getMaxTotalPower() {
        return maxTotalPower;
    }
    
    public String getUnitName() {
        return unitName;
    }
    
    // Setter metodları
    public void setMaxTotalPower(double maxTotalPower) {
        this.maxTotalPower = maxTotalPower;
    }
    
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}

