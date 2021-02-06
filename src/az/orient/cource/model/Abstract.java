package az.orient.cource.model;

import javax.sql.DataSource;
import java.util.Date;

public abstract class Abstract {
    private long id;
    private Integer active;
    private Date data_date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getData_date() {
        return data_date;
    }

    public void setData_date(Date data_date) {
        this.data_date = data_date;
    }

    @Override
    public String toString() {
        return "Abstract{" +
                "id=" + id +
                ", active=" + active +
                ", data_date=" + data_date +
                '}';
    }
}
