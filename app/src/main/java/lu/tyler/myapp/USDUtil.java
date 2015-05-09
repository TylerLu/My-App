package lu.tyler.myapp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class USDUtil {

    static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"); //2015.05.09 03:01:40

    public static ExchangeRate[] GetExchangeRates(){
        String url = "http://srh.bankofchina.com/search/whpj/search.jsp?pjname=1316";
        Document doc;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            return new ExchangeRate[0];
        }

        Elements rows = doc.select(".BOC_main table tr");
        rows.remove(0);

        ArrayList<ExchangeRate> list = new ArrayList<>();
        for (Element row : rows) {

            if(row.children().size() == 1)
                continue;

            String rateString = row.child(1).text();
            String dateString = row.child(7).text();

            ExchangeRate rate = new ExchangeRate();
            rate.setValue(Float.valueOf(rateString));
            try {
                rate.setDate(dateFormat.parse(dateString));
            } catch (ParseException e) {
                e.printStackTrace();
                continue;
            }
            list.add(rate);
        }
        return list.toArray(new ExchangeRate[list.size()]);
    }
}
