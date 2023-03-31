package system.models;

/**
 *
 * @author halilugur
 */
public class BaseModel {

    private Integer id;

    public BaseModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
