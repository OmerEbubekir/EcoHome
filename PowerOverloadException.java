/**
 * PowerOverloadException - Güç Aşımı İstisnası
 * GEREKSINIM: Custom Exception
 */
public class PowerOverloadException extends Exception {
    private double currentPower;
    private double maxPower;
    
    // GEREKSINIM: Constructor
    public PowerOverloadException(double currentPower, double maxPower) {
        super(String.format("Güç aşımı! Mevcut: %.2fW, Maksimum: %.2fW", currentPower, maxPower));
        this.currentPower = currentPower;
        this.maxPower = maxPower;
    }
    
    public double getCurrentPower() {
        return currentPower;
    }
    
    public double getMaxPower() {
        return maxPower;
    }
}

