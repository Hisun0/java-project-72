package hexlet.code.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Url {
    private long id;
    private String name;
    private Timestamp createdAt;

    public Url(String pName, Timestamp pCreatedAt) {
        name = pName;
        createdAt = pCreatedAt;
    }
}
