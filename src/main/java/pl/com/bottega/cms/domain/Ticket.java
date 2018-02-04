package pl.com.bottega.cms.domain;

import javax.persistence.*;

@Embeddable
public class Ticket {


    private String kind;

    private int count;


    public Ticket() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
