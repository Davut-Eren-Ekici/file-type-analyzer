import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileTypeAnalyzer {
    public static void main(String[] args) {
        String klasorYolu;

        if (args.length > 0 && !args[0].isBlank()) {
            klasorYolu = args[0];
        } else {
            klasorYolu = "test-dosyalari";
        }

        Path hedefKlasor = Path.of(klasorYolu);

        if (!Files.exists(hedefKlasor) || !Files.isDirectory(hedefKlasor)) {
            System.out.println("Klasör Bulunamadı: " + hedefKlasor.toAbsolutePath());
            return;
        }

        System.out.println("Taranan klasor: " + hedefKlasor.toAbsolutePath());
        System.out.println("----------------------------------------");

        try (Stream<Path> dosyalar = Files.list(hedefKlasor)) {
            dosyalar
                    .filter(Files::isRegularFile)// alt klasörleri eler yanlızca normal dosyaları geçirir
                    .forEach(dosya -> {
                        String dosyaAdi = dosya.getFileName().toString();
                        String tur = turBelirle(dosyaAdi);
                        System.out.println(dosyaAdi + "  -->  " + tur);

                    });
        } catch (IOException e) {
            System.out.println("Dosyalar Okunurken Bir Hata Oluştu" + e.getMessage());
        }
    }

    public static String turBelirle(String dosyaAdi) {
        String kucukAd = dosyaAdi.toLowerCase();
        if (kucukAd.endsWith(".png") || kucukAd.endsWith(".jpg") || kucukAd.endsWith(".jpeg")
                || kucukAd.endsWith(".webp")) {
            return "Görsel";
        } else if (kucukAd.endsWith(".pdf") || kucukAd.endsWith(".docx") || kucukAd.endsWith(".txt")
                || kucukAd.endsWith(".xlsx")) {
            return "Belge";
        } else if (kucukAd.endsWith(".zip") || kucukAd.endsWith(".rar") || kucukAd.endsWith(".7z")) {
            return "Arsiv";
        } else if (kucukAd.endsWith(".java") || kucukAd.endsWith(".py") || kucukAd.endsWith(".js")
                || kucukAd.endsWith(".html") || kucukAd.endsWith(".css")) {
            return "Kod";
        }
        return "Diger";
    }
}
