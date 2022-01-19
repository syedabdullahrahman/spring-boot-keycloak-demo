package com.abdullah.keycloak.model;

import com.abdullah.keycloak.enums.Operation;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MappedSuperclass
public abstract class AbstractModel<U extends Serializable> implements Serializable {

    private static final long serialVersionUID = -6323358535657100144L;

    @Id
    @GeneratedValue
    private U id;

    public U getId() {
        return this.id;
    }

    public void setId(U id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractModel<?> other = (AbstractModel<?>) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    public static List<String> getIgnorablePropertiesAtBeanCopy(Operation operation){
        String properties[] = getIgnorablePropertiesBasedOnOp(operation);
        List<String> propList = new ArrayList<>(Arrays.asList(properties));
        return propList;
    }

    private static String[] getIgnorablePropertiesBasedOnOp(Operation operation) {
        switch (operation){
            case SAVE: return new String[]{};
            case UPDATE: return new String[]{"id"};
            case DELETE: return new String[]{"id"};
            default: return new String[]{};
        }
    }
}
