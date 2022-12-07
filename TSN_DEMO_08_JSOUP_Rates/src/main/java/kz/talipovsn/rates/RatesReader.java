package kz.talipovsn.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// СОЗДАТЕЛЬ КОТИРОВОК ВАЛЮТ
public class RatesReader {

    private static final String BASE_URL = "https://github.com/proffix4?tab=following"; // Адрес с котировками

    // Парсинг котировок из формата html web-страницы банка, при ошибке доступа возвращаем null
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {

            Document doc = Jsoup.connect(BASE_URL).timeout(5000).get();
            data.append("New followers proffix4: \n\n");

            Elements v = doc.select("span.f4");
            Elements d = doc.select("p.color-fg-muted");

            Elements box = doc.select("div.container-xl");
            for (int i = 0; i < box.size(); i++) {
                Element v2 = v.get(i);
                Element d2 = d.get(i);


                data.append("Name: " + v2.text() + "\n");
                data.append("Location: " + d2.text() + "\n");

                data.append(" \n");
              }
        } catch (Exception e) {
      //      System.out.println(e.toString());
            return null; // При ошибке доступа возвращаем null
        }
        return data.toString().trim(); // Возвращаем результат
    }

}