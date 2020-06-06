package amjad.fayad.dev.assessment4.models;

import com.google.gson.annotations.SerializedName;

public class Currency {

    private Integer id;
    private String title;
    private Integer vbdl;
    @SerializedName("realvalue")
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
    public Currency(Integer id, String title, Integer vbdl, Integer realvalue) {
        this.id = id;
        this.title = title;
        this.vbdl = vbdl;
        this.realvalue = realvalue;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getVbdl() {
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
