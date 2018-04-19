package ly.mysummery03;

public class InfoBean {
    private String info;
    private int id;

    public InfoBean(String info, int id) {
        this.info = info;
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
