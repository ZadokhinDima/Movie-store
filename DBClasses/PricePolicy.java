package DBClasses;



public class PricePolicy {

    float BaseUp, oftenClientOff, usualClientOff, alwaysClientOff, seasonOff, birthDayOff;
    int oftenClient, usualClient, alwaysClient, speed;
    boolean summing;

    public PricePolicy(float baseUp, float oftenClientOff, float usualClientOff, float alwaysClientOff,
                       float seasonOff, float birthDayOff, int oftenClient, int usualClient, int alwaysClient,
                       int speed, boolean summing) {
        BaseUp = baseUp;
        this.oftenClientOff = oftenClientOff;
        this.usualClientOff = usualClientOff;
        this.alwaysClientOff = alwaysClientOff;
        this.seasonOff = seasonOff;
        this.birthDayOff = birthDayOff;
        this.oftenClient = oftenClient;
        this.usualClient = usualClient;
        this.alwaysClient = alwaysClient;
        this.speed = speed;
        this.summing = summing;
    }

    public float getBaseUp() {
        return BaseUp;
    }

    public void setBaseUp(float baseUp) {
        BaseUp = baseUp;
    }

    public float getOftenClientOff() {
        return oftenClientOff;
    }

    public void setOftenClientOff(float oftenClientOff) {
        this.oftenClientOff = oftenClientOff;
    }

    public float getUsualClientOff() {
        return usualClientOff;
    }

    public void setUsualClientOff(float usualClientOff) {
        this.usualClientOff = usualClientOff;
    }

    public float getAlwaysClientOff() {
        return alwaysClientOff;
    }

    public void setAlwaysClientOff(float alwaysClientOff) {
        this.alwaysClientOff = alwaysClientOff;
    }

    public float getSeasonOff() {
        return seasonOff;
    }

    public void setSeasonOff(float seasonOff) {
        this.seasonOff = seasonOff;
    }

    public float getBirthDayOff() {
        return birthDayOff;
    }

    public void setBirthDayOff(float birthDayOff) {
        this.birthDayOff = birthDayOff;
    }

    public int getOftenClient() {
        return oftenClient;
    }

    public void setOftenClient(int oftenClient) {
        this.oftenClient = oftenClient;
    }

    public int getUsualClient() {
        return usualClient;
    }

    public void setUsualClient(int usualClient) {
        this.usualClient = usualClient;
    }

    public int getAlwaysClient() {
        return alwaysClient;
    }

    public void setAlwaysClient(int alwaysClient) {
        this.alwaysClient = alwaysClient;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isSumming() {
        return summing;
    }

    public void setSumming(boolean summing) {
        this.summing = summing;
    }
}
