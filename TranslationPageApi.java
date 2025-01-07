import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslationPageApi {

    @GetMapping("/get-translation")
    public JSONObject getTranslation(
            @RequestParam String langCd, @RequestParam String pageNm
    ) {
        JSONObject temp = (JSONObject) Functions.getTranslationJson(pageNm).get("en");
        for(Object tKey : temp.keySet()) {
            if (((JSONObject) Functions.getTranslationJson(pageNm).get(langCd.toLowerCase())).get(tKey) != null) {
                temp.put(tKey, ((JSONObject) Functions.getTranslationJson(pageNm).get(langCd.toLowerCase())).get(tKey));
            }
        }
        return temp;
    }
}
