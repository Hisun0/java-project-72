package hexlet.code.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class RootPage {
    private Map<String, String> flash;
}
