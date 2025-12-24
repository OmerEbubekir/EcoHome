/**
 * RemoteControllable - Uzaktan Kontrol Edilebilir Arayüz
 * GEREKSINIM: Interface
 */
public interface RemoteControllable {
    // Uzaktan açma/kapama metodları
    void remoteTurnOn() throws DeviceOfflineException;
    void remoteTurnOff() throws DeviceOfflineException;
    
    // Uzaktan durum kontrolü
    boolean checkRemoteStatus();
}

