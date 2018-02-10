package pl.com.bottega.cms.domain;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class Ticket {

    private String kind;

    private Integer count;


    public Ticket() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}