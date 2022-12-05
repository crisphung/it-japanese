package quan.hust.itjapanese.type;

public enum BookType
{
  N1("N1"),
  N2("N2"),
  N3("N3"),
  N4("N4"),
  N5("N5");

  private final String text;

  BookType(final String text) {
    this.text = text;
  }
  @Override
  public String toString() {
    return text;
  }
}
