package amjad.fayad.dev.assessment4.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "currency")
public class Currency {

    @PrimaryKey(autoGenerate = true)
    private Integer localId;

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

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getVbdl() {
        return vbdl;
    }

    public void setVbdl(Integer vbdl) {
        this.vbdl = vbdl;
    }

    public Integer getRealvalue() {
        return realvalue;
    }

    public void setRealvalue(Integer realvalue) {
        this.realvalue = realvalue;
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
