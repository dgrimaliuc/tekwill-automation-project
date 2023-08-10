package denis_grimaliuc.data.enums;

public enum Colors {


    BLUE_BACK_COLOR("rgba(29, 78, 216, 1)"),
    ORANGE_BACK_COLOR("rgba(234, 88, 12, 1)"),
    GREEN_BACK_COLOR("rgba(22, 163, 74, 1)"),
    RED_BACK_COLOR("rgba(220, 38, 38, 1)"),
    GRAY_BACK_COLOR("rgba(209, 213, 219, 1)");

    public final String color;


    Colors(String color) {
        this.color = color;
    }
}
