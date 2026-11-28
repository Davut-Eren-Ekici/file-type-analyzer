# FileTypeAnalyzer

**FileTypeAnalyzer**, Java 21 ile geliştirilmiş; komut satırı argümanı veya etkileşimli `Scanner` girdisi üzerinden seçilen bir klasördeki dosyaları uzantı kurallarına göre kategorilere ayıran ve çalıştırılabilir `.jar` paketi olarak sunulabilen bir komut satırı (CLI) aracıdır.

---

## Özellikler

- **Esnek Klasör Seçimi:** Komut satırından argümanla (`args[0]`) veya etkileşimli `Scanner` girişiyle hedef klasörü seçme; boş bırakıldığında varsayılan `test-dosyalari` klasörünü kullanma.
- **Tırnaklı Yol Temizleme ve Doğrulama:** Windows yollarında çift tırnak karakterlerini temizleme ve klasörün varlığını / dizin olduğunu `java.nio.file.Path` ve `Files` ile kontrol etme.
- **Genişletilebilir Uzantı Kuralları:** `Map<String, List<String>>` yapısıyla tek merkezden yönetilen dosya türü sınıflandırması.
- **Büyük/Küçük Harf Duyarsız Eşleşme:** Dosya adlarını küçük harfe çevirerek `.PDF`, `.STL`, `.Blend` gibi farklı yazımlarda da tutarlı sonuç üretme.
- **Çalıştırılabilir `.jar` Desteği:** Kaynak kod derlemesinin ardından tek komutla (`java -jar file-type-analyzer.jar`) çalıştırılabilme.

---

## Desteklenen Kategoriler

- **Belge:** `.pdf`, `.txt`, `.md`, `.rtf`, `.csv`, `.doc`, `.docx`, `.odt`, `.xls`, `.xlsx`, `.ods`, `.ppt`, `.pptx`, `.odp`
- **Gorsel:** `.png`, `.jpg`, `.jpeg`, `.gif`, `.webp`, `.bmp`, `.svg`, `.ico`, `.tiff`, `.psd`, `.ai`
- **3D/Tasarim:** `.stl`, `.obj`, `.3mf`, `.blend`, `.blend1`, `.fbx`, `.dae`, `.step`, `.stp`, `.iges`, `.igs`, `.gcode`
- **Kod:** `.java`, `.kt`, `.py`, `.js`, `.ts`, `.tsx`, `.jsx`, `.html`, `.css`, `.scss`, `.c`, `.cpp`, `.h`, `.cs`, `.ino`, `.json`, `.xml`, `.yaml`, `.yml`, `.sql`, `.sh`, `.bat`, `.ps1`
- **Arsiv:** `.zip`, `.rar`, `.7z`, `.tar`, `.gz`, `.bz2`, `.xz`, `.iso`
- **Medya:** `.mp3`, `.wav`, `.ogg`, `.m4a`, `.flac`, `.mp4`, `.mkv`, `.avi`, `.mov`, `.webm`
- **Diger:** Tanımlı listelere girmeyen veya farklı uzantıya sahip dosyalar.

---

## Kurulum ve Kullanım

### Gereksinimler
- **Java (JDK 21)**

### 1. Derleme
```powershell
javac FileTypeAnalyzer.java
2. Çalıştırılabilir .jar Oluşturma
PowerShell
jar --create --file file-type-analyzer.jar --main-class FileTypeAnalyzer *.class
3. Çalıştırma
Etkileşimli mod (Scanner ile yol sorma veya Enter ile varsayılan klasör):

PowerShell
java -jar file-type-analyzer.jar
Doğrudan klasör yolu vererek:

PowerShell
java -jar file-type-analyzer.jar "C:\Users\ekici\OneDrive\Desktop\flas2"
Örnek Çıktı
Plaintext
Taranan klasor: C:\Users\User1\OneDrive\Desktop\flas2
----------------------------------------
index1.html  -->  Kod
OKULLOGO01.psd  -->  Gorsel
MEB_SERTIFIKASI.cer  -->  Diger
Proje Yapısı
Plaintext
file-type-analyzer/
├── FileTypeAnalyzer.java
├── file-type-analyzer.jar   # Derleme çıktısı (yerel kullanım için)
├── .gitignore
└── README.md