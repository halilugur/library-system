package system.comparator;

import java.util.Comparator;
import system.models.BaseModel;

/**
 *
 * A comparator for comparing two BaseModel objects based on their ID.
 *
 * @param <M> The type of BaseModel being compared.
 *
 * @author Tolga Baris Pinar
 * @author halilugur
 */
public class IdComparator<M extends BaseModel> implements Comparator<M> {

    @Override
    public int compare(M source, M target) {
        return source.getId() - target.getId();
    }
}
