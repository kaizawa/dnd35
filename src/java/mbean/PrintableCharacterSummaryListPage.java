/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.CharacterRecordFacade;
import entity.AbilityMaster;
import entity.CharacterRecord;
import entity.SaveMaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.convert.NumberConverter;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version PrintableCharacterRecordPage.java
 * @version Created on 2009/02/20, 23:11:21
 * @author ka78231
 */
@ManagedBean
@RequestScoped
public class PrintableCharacterSummaryListPage extends BaseBean {
    private HtmlDataTable abilityTable = new HtmlDataTable();

    public HtmlDataTable getAbilityTable() {
        return abilityTable;
    }

    public void setAbilityTable(HtmlDataTable hdt) {
        this.abilityTable = hdt;
    }

    private HtmlDataTable saveTable = new HtmlDataTable();

    public HtmlDataTable getSaveTable() {
        return saveTable;
    }

    public void setSaveTable(HtmlDataTable hdt) {
        this.saveTable = hdt;
    }
    private HtmlDataTable SaveTable = new HtmlDataTable();

    private NumberConverter numberConverter1 = new NumberConverter();

    public NumberConverter getNumberConverter1() {
        return numberConverter1;
    }

    public void setNumberConverter1(NumberConverter nc) {
        this.numberConverter1 = nc;
    }
    @EJB
    private CharacterRecordFacade characterRecordFacade;
    private HtmlDataTable dataTable1 = new HtmlDataTable();

    public HtmlDataTable getDataTable1() {
        return dataTable1;
    }

    public void setDataTable1(HtmlDataTable hdt) {
        this.dataTable1 = hdt;
    }


    @PostConstruct
    public void init() {

        // キャラクタリストの作成
        Set<CharacterRecord> charaSet = (Set<CharacterRecord>) getSessionBean().getSelectedCharas();
        List<CharacterRecord> charaList = new ArrayList<CharacterRecord>();
        for (CharacterRecord chara : charaSet){
            charaList.add(chara);
        }
        getSessionBean().setCharacterRecordList(charaList);
    }


    protected void _init() throws Exception {
        numberConverter1.setMinIntegerDigits(1);
        numberConverter1.setMaxIntegerDigits(40);
        numberConverter1.setMaxFractionDigits(3);
    }



    /* User one on Session Beans
    private List<CharacterRecord> characterRecordList;

    public List<CharacterRecord> getCharacterRecordList() {
        return this.characterRecordList;
    }

    public void setCharacterRecordList(List<CharacterRecord> charaList) {
        this.characterRecordList = charaList;
    }
     */

    public String charalist_action() {
        return "charalist";
    }

    public String editCharaLink_action() {
        CharacterRecord characterRecord = (CharacterRecord) dataTable1.getRowData();

        //管理Beanへ反映
        getSessionBean().setCharacterRecord(characterRecord);
        return "editchara";
    }

    public Integer getSaveTotal() {
        CharacterRecord chara =(CharacterRecord) dataTable1.getRowData();
        SaveMaster save= (SaveMaster) saveTable.getRowData();
        return new DnDUtil(chara).getSaveTotalById(save.getId());
    }


    public Integer getAbilityModifier() {
        CharacterRecord chara = (CharacterRecord) dataTable1.getRowData();
        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();
        return new DnDUtil(chara).getAbilityModifierById(ability.getId());
    }

    public Integer getAbilityTotal() {
        CharacterRecord chara = (CharacterRecord) dataTable1.getRowData();
        AbilityMaster ability = (AbilityMaster) abilityTable.getRowData();
        return new DnDUtil(chara).getAbilityTotalById(ability.getId());
    }

    public String getCommonSkills() {
        CharacterRecord chara = (CharacterRecord) dataTable1.getRowData();
        DnDUtil util = new DnDUtil(chara);

        return "隠れ身: " + util.getSkillTotalCheckModifierById(4) + "<br>" +
                "聞き耳: " + util.getSkillTotalCheckModifierById(7) + "<br>" +
                "交渉: " + util.getSkillTotalCheckModifierById(13) + "<br>" +
                "視認: " + util.getSkillTotalCheckModifierById(14) + "<br>" +
                "忍び足: " + util.getSkillTotalCheckModifierById(15) + "<br>" +
                "真意看破: " + util.getSkillTotalCheckModifierById(21) + "<br>" +
                "捜索: " + util.getSkillTotalCheckModifierById(28);
    }

}
