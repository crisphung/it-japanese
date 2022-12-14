package quan.hust.itjapanese.controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import quan.hust.itjapanese.dto.request.LoginRequest;
import quan.hust.itjapanese.dto.request.SignUpRequest;
import quan.hust.itjapanese.dto.response.AuthResponse;
import quan.hust.itjapanese.services.UserService;
import quan.hust.itjapanese.utils.FileUploadUtils;

@RestController
public class UserController implements UserOperations
{
  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";
  @Autowired
  private UserService userService;


  @Override
  public ResponseEntity<AuthResponse> login(LoginRequest request)
  {

    return ResponseEntity.ok(userService.login(request));
  }

  @Override
  public ResponseEntity<AuthResponse> signup(SignUpRequest request)
  {
    return ResponseEntity.ok(userService.signup(request));
  }

  @Override
  public String update(MultipartFile imageFile) throws IOException
  {
    Path filePath = null;
    try
    {
      String uploadDir = UPLOAD_DIRECTORY + FileUploadUtils.extractUploadDirectory();
      filePath = FileUploadUtils.saveFile(uploadDir,imageFile.getOriginalFilename(),imageFile);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    String fileName = String.format("%s%s/%s",UPLOAD_DIRECTORY,FileUploadUtils.extractUploadDirectory(),imageFile.getOriginalFilename());
    File file2 = new File(fileName);
    File  file = new File(filePath.toString());

    InputStream in = new FileInputStream(file2);

    return  Base64.encodeBase64String(IOUtils.toByteArray(in));
  }
}
