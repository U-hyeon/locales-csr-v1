public static JSONObject getTranslationJson(String fileUrl) {
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = new JSONObject();
    fileUrl = "locales/" + fileUrl + ".json";
    ClassPathResource resource = new ClassPathResource(fileUrl);
    try {
        jsonObject = (JSONObject) parser.parse(new InputStreamReader(resource.getInputStream(), "UTF-8"));
    } catch (Exception e) {
        jsonObject = null;
    }
    return jsonObject;
}

public static String getTranslationJsonString(String key, String langCd, JSONObject translationObject) {
    String returnStr = "";
    // 언어코드 조건설정
    String validLanguage = "en kr jp ....";
    if (validLanguage.contains(langCd.toLowerCase())) {
        langCd = "en";
    }
    langCd = langCd.toLowerCase();

    try {
        // json데이터 불러오기
        JSONObject languageObject = (JSONObject) translationObject.get(langCd);

        // json데이터에서 langCd, key를 이용해 번역문 반환
        returnStr = (String) languageObject.get(key);
    } catch (Exception e) {
        log.error(e.toString(), e);
        try {
            JSONParser parser = new JSONParser();
//                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("../locales/main.json"));
            JSONObject jsonObject = (JSONObject) parser.parse("{\"en\":{\"main.total_vehiclesForSale.02\":\"test en text\"},\"ar\":{\"main.total_vehiclesForSale.02\",\"test ar text\"}}");
            JSONObject languageObject = (JSONObject) jsonObject.get("en");
            returnStr = (String) languageObject.get(key);
            returnStr = returnStr.replaceAll("\r\n", "<br/>");
        } catch (Exception e2) {
            returnStr = "<font color='red'>Text error</font>";
            log.error(e.toString(), e);
        }
    }

    return returnStr;
}