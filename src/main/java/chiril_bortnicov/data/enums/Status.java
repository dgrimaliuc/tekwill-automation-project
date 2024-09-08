package chiril_bortnicov.data.enums;

public enum Status {
    ADOPTED,
    APPROVED,
    ONHOLD,
    AVAILABLE,
    DENIED;
    

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
