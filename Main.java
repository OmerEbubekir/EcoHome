import java.util.Random;
import java.util.Scanner;

/**
 * Main - Ana Sınıf
 * EcoHome Akıllı Ev Enerji ve Cihaz Yönetim Simülasyonu
 * GEREKSINIM: Simülasyon Senaryosu
 */
public class Main {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        System.out.println("========================================");
        System.out.println("  EcoHome - Akıllı Ev Simülasyonu");
        System.out.println("========================================\n");
        
        // Menü gösterimi
        System.out.println("Lütfen bir seçenek seçin:");
        System.out.println("1. Otomatik Simülasyonu Başlat");
        System.out.println("2. Çıkış");
        System.out.print("\nSeçiminiz: ");
        
        try {
            int choice = scanner.nextInt();
            // nextInt enter karakterini almaz, onu temizlemek için bir boş okuma yapıyoruz:
            scanner.nextLine(); 
            
            if (choice == 1) {
                // Otomatik simülasyonu başlat
                runSimulation();
            } else if (choice == 2) {
                System.out.println("\nProgramdan çıkılıyor...");
            } else {
                System.out.println("\nGeçersiz seçim!");
            }
        } catch (Exception e) {
            System.out.println("Hatalı giriş yaptınız.");
        }

        //
        System.out.println("\n========================================");
        System.out.println(" Çıkış yapmak için ENTER tuşuna basınız...");
        System.out.println("========================================");
        scanner.nextLine(); // Kullanıcı Enter'a basana kadar burada bekler
    }
    
    /**
     * Tüm simülasyon senaryolarını çalıştıran metod
     */
    private static void runSimulation() {
        // Merkezi kontrol ünitesi oluşturma
        CentralControlUnit controlUnit = new CentralControlUnit("Ana Kontrol Ünitesi", 5000.0);
        
        // Cihazları oluşturma
        System.out.println("\n=== Cihazlar Oluşturuluyor ===");
        
        SmartLight light1 = new SmartLight("LIGHT001", "Salon Ampulü", 10.0);
        SmartLight light2 = new SmartLight("LIGHT002", "Yatak Odası Ampulü", 8.0, 75);
        SmartLight light3 = new SmartLight("LIGHT003", "Mutfak Ampulü", 12.0, 60, "Daylight");
        
        SmartThermostat thermostat1 = new SmartThermostat("THERMO001", "Salon Termostatı", 150.0);
        SmartThermostat thermostat2 = new SmartThermostat("THERMO002", "Yatak Odası Termostatı", 120.0, 20.0);
        
        SmartTV tv1 = new SmartTV("TV001", "Salon TV", 100.0);
        SmartTV tv2 = new SmartTV("TV002", "Yatak Odası TV", 80.0, 40);
        
        // Cihazları sisteme ekleme
        controlUnit.addDevice(light1);
        controlUnit.addDevice(light2);
        controlUnit.addDevice(light3);
        controlUnit.addDevice(thermostat1);
        controlUnit.addDevice(thermostat2);
        controlUnit.addDevice(tv1);
        controlUnit.addDevice(tv2);
        
        System.out.println("\n");
        
        // Senaryo 1: Bazı cihazları açma
        System.out.println("=== Senaryo 1: Cihazları Açma ===");
        try {
            light1.turnOn();
            light2.turnOn();
            thermostat1.turnOn();
            tv1.turnOn();
        } catch (DeviceOfflineException e) {
            System.out.println("HATA: " + e.getMessage());
        }
        
        // Senaryo 2: Güç tüketimini kontrol etme
        System.out.println("\n=== Senaryo 2: Güç Tüketimi Kontrolü ===");
        try {
            double totalPower = controlUnit.calculateTotalPowerConsumption();
            System.out.println("Toplam Güç Tüketimi: " + String.format("%.2f", totalPower) + "W");
        } catch (PowerOverloadException e) {
            System.out.println("HATA: " + e.getMessage());
        }
        
        // Senaryo 3: Method Overloading örnekleri
        System.out.println("\n=== Senaryo 3: Method Overloading Örnekleri ===");
        light1.setBrightness(80);
        light1.setBrightness(50, true);
        light1.setBrightness(70, "Cool");
        
        thermostat1.setTemperature(24.0);
        thermostat1.setTemperature(22.0, "Heating");
        thermostat1.setTemperature(20.0, "Cooling", true);
        
        tv1.setChannel(5);
        tv1.setChannel(10, true);
        tv1.setChannel(15, "Netflix");
        
        // Senaryo 4: Inner Class kullanımı
        System.out.println("\n=== Senaryo 4: Inner Class - Sıcaklık Programı ===");
        SmartThermostat.TemperatureSchedule mondaySchedule = 
            thermostat1.new TemperatureSchedule("Pazartesi", 20.0, 22.0, 21.0, 18.0);
        System.out.println(mondaySchedule.getScheduleInfo());
        mondaySchedule.applySchedule(8);
        mondaySchedule.applySchedule(14);
        mondaySchedule.applySchedule(19);
        mondaySchedule.applySchedule(23);
        
        // Senaryo 5: Exception Handling - Çevrimdışı cihaz
        System.out.println("\n=== Senaryo 5: Exception Handling - Çevrimdışı Cihaz ===");
        light3.setOnline(false);
        try {
            light3.turnOn();
        } catch (DeviceOfflineException e) {
            System.out.println("Yakalanan İstisna: " + e.getMessage());
            System.out.println("Çevrimdışı Cihaz ID: " + e.getDeviceId());
        }
        
        // Senaryo 6: Exception Handling - Güç Aşımı
        System.out.println("\n=== Senaryo 6: Exception Handling - Güç Aşımı ===");
        try {
            controlUnit.turnOnAllDevices();
            double totalPower = controlUnit.calculateTotalPowerConsumption();
            System.out.println("Toplam Güç: " + String.format("%.2f", totalPower) + "W");
        } catch (PowerOverloadException e) {
            System.out.println("Yakalanan İstisna: " + e.getMessage());
            System.out.println("Mevcut Güç: " + String.format("%.2f", e.getCurrentPower()) + "W");
            System.out.println("Maksimum Güç: " + String.format("%.2f", e.getMaxPower()) + "W");
        }
        
        // Senaryo 7: Güvenli cihaz açma
        System.out.println("\n=== Senaryo 7: Güvenli Cihaz Açma ===");
        controlUnit.turnOffAllDevices();
        controlUnit.safeTurnOnDevice(light1);
        controlUnit.safeTurnOnDevice(thermostat1);
        controlUnit.safeTurnOnDevice(tv1);
        
        // Senaryo 8: Enerji tasarruf modu
        System.out.println("\n=== Senaryo 8: Enerji Tasarruf Modu ===");
        controlUnit.enableEnergySavingModeForAll();
        
        double totalEnergySaved = 0.0;
        for (Device device : controlUnit.getDevices()) {
            if (device instanceof EnergySaver && device.isOn()) {
                totalEnergySaved += ((EnergySaver) device).calculateEnergySaved();
            }
        }
        System.out.println("Toplam Tasarruf Edilen Güç: " + String.format("%.2f", totalEnergySaved) + "W");
        
        // Senaryo 9: Rastgele durumlar
        System.out.println("\n=== Senaryo 9: Rastgele Durumlar ===");
        Random random = new Random();
        
        for (Device device : controlUnit.getDevices()) {
            if (random.nextBoolean()) {
                try {
                    device.turnOn();
                } catch (DeviceOfflineException e) {
                    System.out.println("Rastgele açma başarısız: " + e.getMessage());
                }
            } else {
                device.turnOff();
            }
        }
        
        int offlineCount = random.nextInt(3);
        for (int i = 0; i < offlineCount && i < controlUnit.getDevices().size(); i++) {
            Device device = controlUnit.getDevices().get(random.nextInt(controlUnit.getDevices().size()));
            device.setOnline(false);
            System.out.println(device.getDeviceName() + " rastgele çevrimdışı yapıldı.");
        }
        
        // Senaryo 10: Final durum raporu
        System.out.println("\n=== Senaryo 10: Final Durum Raporu ===");
        controlUnit.printAllDeviceStatus();
        
        System.out.println("\n--- İstatistikler ---");
        System.out.println("Toplam Cihaz Sayısı: " + controlUnit.getDevices().size());
        System.out.println("Çevrimiçi Cihaz Sayısı: " + controlUnit.getOnlineDevices().size());
        System.out.println("Çevrimdışı Cihaz Sayısı: " + controlUnit.getOfflineDevices().size());
        
        try {
            double finalPower = controlUnit.calculateTotalPowerConsumption();
            System.out.println("Mevcut Güç Tüketimi: " + String.format("%.2f", finalPower) + "W");
        } catch (PowerOverloadException e) {
            System.out.println("UYARI: Güç limiti aşıldı! " + e.getMessage());
        }
        
        double dailyEnergy = controlUnit.calculateTotalDailyEnergyConsumption();
        System.out.println("Günlük Enerji Tüketimi: " + String.format("%.2f", dailyEnergy) + " kWh");
        
        System.out.println("\n========================================");
        System.out.println("  Simülasyon Tamamlandı!");
        System.out.println("========================================");
        
    }
}