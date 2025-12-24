/**
 * DeviceOfflineException - Cihaz Çevrimdışı İstisnası
 * GEREKSINIM: Custom Exception
 */
public class DeviceOfflineException extends Exception {
    private String deviceId;
    
    // GEREKSINIM: Constructor
    public DeviceOfflineException(String deviceId) {
        super("Cihaz çevrimdışı: " + deviceId);
        this.deviceId = deviceId;
    }
    
    public DeviceOfflineException(String deviceId, String message) {
        super(message);
        this.deviceId = deviceId;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
}

