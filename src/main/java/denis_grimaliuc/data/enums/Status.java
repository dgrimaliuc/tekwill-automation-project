package denis_grimaliuc.data.enums;

public enum Status {
    ADOPTED("ADOPTED"),
    APPROVED("APPROVED"),
    ONHOLD("ONHOLD"),
    AVAILABLE("AVAILABLE"),
    DENIED("DENIED");

    //    public static final String APPROVED = ;
    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
