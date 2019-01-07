package net.explodingbush.ksoftapi.entities;

<<<<<<< HEAD
public interface BulkBan {

    BulkBan addId(String id);
    BulkBan removeId(String id);
    String toString();
=======
import net.explodingbush.ksoftapi.entities.actions.BanAction;

public interface BulkBan {



    BulkBan addId(String id);
    BulkBan addIds(String... ids);
    BulkBan removeId(String id);

    @Override
    String toString();

>>>>>>> development
    BanAction set();

}
