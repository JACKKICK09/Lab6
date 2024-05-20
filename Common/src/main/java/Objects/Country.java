package Objects;

public enum Country{
    UNITED_KINGDOM,
    GERMANY,
    CHINA,
    ITALY,
    THAILAND;

    public static String names() {
        String nameList = "";
        for (Country countryType : values()) {
            nameList += countryType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
