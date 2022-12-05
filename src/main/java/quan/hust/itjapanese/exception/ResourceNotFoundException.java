package quan.hust.itjapanese.exception;

/**
 * This exception should used to indicate resource not found. Mapping with HttpStatus.NOT_FOUND(404)
 */
public class ResourceNotFoundException extends RuntimeException
{

  private static final long serialVersionUID = -7365959061670222915L;

  public ResourceNotFoundException(String message)
  {
    super(message);
  }
}
