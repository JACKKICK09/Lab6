package Objects;

public enum Color2 {
    GREEN,
    RED,
    BLUE,
    WHITE,
    BROWN;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var weaponType : values()) {
            nameList.append(weaponType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
