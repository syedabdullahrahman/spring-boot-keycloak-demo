package com.abdullah.keycloak.model;

import com.abdullah.keycloak.enums.Operation;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * User: Syed Abdullah
 * Date: 19-Jan-2022
 * Time: 6:56 PM
 */
@Entity
@Table
@Audited
public class Book extends AbstractModel<Long> {

    @Column
    private String title;
    @Column
    private String author;

    @Version
    @Column(name = "version")
    private int version;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public static List<String> getIgnorablePropertiesAtBeanCopy(Operation operation){
        String properties[] = getIgnorablePropertiesBasedOnOp(operation);
        List<String > propList = new ArrayList<>(Arrays.asList(properties));
        propList.addAll(AbstractModel.getIgnorablePropertiesAtBeanCopy(operation));
        return Collections.unmodifiableList(propList);
    }

    private static String[] getIgnorablePropertiesBasedOnOp(Operation operation) {
        switch (operation){
            case SAVE: return new String[]{};
            case UPDATE: return new String[]{};
            case DELETE: return new String[]{};
            default: return new String[]{};
        }
    }
}
