package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrlCheck {
    private long id;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private long urlId;
    private Timestamp createdAd;

    public UrlCheck(
            int pStatusCode, String pTitle, String pH1, String pDescription, long pUrlId, Timestamp pCreatedAt
    ) {
        statusCode = pStatusCode;
        title = pTitle;
        h1 = pH1;
        description = pDescription;
        urlId = pUrlId;
        createdAd = pCreatedAt;
    }
}