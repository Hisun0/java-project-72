package hexlet.code.model;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
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
