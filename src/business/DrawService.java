package business;

import bean.EntryBean;
import bean.PlayBean;
import type.QaType;

import java.io.*;
import java.util.*;

/**
 * Created by tsuiki on 2015/11/22.
 */
public class DrawService {

    public List<EntryBean> read() {

        List<EntryBean> result = new ArrayList<EntryBean>();

        try {
            FileReader fileReader = new FileReader("static\\csv\\entry.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] cols = line.split(",");

                EntryBean entryBean = new EntryBean();
                entryBean.setId(cols[0]);
                entryBean.setName(cols[1]);
                entryBean.setByeFlag(cols[2] != null && cols[2].length() > 0);
                if (cols[3] != null && cols[3].length() > 0) {
                    entryBean.setQaType(QaType.valueOf(cols[3]));
                }
                result.add(entryBean);
            }

            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public void drawPrint(List<EntryBean> list) {

        if (list.size() == 0) {
            return;
        }

        // 抽選箱の生成
        List<Integer> drawBox = new ArrayList<Integer>();
        for (int i=1; i <= list.size(); i++) {
            drawBox.add(i);
        }
        Collections.shuffle(drawBox);

        TreeMap<Integer, EntryBean> drawResult = new TreeMap();
        // 不戦勝から先に抽選する
        draw(list, drawBox, drawResult, true);
        draw(list, drawBox, drawResult, false);

        PlayBean playBean = new PlayBean();
        System.out.println("--------- 抽選結果 ---------");
        for (Integer key : drawResult.keySet()) {

            if (playBean.getTop() == null || playBean.getTop().length() == 0) {
                playBean.setTop(drawResult.get(key).getName());
            } else if (playBean.getBottom() == null || playBean.getBottom().length() == 0) {
                playBean.setBottom(drawResult.get(key).getName());
            }

            if ((playBean.getTop() != null && playBean.getTop().length() > 0) &&
                    (playBean.getBottom() != null && playBean.getBottom().length() > 0)) {

                System.out.println("■■■");
                System.out.println("■質問者：" + playBean.getTop());
                System.out.println("■回答者：" + playBean.getBottom());
                playBean =  new PlayBean();
            }

        }
        System.out.println("--------- end ---------");
    }

    private void draw(List<EntryBean> list, List<Integer> drawBox, TreeMap<Integer, EntryBean> drawResult, boolean byeFlag) {
        for (EntryBean entryBean : list) {
            for (Integer num : drawBox) {
                if (byeFlag && entryBean.isByeFlag()) {
                    if (entryBean.getQaType() == QaType.QUESTION) {
                        if (num % 2 == 0) {
                            drawResult.put(num, entryBean);
                            drawBox.remove(num);
                            break;
                        }
                    } else {
                        if (num % 2 > 0) {
                            drawResult.put(num, entryBean);
                            drawBox.remove(num);
                            break;
                        }
                    }
                } else if (byeFlag && !entryBean.isByeFlag()) {
                    drawResult.put(num, entryBean);
                    drawBox.remove(num);
                    break;
                }
            }
        }
    }
}
