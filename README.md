# 🏠 EcoHome - Akıllı Ev Enerji ve Cihaz Yönetim Simülasyonu

![Java](https://img.shields.io/badge/Language-Java-orange) ![OOP](https://img.shields.io/badge/Concepts-OOP-blue) ![License](https://img.shields.io/badge/License-MIT-green)

**EcoHome**, Nesneye Yönelik Programlama (OOP) prensiplerini gerçek hayat senaryosu üzerinde simüle eden, Java tabanlı bir akıllı ev yönetim sistemidir. Bu proje, cihazların polimorfik olarak yönetilmesini, enerji tüketiminin hesaplanmasını ve sistem hatalarının (exception handling) yönetimini kapsar.

---

## 🚀 Projenin Amacı ve Özellikleri

Bu simülasyon, bir evin elektrik altyapısını ve akıllı cihazlarını modelleyerek aşağıdaki işlemleri otomatik gerçekleştirir:

* 📱 **Merkezi Yönetim:** Farklı türdeki cihazların (TV, Ampul, Termostat) tek bir noktadan kontrolü.
* ⚡ **Enerji Takibi:** Anlık güç tüketimi hesaplama ve günlük kWh raporlaması.
* 🛡️ **Güvenlik Simülasyonu:** Güç aşımı (`PowerOverloadException`) ve bağlantı kopukluğu (`DeviceOfflineException`) senaryoları.
* 🌡️ **Otomasyon:** Termostat için zamanlamaya dayalı sıcaklık programlama (Inner Class yapısı).
* 💡 **Tasarruf Modu:** Destekleyen cihazlar için enerji tasarruf protokolleri.

---

## 🛠️ Teknik Mimari ve Sınıf Yapısı

Proje, **SOLID** prensiplerine ve **OOP** temellerine sadık kalınarak geliştirilmiştir.

### Sınıf Hiyerarşisi
```text
Device (Abstract Class)
├── SmartLight (Aydınlatma)
├── SmartTV (Eğlence Sistemleri)
└── SmartThermostat (İklimlendirme)
    └── TemperatureSchedule (Inner Class)

Interfaces:
├── RemoteControllable (Uzaktan Erişim)
└── EnergySaver (Güç Tasarrufu)

Exceptions:
├── DeviceOfflineException (Cihaz Çevrimdışı)
└── PowerOverloadException (Güç Aşımı)

Management:
├── CentralControlUnit (Merkezi Kontrol)
└── Main (Giriş Noktası)
```

### Modül Açıklamaları

| Sınıf | Tip | Açıklama |
|-------|-----|----------|
| `Device` | Abstract Class | Tüm akıllı cihazların temel sınıfı. Ortak özellikler ve soyut metodlar tanımlar. |
| `SmartLight` | Concrete Class | Akıllı ampul. Parlaklık ve renk sıcaklığı kontrolü. |
| `SmartTV` | Concrete Class | Akıllı televizyon. Kanal, ses ve uygulama yönetimi. |
| `SmartThermostat` | Concrete Class | Akıllı termostat. Sıcaklık kontrolü ve programlama. |
| `TemperatureSchedule` | Inner Class | Termostat için günlük sıcaklık programı. |
| `RemoteControllable` | Interface | Uzaktan kontrol özelliği için sözleşme. |
| `EnergySaver` | Interface | Enerji tasarrufu özelliği için sözleşme. |
| `CentralControlUnit` | Management | Tüm cihazları yöneten merkezi kontrol ünitesi. |
| `DeviceOfflineException` | Exception | Cihaz çevrimdışı durumu için özel istisna. |
| `PowerOverloadException` | Exception | Güç limiti aşımı için özel istisna. |

---

## 📦 Kurulum

### Gereksinimler
- **Java Development Kit (JDK)** 8 veya üzeri
- Terminal/Command Prompt erişimi

### Derleme ve Çalıştırma

#### Yöntem 1: Otomatik Build (Önerilen)
```bash
# Windows için
build.bat

# JAR dosyasını çalıştır
java -jar EcoHome.jar
```

#### Yöntem 2: Manuel Derleme
```bash
# Tüm Java dosyalarını derle
javac *.java

# JAR dosyası oluştur
jar cvfm EcoHome.jar MANIFEST.MF *.class

# Çalıştır
java -jar EcoHome.jar
```

#### Yöntem 3: Doğrudan Çalıştırma
```bash
# Derle
javac *.java

# Çalıştır
java Main
```

---

## 💻 Kullanım

Program başlatıldığında kullanıcıya bir menü sunulur:

```
========================================
  EcoHome - Akıllı Ev Simülasyonu
========================================

Lütfen bir seçenek seçin:
1. Otomatik Simülasyonu Başlat
2. Çıkış

Seçiminiz: 
```

### Simülasyon Senaryoları

Program, seçenek 1 seçildiğinde aşağıdaki 10 senaryoyu otomatik olarak çalıştırır:

1. **Cihazları Açma** - Farklı cihazların açılması
2. **Güç Tüketimi Kontrolü** - Anlık enerji tüketimi hesaplama
3. **Method Overloading** - Aynı metodun farklı parametrelerle kullanımı
4. **Inner Class** - Termostat için sıcaklık programlama
5. **Exception Handling (Çevrimdışı)** - Cihaz bağlantı hatası simülasyonu
6. **Exception Handling (Güç Aşımı)** - Güç limiti aşımı senaryosu
7. **Güvenli Cihaz Açma** - Güç kontrolü ile güvenli açma
8. **Enerji Tasarruf Modu** - Tasarruf protokollerinin aktif edilmesi
9. **Rastgele Durumlar** - Rastgele cihaz durumları oluşturma
10. **Final Durum Raporu** - Sistem durumu özeti

---

## 🎯 OOP Prensipleri ve Uygulamaları

### 1. Encapsulation (Kapsülleme)
Verilerin ve metodların erişim kontrolü ile korunması.

**Örnek:**
```java
// Device.java - Private alanlar ve protected metod
private String deviceId;
private boolean isOn;

protected void setOn(boolean on) {
    isOn = on; // Sadece alt sınıflar erişebilir
}
```

### 2. Inheritance (Kalıtım)
Bir sınıfın başka bir sınıftan özellik ve davranışları devralması.

**Örnek:**
```java
// SmartLight.java - Device sınıfından türer
public class SmartLight extends Device {
    public SmartLight(...) {
        super(deviceId, deviceName, powerConsumption);
    }
    
    @Override
    public void turnOn() throws DeviceOfflineException {
        // Üst sınıfın soyut metodunu implement eder
    }
}
```

### 3. Abstraction (Soyutlama)
Karmaşık sistemleri basitleştirerek sadece gerekli detayları gösterme.

**Örnek:**
```java
// Device.java - Abstract sınıf
public abstract class Device {
    // Soyut metodlar - alt sınıflar implement etmek zorunda
    public abstract void turnOn() throws DeviceOfflineException;
    public abstract void turnOff();
    public abstract double calculateDailyEnergyConsumption();
}
```

### 4. Polymorphism (Çok Biçimlilik)
Aynı arayüz veya sınıf tipi üzerinden farklı davranışlar sergileme.

**Örnek:**
```java
// CentralControlUnit.java - Polymorphism
private List<Device> devices; // Device tipinde liste

public void enableEnergySavingModeForAll() {
    for (Device device : devices) {
        if (device instanceof EnergySaver) {
            ((EnergySaver) device).enableEnergySavingMode();
        }
    }
}
```

### 5. Interface (Arayüz)
Sınıfların uyması gereken sözleşmeleri tanımlama.

**Örnek:**
```java
// SmartLight.java - Çoklu arayüz implementasyonu
public class SmartLight extends Device 
    implements RemoteControllable, EnergySaver {
    // Her iki arayüzün metodlarını implement eder
}
```

### 6. Method Overloading (Metod Aşırı Yükleme)
Aynı isimde farklı parametrelerle metodlar tanımlama.

**Örnek:**
```java
// SmartLight.java - Method Overloading
public void setBrightness(int brightness) { ... }
public void setBrightness(int brightness, boolean adjustEnergyMode) { ... }
public void setBrightness(int brightness, String colorTemperature) { ... }
```

### 7. Inner Class (İç Sınıf)
Bir sınıfın içinde tanımlanan başka bir sınıf.

**Örnek:**
```java
// SmartThermostat.java - Inner Class
public class SmartThermostat extends Device {
    public class TemperatureSchedule {
        public void applySchedule(int hour) {
            setTemperature(tempToSet); // Dış sınıfın metodunu kullanır
        }
    }
}
```

### 8. Exception Handling (İstisna Yönetimi)
Hata durumlarının kontrollü bir şekilde yönetilmesi.

**Örnek:**
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

## 📁 Proje Yapısı

```
EcoHome/
├── Device.java                    # Abstract Base Class
├── SmartLight.java                # Concrete Class (Aydınlatma)
├── SmartThermostat.java           # Concrete Class + Inner Class (İklimlendirme)
├── SmartTV.java                   # Concrete Class (Eğlence)
├── RemoteControllable.java        # Interface (Uzaktan Kontrol)
├── EnergySaver.java               # Interface (Enerji Tasarrufu)
├── DeviceOfflineException.java    # Custom Exception
├── PowerOverloadException.java    # Custom Exception
├── CentralControlUnit.java        # Management Class
├── Main.java                      # Entry Point
├── MANIFEST.MF                    # JAR Manifest
├── build.bat                      # Build Script
└── README.md                      # Bu dosya
```

---

## 🔧 Teknik Detaylar

### Erişim Belirleyicileri
- **`private`**: Sınıf içi erişim (tüm alanlar)
- **`protected`**: Alt sınıflardan erişim (`Device.setOn()`)
- **`public`**: Her yerden erişim (metodlar, constructor'lar)

### Constructor Kullanımı
- Her sınıfın en az bir constructor'ı vardır
- Constructor overloading uygulanmıştır
- `super()` ile üst sınıf constructor'ı çağrılır

### Metod İstatistikleri
- **Toplam Metod Sayısı:** 50+
- Getter/Setter metodları
- İş mantığı metodları
- Override edilmiş metodlar
- Overload edilmiş metodlar

### Exception Handling
- **2 Özel Exception Sınıfı:**
  - `DeviceOfflineException`
  - `PowerOverloadException`
- try-catch blokları
- throw/catch mekanizması
- Checked exception kullanımı

---

## 🧪 Test Senaryoları

Proje, aşağıdaki senaryoları test eder:

1. ✅ Cihaz oluşturma ve sisteme ekleme
2. ✅ Cihazları açma/kapatma işlemleri
3. ✅ Güç tüketimi hesaplama
4. ✅ Güç limiti kontrolü ve aşım durumu
5. ✅ Çevrimdışı cihaz durumu yönetimi
6. ✅ Enerji tasarruf modu aktif etme
7. ✅ Uzaktan kontrol simülasyonu
8. ✅ Inner class kullanımı
9. ✅ Method overloading örnekleri
10. ✅ Polimorfik cihaz yönetimi

---

## 🤝 Katkıda Bulunma

Bu proje bir eğitim projesidir. Katkılarınızı memnuniyetle karşılarız!

1. Bu repository'yi fork edin
2. Feature branch oluşturun (`git checkout -b feature/AmazingFeature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some AmazingFeature'`)
4. Branch'inizi push edin (`git push origin feature/AmazingFeature`)
5. Bir Pull Request oluşturun

---


## 👥 Yazarlar

- **Ebubekir Ömer Yeniçağ**
- **Şahin Temel**

---



<div align="center">
  <p>⭐ Bu projeyi beğendiyseniz yıldız vermeyi unutmayın! ⭐</p>
</div>
