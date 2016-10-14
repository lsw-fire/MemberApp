package lsw.system.member.business.model;

import io.realm.RealmObject;

/**
 * Created by swli on 10/12/2016.
 */
public class ProductCategory extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
