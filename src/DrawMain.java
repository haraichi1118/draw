import bean.EntryBean;
import business.DrawService;

import java.util.List;

/**
 * Created by tsuiki on 2015/11/22.
 */
public class DrawMain {

    public static void main(String[] arg) {
        DrawService drawService = new DrawService();
        List<EntryBean> list = drawService.read();
        drawService.drawPrint(list);
    }

}
