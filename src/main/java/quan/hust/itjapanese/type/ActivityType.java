package quan.hust.itjapanese.type;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityType
{
  LIKE("LIKE"),
  UNLIKE("UNLIKE"),

  UNDISLIKE("UNDISLIKE"),
  DISLIKE("DISLIKE"),
  EDIT("EDIT");

  private final String value;

  private static final Map<String, ActivityType> mapping = new HashMap<>();

  static
  {
    for (ActivityType activity : values())
    {
      mapping.put(activity.getValue(), activity);
    }
  }

  public static ActivityType resolve(String actionStr)
  {
    return mapping.get(actionStr);
  }

}
