@echo off
echo ========================================
echo EcoHome JAR Dosyasi Olusturuluyor...
echo ========================================
echo.

REM Eski dosyalari temizle
echo [1/4] Eski dosyalar temizleniyor...
if exist *.class del /Q *.class
if exist EcoHome.jar del /Q EcoHome.jar
echo Tamamlandi.
echo.

REM Java dosyalarini derle
echo [2/4] Java dosyalari derleniyor...
javac *.java
if errorlevel 1 (
    echo HATA: Derleme basarisiz!
    pause
    exit /b 1
)
echo Tamamlandi.
echo.

REM MANIFEST.MF dosyasini olustur (yoksa)
if not exist MANIFEST.MF (
    echo Manifest-Version: 1.0 > MANIFEST.MF
    echo Main-Class: Main >> MANIFEST.MF
    echo.
    echo [3/4] MANIFEST.MF dosyasi olusturuldu.
) else (
    echo [3/4] MANIFEST.MF dosyasi mevcut.
)
echo.

REM JAR dosyasi olustur
echo [4/4] JAR dosyasi olusturuluyor...
jar cvfm EcoHome.jar MANIFEST.MF *.class
if errorlevel 1 (
    echo HATA: JAR olusturma basarisiz!
    pause
    exit /b 1
)
echo Tamamlandi.
echo.

echo ========================================
echo Basariyla tamamlandi!
echo ========================================
echo.
echo JAR dosyasi: EcoHome.jar
echo Calistirmak icin: java -jar EcoHome.jar
echo.
pause

