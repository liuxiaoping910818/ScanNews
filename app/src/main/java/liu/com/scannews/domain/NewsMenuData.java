package liu.com.scannews.domain;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/21.
 */
public class NewsMenuData {

    public int retcode;
    public ArrayList<String> extend;
    private ArrayList<NewsData> data;

    public class NewsData{

        public String id;
        public String title;
        public int type;
        public ArrayList<NewsMenuData> children;

        @Override
        public String toString() {
            return "NewsData{" +
                    "title='" + title + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    public class NewsTabData{

        public String id;
        public String title;

       public String url;
        public int type;

        @Override
        public String toString() {
            return "NewsTabData{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsMenuData [data=" + data + "]";
    }
}
