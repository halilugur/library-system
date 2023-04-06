package system.comparator;

import java.util.Comparator;
import system.models.BaseModel;

/**
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 * @param <M> model of data. Data should extended from BaseModel class
 */
public class IdComparator<M extends BaseModel> implements Comparator<M> {

    @Override
    public int compare(M source, M target) {
        return source.getId() - target.getId();
    }
}
