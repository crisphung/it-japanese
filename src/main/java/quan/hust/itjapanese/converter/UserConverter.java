package quan.hust.itjapanese.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import quan.hust.itjapanese.domain.User;
import quan.hust.itjapanese.dto.UserDto;


@Component
public class UserConverter implements EntityConverter<User, UserDto>
{
  public static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";
  @Override
  public UserDto convertToDto(User entity)
  {
    String imagePath =  String.format("%s%s",UPLOAD_DIRECTORY,entity.getProfileImage());
    File file2 = new File(imagePath);
    String profileImage;
    InputStream in = null;
    try
    {
      in = new FileInputStream(file2);
      profileImage = Base64.encodeBase64String(IOUtils.toByteArray(in));
    }
    catch (FileNotFoundException e)
    {
      profileImage = null;
    }
    catch (IOException e)
    {
      profileImage = null;
    }

    return UserDto.builder()
      .email(entity.getEmail())
      .name(entity.getName())
      .profileImage(profileImage)
      .build();
  }
}
