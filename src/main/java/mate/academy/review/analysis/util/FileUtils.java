package mate.academy.review.analysis.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {
    public static final String FILE_IN_FILE_SYSTEM = "src/main/resources/Reviews.csv";
    public static final String FILE_IN_INTERNET =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";

    public static File getFileFromSystem() {
        if (!new File(FILE_IN_FILE_SYSTEM).exists()) {
            log.info("Cannot find in the filesystem. Trying to download it from internet");
            getFileFromInternet();
        }
        return new File(FILE_IN_FILE_SYSTEM);
    }

    private static void getFileFromInternet() {
        log.info("Downloading in progress. Please wait!");
        try (BufferedInputStream in =
                     new BufferedInputStream(new URL(FILE_IN_INTERNET).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_IN_FILE_SYSTEM)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            log.info("Download was successful!");
        } catch (IOException e) {
            throw new RuntimeException("Cannot download file");
        }
    }
}
