# ECOHOME - AKILLI EV ENERJİ VE CİHAZ YÖNETİM SİMÜLASYONU
## Nesneye Yönelik Programlama (OOP) Dönem Projesi

---

## 1. PROJE ÖZETİ

### 1.1. Proje Amacı
EcoHome projesi, akıllı ev sistemlerinde bulunan çeşitli cihazların merkezi bir kontrol ünitesi tarafından yönetilmesini ve enerji tüketiminin izlenmesini simüle eden bir Java uygulamasıdır. Proje, Nesneye Yönelik Programlama (OOP) prensiplerinin kapsamlı bir şekilde uygulanmasını hedeflemektedir. Sistem, farklı türde akıllı cihazların (ampul, termostat, televizyon) oluşturulması, yönetilmesi, enerji tüketimlerinin hesaplanması ve istisna durumlarının yönetilmesi gibi gerçek dünya senaryolarını simüle etmektedir.

### 1.2. Proje Kapsamı
Proje, aşağıdaki temel işlevleri içermektedir:
- Farklı türde akıllı cihazların tanımlanması ve yönetimi
- Cihazların açılması/kapatılması ve durum takibi
- Enerji tüketiminin hesaplanması ve izlenmesi
- Güç limiti kontrolü ve aşım durumlarının yönetimi
- Çevrimdışı cihaz durumlarının işlenmesi
- Enerji tasarruf modlarının aktif edilmesi
- Uzaktan kontrol özelliklerinin simülasyonu

---

## 2. PROJE MİMARİSİ VE SINIF YAPISI

### 2.1. Sınıf Hiyerarşisi

```
Device (Abstract Class)
├── SmartLight
├── SmartThermostat
│   └── TemperatureSchedule (Inner Class)
└── SmartTV

RemoteControllable (Interface)
EnergySaver (Interface)

DeviceOfflineException (Custom Exception)
PowerOverloadException (Custom Exception)

CentralControlUnit
Main
```

### 2.2. Modül Açıklamaları

#### 2.2.1. Device (Soyut Sınıf)
**Konum:** `Device.java`  
**Rol:** Tüm akıllı cihazların ortak özelliklerini ve davranışlarını tanımlayan soyut temel sınıftır.

**Özellikler:**
- `deviceId`: Cihazın benzersiz kimliği
- `deviceName`: Cihazın adı
- `isOn`: Cihazın açık/kapalı durumu
- `powerConsumption`: Cihazın güç tüketimi (Watt)
- `isOnline`: Cihazın çevrimiçi/çevrimdışı durumu

**Soyut Metodlar:**
- `turnOn()`: Cihazı açma (alt sınıflar implement eder)
- `turnOff()`: Cihazı kapatma (alt sınıflar implement eder)
- `calculateDailyEnergyConsumption()`: Günlük enerji tüketimini hesaplama (alt sınıflar implement eder)

**OOP Prensibi:** Abstraction (Soyutlama) - Ortak davranışları tanımlar, detayları alt sınıflara bırakır.

#### 2.2.2. SmartLight (Akıllı Ampul)
**Konum:** `SmartLight.java`  
**Rol:** Akıllı ampul cihazını temsil eden somut sınıftır.

**Kalıtım:** `Device` sınıfından türer  
**Arayüzler:** `RemoteControllable`, `EnergySaver` arayüzlerini implement eder

**Özel Özellikler:**
- `brightness`: Parlaklık seviyesi (0-100)
- `colorTemperature`: Renk sıcaklığı ("Warm", "Cool", "Daylight")
- `energySavingMode`: Enerji tasarruf modu durumu

**Önemli Metodlar:**
- `setBrightness(int brightness)`: Parlaklık ayarlama (3 farklı overload versiyonu)
- `enableEnergySavingMode()`: Enerji tasarruf modunu aktif etme
- `calculateEnergySaved()`: Tasarruf edilen enerjiyi hesaplama

**OOP Prensipleri:**
- **Inheritance (Kalıtım):** Device sınıfından türer
- **Polymorphism (Çok Biçimlilik):** Device tipinde kullanılabilir
- **Method Overloading:** setBrightness metodunun 3 farklı versiyonu

#### 2.2.3. SmartThermostat (Akıllı Termostat)
**Konum:** `SmartThermostat.java`  
**Rol:** Akıllı termostat cihazını temsil eden somut sınıftır.

**Kalıtım:** `Device` sınıfından türer  
**Arayüzler:** `RemoteControllable` arayüzünü implement eder

**Özel Özellikler:**
- `currentTemperature`: Mevcut sıcaklık
- `targetTemperature`: Hedef sıcaklık
- `mode`: Çalışma modu ("Heating", "Cooling", "Auto")

**İç Sınıf:**
- `TemperatureSchedule`: Günlük sıcaklık programını yöneten inner class
  - Sabah, öğle, akşam ve gece sıcaklıklarını saklar
  - Saate göre otomatik sıcaklık ayarlama yapar

**Önemli Metodlar:**
- `setTemperature(double temperature)`: Sıcaklık ayarlama (3 farklı overload versiyonu)
- `TemperatureSchedule.applySchedule(int hour)`: Programlı sıcaklık ayarlama

**OOP Prensipleri:**
- **Inheritance (Kalıtım):** Device sınıfından türer
- **Inner Class:** TemperatureSchedule iç sınıfı ile mantıksal gruplama
- **Method Overloading:** setTemperature metodunun 3 farklı versiyonu

#### 2.2.4. SmartTV (Akıllı Televizyon)
**Konum:** `SmartTV.java`  
**Rol:** Akıllı televizyon cihazını temsil eden somut sınıftır.

**Kalıtım:** `Device` sınıfından türer  
**Arayüzler:** `RemoteControllable`, `EnergySaver` arayüzlerini implement eder

**Özel Özellikler:**
- `volume`: Ses seviyesi (0-100)
- `channel`: Aktif kanal
- `currentApp`: Aktif uygulama ("Netflix", "YouTube", vb.)
- `energySavingMode`: Enerji tasarruf modu durumu

**Önemli Metodlar:**
- `setChannel(int channel)`: Kanal ayarlama (3 farklı overload versiyonu)
- `enableEnergySavingMode()`: Enerji tasarruf modunu aktif etme

**OOP Prensipleri:**
- **Inheritance (Kalıtım):** Device sınıfından türer
- **Polymorphism (Çok Biçimlilik):** Device tipinde kullanılabilir
- **Method Overloading:** setChannel metodunun 3 farklı versiyonu

#### 2.2.5. RemoteControllable (Arayüz)
**Konum:** `RemoteControllable.java`  
**Rol:** Uzaktan kontrol edilebilir cihazlar için sözleşme tanımlar.

**Metodlar:**
- `remoteTurnOn()`: Uzaktan açma
- `remoteTurnOff()`: Uzaktan kapatma
- `checkRemoteStatus()`: Uzaktan durum kontrolü

**OOP Prensibi:** Interface Segregation - Belirli bir davranış için sözleşme tanımlar.

#### 2.2.6. EnergySaver (Arayüz)
**Konum:** `EnergySaver.java`  
**Rol:** Enerji tasarrufu yapabilen cihazlar için sözleşme tanımlar.

**Metodlar:**
- `enableEnergySavingMode()`: Enerji tasarruf modunu aktif etme
- `disableEnergySavingMode()`: Enerji tasarruf modunu kapatma
- `isEnergySavingModeActive()`: Enerji tasarruf modu durumu
- `calculateEnergySaved()`: Tasarruf edilen enerjiyi hesaplama

**OOP Prensibi:** Interface Segregation - Enerji tasarrufu özelliği için ayrı sözleşme.

#### 2.2.7. DeviceOfflineException (Özel İstisna)
**Konum:** `DeviceOfflineException.java`  
**Rol:** Cihaz çevrimdışı olduğunda fırlatılan özel istisna sınıfıdır.

**Özellikler:**
- `deviceId`: Çevrimdışı olan cihazın kimliği

**OOP Prensibi:** Exception Handling - Özel durumlar için özelleştirilmiş istisna sınıfı.

#### 2.2.8. PowerOverloadException (Özel İstisna)
**Konum:** `PowerOverloadException.java`  
**Rol:** Güç limiti aşıldığında fırlatılan özel istisna sınıfıdır.

**Özellikler:**
- `currentPower`: Mevcut güç tüketimi
- `maxPower`: Maksimum izin verilen güç

**OOP Prensibi:** Exception Handling - Güç aşımı durumları için özelleştirilmiş istisna sınıfı.

#### 2.2.9. CentralControlUnit (Merkezi Kontrol Ünitesi)
**Konum:** `CentralControlUnit.java`  
**Rol:** Tüm cihazları yöneten ve enerji tüketimini kontrol eden merkezi yönetim sınıfıdır.

**Özellikler:**
- `devices`: Yönetilen cihazların listesi
- `maxTotalPower`: Maksimum toplam güç limiti
- `unitName`: Kontrol ünitesinin adı

**Önemli Metodlar:**
- `addDevice(Device device)`: Cihaz ekleme
- `removeDevice(Device device)`: Cihaz çıkarma
- `turnOnAllDevices()`: Tüm cihazları açma
- `turnOffAllDevices()`: Tüm cihazları kapatma
- `calculateTotalPowerConsumption()`: Toplam güç tüketimini hesaplama (PowerOverloadException fırlatabilir)
- `safeTurnOnDevice(Device device)`: Güvenli cihaz açma (güç kontrolü ile)
- `enableEnergySavingModeForAll()`: Tüm cihazlarda enerji tasarruf modunu aktif etme

**OOP Prensipleri:**
- **Composition:** Device nesnelerini içerir
- **Polymorphism:** Device tipinde nesnelerle çalışır
- **Exception Handling:** try-catch blokları ile hata yönetimi

#### 2.2.10. Main (Ana Sınıf)
**Konum:** `Main.java`  
**Rol:** Programın giriş noktası ve simülasyon senaryolarını çalıştıran sınıftır.

**İşlevler:**
- Kullanıcı menüsü sunar
- Cihazları oluşturur ve sisteme ekler
- 10 farklı simülasyon senaryosunu çalıştırır:
  1. Cihazları açma
  2. Güç tüketimi kontrolü
  3. Method overloading örnekleri
  4. Inner class kullanımı
  5. Çevrimdışı cihaz exception handling
  6. Güç aşımı exception handling
  7. Güvenli cihaz açma
  8. Enerji tasarruf modu
  9. Rastgele durumlar
  10. Final durum raporu

---

## 3. OOP PRENSİPLERİNİN UYGULANMASI

### 3.1. Encapsulation (Kapsülleme)
**Açıklama:** Verilerin ve metodların erişim kontrolü ile korunması.

**Uygulama Örnekleri:**
- Tüm sınıflarda private alanlar kullanılmıştır
- Getter ve setter metodları ile kontrollü erişim sağlanmıştır
- `Device` sınıfında `setOn()` metodu `protected` olarak tanımlanmıştır (sadece alt sınıflar erişebilir)

**Kod Örnekleri:**
```java
// Device.java - Private alanlar
private String deviceId;
private String deviceName;
private boolean isOn;

// Protected metod - sadece alt sınıflar erişebilir
protected void setOn(boolean on) {
    isOn = on;
}
```

### 3.2. Inheritance (Kalıtım)
**Açıklama:** Bir sınıfın başka bir sınıftan özellik ve davranışları devralması.

**Uygulama Örnekleri:**
- `SmartLight`, `SmartThermostat`, `SmartTV` sınıfları `Device` sınıfından türer
- Alt sınıflar `super()` anahtar kelimesi ile üst sınıf constructor'ını çağırır
- Alt sınıflar üst sınıfın soyut metodlarını implement eder

**Kod Örnekleri:**
```java
// SmartLight.java - Kalıtım
public class SmartLight extends Device {
    public SmartLight(String deviceId, String deviceName, double powerConsumption) {
        super(deviceId, deviceName, powerConsumption); // Üst sınıf constructor'ı
        // ...
    }
    
    @Override
    public void turnOn() throws DeviceOfflineException {
        // Üst sınıfın soyut metodunu implement etme
    }
}
```

### 3.3. Abstraction (Soyutlama)
**Açıklama:** Karmaşık sistemleri basitleştirerek sadece gerekli detayları gösterme.

**Uygulama Örnekleri:**
- `Device` sınıfı abstract olarak tanımlanmıştır
- Ortak davranışlar tanımlanmış, detaylar alt sınıflara bırakılmıştır
- Soyut metodlar ile zorunlu implementasyon sağlanmıştır

**Kod Örnekleri:**
```java
// Device.java - Abstract sınıf
public abstract class Device {
    // Soyut metodlar - alt sınıflar implement etmek zorunda
    public abstract void turnOn() throws DeviceOfflineException;
    public abstract void turnOff();
    public abstract double calculateDailyEnergyConsumption();
}
```

### 3.4. Polymorphism (Çok Biçimlilik)
**Açıklama:** Aynı arayüz veya sınıf tipi üzerinden farklı davranışlar sergileme.

**Uygulama Örnekleri:**
- `CentralControlUnit` sınıfı `Device` tipinde nesnelerle çalışır
- Farklı cihaz tipleri aynı referans tipi üzerinden yönetilir
- `instanceof` operatörü ile tip kontrolü yapılır

**Kod Örnekleri:**
```java
// CentralControlUnit.java - Polymorphism
private List<Device> devices; // Device tipinde liste

public void enableEnergySavingModeForAll() {
    for (Device device : devices) {
        if (device instanceof EnergySaver) { // Tip kontrolü
            ((EnergySaver) device).enableEnergySavingMode();
        }
    }
}
```

### 3.5. Interface (Arayüz)
**Açıklama:** Sınıfların uyması gereken sözleşmeleri tanımlama.

**Uygulama Örnekleri:**
- `RemoteControllable` arayüzü: Uzaktan kontrol özelliği için sözleşme
- `EnergySaver` arayüzü: Enerji tasarrufu özelliği için sözleşme
- Bir sınıf birden fazla arayüzü implement edebilir (ör: SmartLight)

**Kod Örnekleri:**
```java
// SmartLight.java - Çoklu arayüz implementasyonu
public class SmartLight extends Device implements RemoteControllable, EnergySaver {
    // Her iki arayüzün metodlarını implement etmek zorunda
}
```

### 3.6. Method Overloading (Metod Aşırı Yükleme)
**Açıklama:** Aynı isimde farklı parametrelerle metodlar tanımlama.

**Uygulama Örnekleri:**
- `SmartLight.setBrightness()`: 3 farklı versiyon
- `SmartThermostat.setTemperature()`: 3 farklı versiyon
- `SmartTV.setChannel()`: 3 farklı versiyon
- Constructor overloading: Her cihaz sınıfında birden fazla constructor

**Kod Örnekleri:**
```java
// SmartLight.java - Method Overloading
public void setBrightness(int brightness) { ... }
public void setBrightness(int brightness, boolean adjustEnergyMode) { ... }
public void setBrightness(int brightness, String colorTemperature) { ... }
```

### 3.7. Inner Class (İç Sınıf)
**Açıklama:** Bir sınıfın içinde tanımlanan başka bir sınıf.

**Uygulama Örnekleri:**
- `SmartThermostat` sınıfı içinde `TemperatureSchedule` inner class tanımlanmıştır
- Inner class, dış sınıfın private üyelerine erişebilir
- Mantıksal olarak ilişkili sınıfları gruplar

**Kod Örnekleri:**
```java
// SmartThermostat.java - Inner Class
public class SmartThermostat extends Device {
    public class TemperatureSchedule {
        // Dış sınıfın metodunu kullanabilir
        setTemperature(tempToSet);
    }
}
```

### 3.8. Exception Handling (İstisna Yönetimi)
**Açıklama:** Hata durumlarının kontrollü bir şekilde yönetilmesi.

**Uygulama Örnekleri:**
- `DeviceOfflineException`: Özel istisna sınıfı
- `PowerOverloadException`: Özel istisna sınıfı
- try-catch blokları ile istisna yakalama
- throw anahtar kelimesi ile istisna fırlatma

**Kod Örnekleri:**
```java
// DeviceOfflineException.java - Custom Exception
public class DeviceOfflineException extends Exception {
    private String deviceId;
    public DeviceOfflineException(String deviceId) {
        super("Cihaz çevrimdışı: " + deviceId);
    }
}

// SmartLight.java - Exception Handling
public void turnOn() throws DeviceOfflineException {
    if (!isOnline()) {
        throw new DeviceOfflineException(getDeviceId());
    }
}
```

---

## 4. TEKNİK ÖZELLİKLER

### 4.1. Erişim Belirleyiciler
- **private:** Sınıf içi erişim (tüm alanlar)
- **protected:** Alt sınıflardan erişim (`Device.setOn()`)
- **public:** Her yerden erişim (metodlar, constructor'lar)

### 4.2. Constructor Kullanımı
- Her sınıfın en az bir constructor'ı vardır
- Constructor overloading uygulanmıştır
- `super()` ile üst sınıf constructor'ı çağrılır

### 4.3. Metod Sayısı
Projede toplam 50+ metod bulunmaktadır:
- Getter/Setter metodları
- İş mantığı metodları
- Override edilmiş metodlar
- Overload edilmiş metodlar

### 4.4. Exception Handling
- 2 özel exception sınıfı
- try-catch blokları
- throw/catch mekanizması
- Checked exception kullanımı

---

## 5. PROJE YAPISI

```
oop test/
├── Device.java                    (Abstract Base Class)
├── SmartLight.java                (Concrete Class)
├── SmartThermostat.java           (Concrete Class + Inner Class)
├── SmartTV.java                   (Concrete Class)
├── RemoteControllable.java        (Interface)
├── EnergySaver.java               (Interface)
├── DeviceOfflineException.java    (Custom Exception)
├── PowerOverloadException.java    (Custom Exception)
├── CentralControlUnit.java        (Management Class)
└── Main.java                      (Entry Point)
```

---

## 6. SONUÇ

EcoHome projesi, Nesneye Yönelik Programlama prensiplerinin kapsamlı bir şekilde uygulandığı, gerçek dünya senaryolarını simüle eden bir sistemdir. Proje, kalıtım, soyutlama, çok biçimlilik, kapsülleme, arayüz kullanımı, metod aşırı yükleme, iç sınıf ve istisna yönetimi gibi temel OOP kavramlarını başarıyla göstermektedir.

Sistem, genişletilebilir bir mimariye sahiptir ve yeni cihaz tipleri kolayca eklenebilir. Merkezi kontrol ünitesi sayesinde tüm cihazlar tek bir noktadan yönetilebilir ve enerji tüketimi izlenebilir. Exception handling mekanizması ile sistemin güvenilirliği artırılmıştır.

Proje, üniversite düzeyinde OOP eğitimi için uygun  teorik bilgilerin pratik uygulamasını göstermektedir.

---



**Hazırlayan:lar** [Ebubekir Ömer Yeniçağ-Şahin Temel]   
**Ders:** Nesneye Yönelik Programlama (OOP)  
**Proje Adı:** EcoHome - Akıllı Ev Enerji ve Cihaz Yönetim Simülasyonu

# ECOHOME - AKILLI EV ENERJİ VE CİHAZ YÖNETİM SİMÜLASYONU
## Nesneye Yönelik Programlama (OOP) Dönem Projesi

---

## 1. PROJE ÖZETİ

### 1.1. Proje Amacı
EcoHome projesi, akıllı ev sistemlerinde bulunan çeşitli cihazların merkezi bir kontrol ünitesi tarafından yönetilmesini ve enerji tüketiminin izlenmesini simüle eden bir Java uygulamasıdır. Proje, Nesneye Yönelik Programlama (OOP) prensiplerinin kapsamlı bir şekilde uygulanmasını hedeflemektedir. Sistem, farklı türde akıllı cihazların (ampul, termostat, televizyon) oluşturulması, yönetilmesi, enerji tüketimlerinin hesaplanması ve istisna durumlarının yönetilmesi gibi gerçek dünya senaryolarını simüle etmektedir.

### 1.2. Proje Kapsamı
Proje, aşağıdaki temel işlevleri içermektedir:
- Farklı türde akıllı cihazların tanımlanması ve yönetimi
- Cihazların açılması/kapatılması ve durum takibi
- Enerji tüketiminin hesaplanması ve izlenmesi
- Güç limiti kontrolü ve aşım durumlarının yönetimi
- Çevrimdışı cihaz durumlarının işlenmesi
- Enerji tasarruf modlarının aktif edilmesi
- Uzaktan kontrol özelliklerinin simülasyonu

---

## 2. PROJE MİMARİSİ VE SINIF YAPISI

### 2.1. Sınıf Hiyerarşisi

```
Device (Abstract Class)
├── SmartLight
├── SmartThermostat
│   └── TemperatureSchedule (Inner Class)
└── SmartTV

RemoteControllable (Interface)
EnergySaver (Interface)

DeviceOfflineException (Custom Exception)
PowerOverloadException (Custom Exception)

CentralControlUnit
Main
```

### 2.2. Modül Açıklamaları

#### 2.2.1. Device (Soyut Sınıf)
**Konum:** `Device.java`  
**Rol:** Tüm akıllı cihazların ortak özelliklerini ve davranışlarını tanımlayan soyut temel sınıftır.

**Özellikler:**
- `deviceId`: Cihazın benzersiz kimliği
- `deviceName`: Cihazın adı
- `isOn`: Cihazın açık/kapalı durumu
- `powerConsumption`: Cihazın güç tüketimi (Watt)
- `isOnline`: Cihazın çevrimiçi/çevrimdışı durumu

**Soyut Metodlar:**
- `turnOn()`: Cihazı açma (alt sınıflar implement eder)
- `turnOff()`: Cihazı kapatma (alt sınıflar implement eder)
- `calculateDailyEnergyConsumption()`: Günlük enerji tüketimini hesaplama (alt sınıflar implement eder)

**OOP Prensibi:** Abstraction (Soyutlama) - Ortak davranışları tanımlar, detayları alt sınıflara bırakır.

#### 2.2.2. SmartLight (Akıllı Ampul)
**Konum:** `SmartLight.java`  
**Rol:** Akıllı ampul cihazını temsil eden somut sınıftır.

**Kalıtım:** `Device` sınıfından türer  
**Arayüzler:** `RemoteControllable`, `EnergySaver` arayüzlerini implement eder

**Özel Özellikler:**
- `brightness`: Parlaklık seviyesi (0-100)
- `colorTemperature`: Renk sıcaklığı ("Warm", "Cool", "Daylight")
- `energySavingMode`: Enerji tasarruf modu durumu

**Önemli Metodlar:**
- `setBrightness(int brightness)`: Parlaklık ayarlama (3 farklı overload versiyonu)
- `enableEnergySavingMode()`: Enerji tasarruf modunu aktif etme
- `calculateEnergySaved()`: Tasarruf edilen enerjiyi hesaplama

**OOP Prensipleri:**
- **Inheritance (Kalıtım):** Device sınıfından türer
- **Polymorphism (Çok Biçimlilik):** Device tipinde kullanılabilir
- **Method Overloading:** setBrightness metodunun 3 farklı versiyonu

#### 2.2.3. SmartThermostat (Akıllı Termostat)
**Konum:** `SmartThermostat.java`  
**Rol:** Akıllı termostat cihazını temsil eden somut sınıftır.

**Kalıtım:** `Device` sınıfından türer  
**Arayüzler:** `RemoteControllable` arayüzünü implement eder

**Özel Özellikler:**
- `currentTemperature`: Mevcut sıcaklık
- `targetTemperature`: Hedef sıcaklık
- `mode`: Çalışma modu ("Heating", "Cooling", "Auto")

**İç Sınıf:**
- `TemperatureSchedule`: Günlük sıcaklık programını yöneten inner class
  - Sabah, öğle, akşam ve gece sıcaklıklarını saklar
  - Saate göre otomatik sıcaklık ayarlama yapar

**Önemli Metodlar:**
- `setTemperature(double temperature)`: Sıcaklık ayarlama (3 farklı overload versiyonu)
- `TemperatureSchedule.applySchedule(int hour)`: Programlı sıcaklık ayarlama

**OOP Prensipleri:**
- **Inheritance (Kalıtım):** Device sınıfından türer
- **Inner Class:** TemperatureSchedule iç sınıfı ile mantıksal gruplama
- **Method Overloading:** setTemperature metodunun 3 farklı versiyonu

#### 2.2.4. SmartTV (Akıllı Televizyon)
**Konum:** `SmartTV.java`  
**Rol:** Akıllı televizyon cihazını temsil eden somut sınıftır.

**Kalıtım:** `Device` sınıfından türer  
**Arayüzler:** `RemoteControllable`, `EnergySaver` arayüzlerini implement eder

**Özel Özellikler:**
- `volume`: Ses seviyesi (0-100)
- `channel`: Aktif kanal
- `currentApp`: Aktif uygulama ("Netflix", "YouTube", vb.)
- `energySavingMode`: Enerji tasarruf modu durumu

**Önemli Metodlar:**
- `setChannel(int channel)`: Kanal ayarlama (3 farklı overload versiyonu)
- `enableEnergySavingMode()`: Enerji tasarruf modunu aktif etme

**OOP Prensipleri:**
- **Inheritance (Kalıtım):** Device sınıfından türer
- **Polymorphism (Çok Biçimlilik):** Device tipinde kullanılabilir
- **Method Overloading:** setChannel metodunun 3 farklı versiyonu

#### 2.2.5. RemoteControllable (Arayüz)
**Konum:** `RemoteControllable.java`  
**Rol:** Uzaktan kontrol edilebilir cihazlar için sözleşme tanımlar.

**Metodlar:**
- `remoteTurnOn()`: Uzaktan açma
- `remoteTurnOff()`: Uzaktan kapatma
- `checkRemoteStatus()`: Uzaktan durum kontrolü

**OOP Prensibi:** Interface Segregation - Belirli bir davranış için sözleşme tanımlar.

#### 2.2.6. EnergySaver (Arayüz)
**Konum:** `EnergySaver.java`  
**Rol:** Enerji tasarrufu yapabilen cihazlar için sözleşme tanımlar.

**Metodlar:**
- `enableEnergySavingMode()`: Enerji tasarruf modunu aktif etme
- `disableEnergySavingMode()`: Enerji tasarruf modunu kapatma
- `isEnergySavingModeActive()`: Enerji tasarruf modu durumu
- `calculateEnergySaved()`: Tasarruf edilen enerjiyi hesaplama

**OOP Prensibi:** Interface Segregation - Enerji tasarrufu özelliği için ayrı sözleşme.

#### 2.2.7. DeviceOfflineException (Özel İstisna)
**Konum:** `DeviceOfflineException.java`  
**Rol:** Cihaz çevrimdışı olduğunda fırlatılan özel istisna sınıfıdır.

**Özellikler:**
- `deviceId`: Çevrimdışı olan cihazın kimliği

**OOP Prensibi:** Exception Handling - Özel durumlar için özelleştirilmiş istisna sınıfı.

#### 2.2.8. PowerOverloadException (Özel İstisna)
**Konum:** `PowerOverloadException.java`  
**Rol:** Güç limiti aşıldığında fırlatılan özel istisna sınıfıdır.

**Özellikler:**
- `currentPower`: Mevcut güç tüketimi
- `maxPower`: Maksimum izin verilen güç

**OOP Prensibi:** Exception Handling - Güç aşımı durumları için özelleştirilmiş istisna sınıfı.

#### 2.2.9. CentralControlUnit (Merkezi Kontrol Ünitesi)
**Konum:** `CentralControlUnit.java`  
**Rol:** Tüm cihazları yöneten ve enerji tüketimini kontrol eden merkezi yönetim sınıfıdır.

**Özellikler:**
- `devices`: Yönetilen cihazların listesi
- `maxTotalPower`: Maksimum toplam güç limiti
- `unitName`: Kontrol ünitesinin adı

**Önemli Metodlar:**
- `addDevice(Device device)`: Cihaz ekleme
- `removeDevice(Device device)`: Cihaz çıkarma
- `turnOnAllDevices()`: Tüm cihazları açma
- `turnOffAllDevices()`: Tüm cihazları kapatma
- `calculateTotalPowerConsumption()`: Toplam güç tüketimini hesaplama (PowerOverloadException fırlatabilir)
- `safeTurnOnDevice(Device device)`: Güvenli cihaz açma (güç kontrolü ile)
- `enableEnergySavingModeForAll()`: Tüm cihazlarda enerji tasarruf modunu aktif etme

**OOP Prensipleri:**
- **Composition:** Device nesnelerini içerir
- **Polymorphism:** Device tipinde nesnelerle çalışır
- **Exception Handling:** try-catch blokları ile hata yönetimi

#### 2.2.10. Main (Ana Sınıf)
**Konum:** `Main.java`  
**Rol:** Programın giriş noktası ve simülasyon senaryolarını çalıştıran sınıftır.

**İşlevler:**
- Kullanıcı menüsü sunar
- Cihazları oluşturur ve sisteme ekler
- 10 farklı simülasyon senaryosunu çalıştırır:
  1. Cihazları açma
  2. Güç tüketimi kontrolü
  3. Method overloading örnekleri
  4. Inner class kullanımı
  5. Çevrimdışı cihaz exception handling
  6. Güç aşımı exception handling
  7. Güvenli cihaz açma
  8. Enerji tasarruf modu
  9. Rastgele durumlar
  10. Final durum raporu

---

## 3. OOP PRENSİPLERİNİN UYGULANMASI

### 3.1. Encapsulation (Kapsülleme)
**Açıklama:** Verilerin ve metodların erişim kontrolü ile korunması.

**Uygulama Örnekleri:**
- Tüm sınıflarda private alanlar kullanılmıştır
- Getter ve setter metodları ile kontrollü erişim sağlanmıştır
- `Device` sınıfında `setOn()` metodu `protected` olarak tanımlanmıştır (sadece alt sınıflar erişebilir)

**Kod Örnekleri:**
```java
// Device.java - Private alanlar
private String deviceId;
private String deviceName;
private boolean isOn;

// Protected metod - sadece alt sınıflar erişebilir
protected void setOn(boolean on) {
    isOn = on;
}
```

### 3.2. Inheritance (Kalıtım)
**Açıklama:** Bir sınıfın başka bir sınıftan özellik ve davranışları devralması.

**Uygulama Örnekleri:**
- `SmartLight`, `SmartThermostat`, `SmartTV` sınıfları `Device` sınıfından türer
- Alt sınıflar `super()` anahtar kelimesi ile üst sınıf constructor'ını çağırır
- Alt sınıflar üst sınıfın soyut metodlarını implement eder

**Kod Örnekleri:**
```java
// SmartLight.java - Kalıtım
public class SmartLight extends Device {
    public SmartLight(String deviceId, String deviceName, double powerConsumption) {
        super(deviceId, deviceName, powerConsumption); // Üst sınıf constructor'ı
        // ...
    }
    
    @Override
    public void turnOn() throws DeviceOfflineException {
        // Üst sınıfın soyut metodunu implement etme
    }
}
```

### 3.3. Abstraction (Soyutlama)
**Açıklama:** Karmaşık sistemleri basitleştirerek sadece gerekli detayları gösterme.

**Uygulama Örnekleri:**
- `Device` sınıfı abstract olarak tanımlanmıştır
- Ortak davranışlar tanımlanmış, detaylar alt sınıflara bırakılmıştır
- Soyut metodlar ile zorunlu implementasyon sağlanmıştır

**Kod Örnekleri:**
```java
// Device.java - Abstract sınıf
public abstract class Device {
    // Soyut metodlar - alt sınıflar implement etmek zorunda
    public abstract void turnOn() throws DeviceOfflineException;
    public abstract void turnOff();
    public abstract double calculateDailyEnergyConsumption();
}
```

### 3.4. Polymorphism (Çok Biçimlilik)
**Açıklama:** Aynı arayüz veya sınıf tipi üzerinden farklı davranışlar sergileme.

**Uygulama Örnekleri:**
- `CentralControlUnit` sınıfı `Device` tipinde nesnelerle çalışır
- Farklı cihaz tipleri aynı referans tipi üzerinden yönetilir
- `instanceof` operatörü ile tip kontrolü yapılır

**Kod Örnekleri:**
```java
// CentralControlUnit.java - Polymorphism
private List<Device> devices; // Device tipinde liste

public void enableEnergySavingModeForAll() {
    for (Device device : devices) {
        if (device instanceof EnergySaver) { // Tip kontrolü
            ((EnergySaver) device).enableEnergySavingMode();
        }
    }
}
```

### 3.5. Interface (Arayüz)
**Açıklama:** Sınıfların uyması gereken sözleşmeleri tanımlama.

**Uygulama Örnekleri:**
- `RemoteControllable` arayüzü: Uzaktan kontrol özelliği için sözleşme
- `EnergySaver` arayüzü: Enerji tasarrufu özelliği için sözleşme
- Bir sınıf birden fazla arayüzü implement edebilir (ör: SmartLight)

**Kod Örnekleri:**
```java
// SmartLight.java - Çoklu arayüz implementasyonu
public class SmartLight extends Device implements RemoteControllable, EnergySaver {
    // Her iki arayüzün metodlarını implement etmek zorunda
}
```

### 3.6. Method Overloading (Metod Aşırı Yükleme)
**Açıklama:** Aynı isimde farklı parametrelerle metodlar tanımlama.

**Uygulama Örnekleri:**
- `SmartLight.setBrightness()`: 3 farklı versiyon
- `SmartThermostat.setTemperature()`: 3 farklı versiyon
- `SmartTV.setChannel()`: 3 farklı versiyon
- Constructor overloading: Her cihaz sınıfında birden fazla constructor

**Kod Örnekleri:**
```java
// SmartLight.java - Method Overloading
public void setBrightness(int brightness) { ... }
public void setBrightness(int brightness, boolean adjustEnergyMode) { ... }
public void setBrightness(int brightness, String colorTemperature) { ... }
```

### 3.7. Inner Class (İç Sınıf)
**Açıklama:** Bir sınıfın içinde tanımlanan başka bir sınıf.

**Uygulama Örnekleri:**
- `SmartThermostat` sınıfı içinde `TemperatureSchedule` inner class tanımlanmıştır
- Inner class, dış sınıfın private üyelerine erişebilir
- Mantıksal olarak ilişkili sınıfları gruplar

**Kod Örnekleri:**
```java
// SmartThermostat.java - Inner Class
public class SmartThermostat extends Device {
    public class TemperatureSchedule {
        // Dış sınıfın metodunu kullanabilir
        setTemperature(tempToSet);
    }
}
```

### 3.8. Exception Handling (İstisna Yönetimi)
**Açıklama:** Hata durumlarının kontrollü bir şekilde yönetilmesi.

**Uygulama Örnekleri:**
- `DeviceOfflineException`: Özel istisna sınıfı
- `PowerOverloadException`: Özel istisna sınıfı
- try-catch blokları ile istisna yakalama
- throw anahtar kelimesi ile istisna fırlatma

**Kod Örnekleri:**
```java
// DeviceOfflineException.java - Custom Exception
public class DeviceOfflineException extends Exception {
    private String deviceId;
    public DeviceOfflineException(String deviceId) {
        super("Cihaz çevrimdışı: " + deviceId);
    }
}

// SmartLight.java - Exception Handling
public void turnOn() throws DeviceOfflineException {
    if (!isOnline()) {
        throw new DeviceOfflineException(getDeviceId());
    }
}
```

---

## 4. TEKNİK ÖZELLİKLER

### 4.1. Erişim Belirleyiciler
- **private:** Sınıf içi erişim (tüm alanlar)
- **protected:** Alt sınıflardan erişim (`Device.setOn()`)
- **public:** Her yerden erişim (metodlar, constructor'lar)

### 4.2. Constructor Kullanımı
- Her sınıfın en az bir constructor'ı vardır
- Constructor overloading uygulanmıştır
- `super()` ile üst sınıf constructor'ı çağrılır

### 4.3. Metod Sayısı
Projede toplam 50+ metod bulunmaktadır:
- Getter/Setter metodları
- İş mantığı metodları
- Override edilmiş metodlar
- Overload edilmiş metodlar

### 4.4. Exception Handling
- 2 özel exception sınıfı
- try-catch blokları
- throw/catch mekanizması
- Checked exception kullanımı

---

## 5. PROJE YAPISI

```
oop test/
├── Device.java                    (Abstract Base Class)
├── SmartLight.java                (Concrete Class)
├── SmartThermostat.java           (Concrete Class + Inner Class)
├── SmartTV.java                   (Concrete Class)
├── RemoteControllable.java        (Interface)
├── EnergySaver.java               (Interface)
├── DeviceOfflineException.java    (Custom Exception)
├── PowerOverloadException.java    (Custom Exception)
├── CentralControlUnit.java        (Management Class)
└── Main.java                      (Entry Point)
```

---

## 6. SONUÇ

EcoHome projesi, Nesneye Yönelik Programlama prensiplerinin kapsamlı bir şekilde uygulandığı, gerçek dünya senaryolarını simüle eden bir sistemdir. Proje, kalıtım, soyutlama, çok biçimlilik, kapsülleme, arayüz kullanımı, metod aşırı yükleme, iç sınıf ve istisna yönetimi gibi temel OOP kavramlarını başarıyla göstermektedir.

Sistem, genişletilebilir bir mimariye sahiptir ve yeni cihaz tipleri kolayca eklenebilir. Merkezi kontrol ünitesi sayesinde tüm cihazlar tek bir noktadan yönetilebilir ve enerji tüketimi izlenebilir. Exception handling mekanizması ile sistemin güvenilirliği artırılmıştır.

Proje, üniversite düzeyinde OOP eğitimi için uygun  teorik bilgilerin pratik uygulamasını göstermektedir.

---



**Hazırlayan:lar** [Ebubekir Ömer Yeniçağ-Şahin Temel]   
**Ders:** Nesneye Yönelik Programlama (OOP)  
**Proje Adı:** EcoHome - Akıllı Ev Enerji ve Cihaz Yönetim Simülasyonu
