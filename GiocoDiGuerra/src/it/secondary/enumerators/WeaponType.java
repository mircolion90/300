package it.secondary.enumerators;
public enum WeaponType {
    CerbottanaUsurata("CerbottanaUsurata", 7, 8),
    PaneInCassetta("PaneInCassetta", 10, 7),
    PadellaInTeflon("PadellaInTeflon", 12, 6);

    private String type;
    private int atk;
    private int def;

    WeaponType(String type, int atk, int def) {
        this.type = type;
        this.atk = atk;
        this.def = def;
    }

    public String getType() {
        return type;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    @Override
    public String toString() {
        return "WeaponType{" +
                "type='" + type + '\'' +
                ", atk=" + atk +
                ", def=" + def +
                '}';
    }
}




