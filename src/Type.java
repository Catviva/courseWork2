public enum Type {

    WORK("Рабочая"),
    PERSONAL("Личная");

    public String translation;

    Type(String translation) {
        this.translation = translation;

    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public String toString() {
        return translation  + super.toString();
    }
}
