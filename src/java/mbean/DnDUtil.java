/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.CharacterRecord;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ka78231
 */
@ManagedBean
@RequestScoped
public class DnDUtil {

    /*
    public static final Integer STR = new Integer(1);
    public static final Integer DEX = new Integer(2);
    public static final Integer CON = new Integer(3);
    public static final Integer INT = new Integer(4);
    public static final Integer WIS = new Integer(5);
    public static final Integer CHA = new Integer(6);
    public static final Integer FORTITUTE = new Integer(1);
    public static final Integer REFLEX = new Integer(2);
    public static final Integer WILL = new Integer(3);
     */
    public static final int STR = 1;
    public static final int DEX = 2;
    public static final int CON = 3;
    public static final int INT = 4;
    public static final int WIS = 5;
    public static final int CHA = 6;
    public static final int FORTITUTE = 1;
    public static final int REFLEX = 2;
    public static final int WILL = 3;

    public static Integer getLevel(Integer exp) {
        int lv = 0;
        int val = 0;
        do {
            lv++;
            val += lv * 1000;
        } while (exp.intValue() >= val);
        return (new Integer(lv));
    }

    public static Integer getExpForNextLevel(Integer exp) {
        int lv = 0;
        int val = 0;
        do {
            lv++;
            val += lv * 1000;
        } while (exp.intValue() >= val);
        return (new Integer(val));
    }

    public static String getLastChange(CharacterRecord charaRecord) {
        Date date = charaRecord.getSaveTime();

        if (date == null) {
            return "--未保存--";
        }
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
        return fmt.format(date);
    }

    /**
     * 改行を<br>に変換
     */
    public static String newLineToBr(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        StringCharacterIterator sci = new StringCharacterIterator(str);
        for (char c = sci.current(); c != StringCharacterIterator.DONE; c = sci.next()) {
            if (c == '\n') {
                sb.append("<br>");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
