package quan.hust.itjapanese.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import quan.hust.itjapanese.domain.Book;

@Component
public class CSVHelper
{
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Title", "Author", "Category", "Price", "Rating" };

  public static final String BOOK_CONTENT_DELIMITER = ",";

  public static boolean hasCSVFormat(MultipartFile file)
  {

    if (!TYPE.equals(file.getContentType()))
    {
      return false;
    }

    return true;
  }

  public List<Book> csvToBooks(MultipartFile file)
  {
    List<Book> books = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)))
    {

      readFromFile(br, books);


    }
    catch (IOException e)
    {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
    return books;
  }

  private void readFromFile(BufferedReader br, List<Book> books) throws IOException
  {
    int lineNumber = 0;

    String line = br.readLine();
    while ((line = br.readLine()) != null)
    {
      lineNumber += 1;

      // If any line before was invalid -> no need to import the valid lines
      if (StringUtils.hasText(line))
      {
        Book book = convertToObject(line);
        books.add(book);

      }
    }
  }

  private Book convertToObject(String line)
  {
    String[] values = line.split(BOOK_CONTENT_DELIMITER);


    // if password is configured
    String name = values[0];
    String author = values[1];
    String description = values[2];
    String category = values[3];
    Double price = Double.parseDouble(values[4]);
    String level = values[5];
    Integer studyTime = Integer.parseInt(values[6]);
    String imageUrl = values[7];


    return Book.builder()
      .name(name)
      .author(author)
      .category(category)
      .price(price)
      .description(description)
      .timeLearn(studyTime)
      .level(level)
      .imageUrl(imageUrl)
      .build();
  }

}
