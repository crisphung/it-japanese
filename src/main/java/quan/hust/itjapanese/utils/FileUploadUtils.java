package quan.hust.itjapanese.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils
{
  public static String extractUploadDirectory()
  {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
    LocalDateTime now = LocalDateTime.now();
    return dtf.format(now);
  }
  public static Path saveFile(String uploadDir, String fileName,
    MultipartFile multipartFile) throws IOException
  {
    Path filePath = null;
    Path uploadPath = Paths.get(uploadDir);
    if (!Files.exists(uploadPath))
    {
      Files.createDirectories(uploadPath);
    }

    try (InputStream inputStream = multipartFile.getInputStream())
    {
      filePath = uploadPath.resolve(fileName);
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
      return filePath;
    }
    catch (IOException ioe)
    {
      throw new IOException("Could not save image file: " + fileName, ioe);
    }
  }
}
