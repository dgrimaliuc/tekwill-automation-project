package denis_grimaliuc.data.enums;

public enum Status {

    ADOPTED("adopted"),
    APPROVED("approved"),
    ONHOLD("onhold"),
    AVAILABLE("available"),
    DENIED("denied");

    Status(String status) {

    }

    public static Status fromString(String status) {
        return switch (status) {
            case "adopted" -> ADOPTED;
            case "approved" -> APPROVED;
            case "onhold" -> ONHOLD;
            case "available" -> AVAILABLE;
            case "denied" -> DENIED;
            default -> Status.valueOf(status.toUpperCase());
        };
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}
