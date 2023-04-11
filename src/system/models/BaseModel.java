package system.models;

/**
 * The BaseModel class is an abstract class that serves as a base for all models
 * in the application. It contains an id field that is common to all models.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public abstract class BaseModel {

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
