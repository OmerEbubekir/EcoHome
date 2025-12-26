/**
 * EnergySaver - Enerji Tasarrufu Arayüzü
 * GEREKSINIM: Interface
 */
public interface EnergySaver {
    // Enerji tasarruf modunu aktif et
    void enableEnergySavingMode();
    
    // Enerji tasarruf modunu kapat
    void disableEnergySavingMode();
    
    // Enerji tasarruf modu aktif mi? method
    boolean isEnergySavingModeActive();
    
    // Tasarruf edilen enerji miktarını hesapla
    double calculateEnergySaved();
}

