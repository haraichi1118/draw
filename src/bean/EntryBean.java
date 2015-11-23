package bean;

import type.QaType;

/**
 * Created by tsuiki on 2015/11/22.
 */
public class EntryBean {

    /** ID */
    private String id;
    /** 名前 */
    private String name;
    /** 不戦勝フラグ */
    private boolean byeFlag;
    /** 質問回答タイプ */
    private QaType qaType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isByeFlag() {
        return byeFlag;
    }

    public void setByeFlag(boolean byeFlag) {
        this.byeFlag = byeFlag;
    }

    public QaType getQaType() {
        return qaType;
    }

    public void setQaType(QaType qaType) {
        this.qaType = qaType;
    }
}
