package net.explodingbush.ksoftapi.entities;

public interface BulkBan {

    BulkBan addId(String id);
    BulkBan removeId(String id);
    String toString();
    BanAction set();

}
