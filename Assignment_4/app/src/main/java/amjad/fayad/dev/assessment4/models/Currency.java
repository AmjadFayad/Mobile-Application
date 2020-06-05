package amjad.fayad.dev.assessment4.models;

public class Currency {

    private Integer id;
    private String title;
    private String vbdl;
    private Integer realvalue;

    public Currency() {
    }

    public Currency(Integer id, String title, String vbdl, Integer realvalue) {
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

    public String getVbdl() {
        return vbdl;
    }

    public Integer getRealvalue() {
        return realvalue;
    }
}
