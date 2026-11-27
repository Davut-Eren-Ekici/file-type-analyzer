import java.util.List;
import java.util.Map;
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

    private static final Map<String, List<String>> KATEGORI_UZANTILARI = Map.of(
            "Belge", List.of(
                    ".pdf", ".txt", ".md", ".rtf", ".csv",
                    ".doc", ".docx", ".odt",
                    ".xls", ".xlsx", ".ods",
                    ".ppt", ".pptx", ".odp"),
            "Gorsel", List.of(
                    ".png", ".jpg", ".jpeg", ".gif", ".webp",
                    ".bmp", ".svg", ".ico", ".tiff", ".psd", ".ai"),
            "3D/Tasarim", List.of(
                    ".stl", ".obj", ".3mf", ".blend", ".fbx",
                    ".dae", ".step", ".stp", ".iges", ".igs", ".gcode", ".blend1"),
            "Kod", List.of(
                    ".java", ".kt", ".py", ".js", ".ts", ".tsx", ".jsx",
                    ".html", ".css", ".scss", ".c", ".cpp", ".h", ".cs",
                    ".ino", ".json", ".xml", ".yaml", ".yml", ".sql",
                    ".sh", ".bat", ".ps1"),
            "Arsiv", List.of(
                    ".zip", ".rar", ".7z", ".tar", ".gz", ".bz2", ".xz", ".iso"),
            "Medya", List.of(
                    ".mp3", ".wav", ".ogg", ".m4a", ".flac",
                    ".mp4", ".mkv", ".avi", ".mov", ".webm"));

    private static String turBelirle(String dosyaAdi) {
        String kucukAd = dosyaAdi.toLowerCase();

        for (Map.Entry<String, List<String>> giris : KATEGORI_UZANTILARI.entrySet()) {
            for (String uzanti : giris.getValue()) {
                if (kucukAd.endsWith(uzanti)) {
                    return giris.getKey();
                }
            }
        }

        return "Diger";
    }
}
