package amjad.fayad.dev.assessment4.models;

public class Currency {

    private Integer id;
    private String title;
    private String vbdl;
    private Integer realvalue;
    private Integer gap;
    private int image;

    public Currency() {
    }

    /**
     * Constructor for API calls
     * @param id
     * @param title
     * @param vbdl
     * @param realvalue
     */
    public Currency(Integer id, String title, String vbdl, Integer realvalue) {
        this.id = id;
        this.title = title;
        this.vbdl = vbdl;
        this.realvalue = realvalue;
    }

    /**
     * Constructor including GAP to be calculated
     * @param id
     * @param title
     * @param vbdl
     * @param realvalue
     * @param gap
     */
    public Currency(Integer id, String title, String vbdl, Integer realvalue, Integer gap) {
        this.id = id;
        this.title = title;
        this.vbdl = vbdl;
        this.realvalue = realvalue;
        this.gap = gap;
    }

    /**
     * Constructor including gap and image
     * @param id
     * @param title
     * @param vbdl
     * @param realvalue
     * @param gap
     * @param image
     */
    public Currency(Integer id, String title, String vbdl, Integer realvalue, Integer gap, int image) {
        this.id = id;
        this.title = title;
        this.vbdl = vbdl;
        this.realvalue = realvalue;
        this.gap = gap;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getVbdl() {
        return vbdl;
    }

    public Integer getRealvalue() {
        return realvalue;
    }

    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
