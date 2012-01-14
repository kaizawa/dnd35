/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.CharacterGrowthRecord;
import entity.CharacterRecord;
import entity.ClassMaster;
import entity.SkillMaster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ValueChangeEvent;

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
public class PrintableCharacterRecordPage extends EditCharacterRecordPageContents {

    @PostConstruct
    @Override
    public void init() {
        super.init();

        int lv =  getSessionBean().getCharacterLevel();
        //クラスのリストをつくり、各クラスのレベルを計算する
        List<CharacterGrowthRecord> growthList = characterGrowthRecordFacade.findByCharacter(getCharacterRecord());
        Map<ClassMaster, Integer> classMap = new HashMap<ClassMaster, Integer>();
        int i = 1;
        for (CharacterGrowthRecord growth : growthList) {
            // 現在のキャラクタレベル分だけしか見ないでよい。
            if (i > lv) {
                break;
            }
            int newVal = 0;
            ClassMaster klass = growth.getClassId();

            if (classMap.get(klass) == null) {
                newVal = 1;
            } else {
                newVal = classMap.get(klass).intValue() + 1;
            }
            classMap.put(klass, newVal);
            i++;
        }
        String classListTemp = "";
        for (Map.Entry<ClassMaster, Integer> mapEntry : classMap.entrySet()) {
            ClassMaster klass = mapEntry.getKey();
            String line =
                    (klass == null ? "未設定" : klass.getClassName()) +
                    mapEntry.getValue() + " Lv" + ", ";
            classListTemp = classListTemp + line;
        }
        setClassList(classListTemp);
    }


    /*
     * ----- キャラクタレベル毎のクラスの選択 ----------------------------------------------
     */
    public String getSelectedClassNameByRow() {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterRecord().getCharacterGrowthRecordList().get(index);
        if (growth.getClassId() == null) {
            return null;
        } else {
            return growth.getClassId().getClassName();
        }

    }
    /*
     * ----- キャラクタレベル 4 レベル毎の上昇能力値の設定------
     */
    String selectedAbilityEnhancementNameByRow;

    public String getSelectedAbilityEnhancementNameByRow() {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterRecord().getCharacterGrowthRecordList().get(index);
        if (growth.getAbilityEnhancement() == null) {
            return null;
        } else {
            return (abilityMasterFacade.find(growth.getAbilityEnhancement())).getAbilityName();
        }
    }

    /*
     * ---------------- キャラクタ記述 ----------------------------------
     */
    public String getCharacterDescription() {
        return DnDUtil.newLineToBr(getCharacterRecord().getDescription());
    }
    /*
     * ---------------- スペル記述 ----------------------------------
     */

    public String getSpellDescription() {
        return DnDUtil.newLineToBr(getCharacterRecord().getSpellDescription());
    }
    /*
     * ---------------- 防御記述 ----------------------------------
     */

    public String getDefenceDescription() {
        return DnDUtil.newLineToBr(getCharacterRecord().getDefenceDescription());
    }
    /*
     * ---------------- 攻撃記述 ----------------------------------
     */

    public String getAttackDescription() {
        return DnDUtil.newLineToBr(getCharacterRecord().getAttackDescription());
    }
    /*
     * ---------------- アイテム記述 ----------------------------------
     */

    public String getItemDescription() {
        return DnDUtil.newLineToBr(getCharacterRecord().getItemDescription());
    }
    /*
     * ---------------- 特技記述 ----------------------------------
     */

    public String getFeatDescription() {
        return DnDUtil.newLineToBr(getCharacterRecord().getFeatDescription());
    }
    /*
     * ---------------- クラスリスト -------------------------------
     */
    protected String classList;

    public void setClassList(String classList){
        this.classList = classList;
    }

    public String getClassList() {
        return classList;
    }


// 以下 親からの継承
    @Override
    public String charaListButton_action() {
        return super.charaListButton_action();
    }

    @Override
    public String deleteButton_action() {
        return super.deleteButton_action();
    }

    @Override
    public String editSkillButton_action() {
        return super.editSkillButton_action();
    }

    @Override
    public String editSkillNomalButton_action() {
        return super.editSkillNomalButton_action();
    }

    @Override
    public Integer getAbilityBase() {
        return super.getAbilityBase();
    }

    @Override
    public Integer getAbilityBaseById(int id) {
        return super.getAbilityBaseById(id);
    }

    @Override
    public Integer getAbilityFeatModifier() {
        return super.getAbilityFeatModifier();
    }

    @Override
    public Integer getAbilityFeatModifierById(int id) {
        return super.getAbilityFeatModifierById(id);
    }

    @Override
    public Integer getAbilityLevelModifier() {
        return super.getAbilityLevelModifier();
    }

    @Override
    public Integer getAbilityLevelModifierById(int id) {
        return super.getAbilityLevelModifierById(id);
    }

    @Override
    public Integer getAbilityMiscModifier() {
        return super.getAbilityMiscModifier();
    }

    @Override
    public Integer getAbilityMiscModifierById(int id) {
        return super.getAbilityMiscModifierById(id);
    }

    @Override
    public Integer getAbilityModifier() {
        return super.getAbilityModifier();
    }

    @Override
    public Integer getAbilityModifierById(int ability) {
        return super.getAbilityModifierById(ability);
    }

    @Override
    public Integer getAbilityRaceModifier() {
        return super.getAbilityRaceModifier();
    }

    @Override
    public Integer getAbilityRaceModifierById(int id) {
        return super.getAbilityRaceModifierById(id);
    }

    @Override
    public HtmlDataTable getAbilityTable() {
        return super.getAbilityTable();
    }

    @Override
    public Integer getAbilityTotal() {
        return super.getAbilityTotal();
    }

    @Override
    public Integer getAbilityTotalById(int id) {
        return super.getAbilityTotalById(id);
    }

    @Override
    public Integer getAcAbilityModifier() {
        return super.getAcAbilityModifier();
    }

    @Override
    public Integer getAcFlatFooted() {
        return super.getAcFlatFooted();
    }

    @Override
    public Integer getAcNormal() {
        return super.getAcNormal();
    }

    @Override
    public Integer getAcTouch() {
        return super.getAcTouch();
    }

    @Override
    public Integer getAttackBonusDexBonus() {
        return super.getAttackBonusDexBonus();
    }

    @Override
    public Integer getAttackBonusStrengthBonus() {
        return super.getAttackBonusStrengthBonus();
    }

    @Override
    public Integer getBaseAttackByClassAndLevel(ClassMaster klass, Integer lv) {
        return super.getBaseAttackByClassAndLevel(klass, lv);
    }

    @Override
    public Integer getBaseAttackTotal() {
        return super.getBaseAttackTotal();
    }

    @Override
    public Integer getHitPointAbilityModifier() {
        return super.getHitPointAbilityModifier();
    }

    @Override
    public Integer getInitiativeAbilityModifier() {
        return super.getInitiativeAbilityModifier();
    }

    @Override
    public Integer getInitiativeFeatModifier() {
        return super.getInitiativeFeatModifier();
    }

    @Override
    public Integer getInitiativeMiscModifier() {
        return super.getInitiativeMiscModifier();
    }

    @Override
    public Integer getInitiativeTotal() {
        return super.getInitiativeTotal();
    }

    @Override
    public String getLastChange() {
        return super.getLastChange();
    }

    @Override
    public Integer getMeleeAttackBonus() {
        return super.getMeleeAttackBonus();
    }

    @Override
    public Integer getRangeAttackBonus() {
        return super.getRangeAttackBonus();
    }

    @Override
    public Integer getSaveAbilityModifier() {
        return super.getSaveAbilityModifier();
    }

    @Override
    public Integer getSaveAbilityModifierById(int saveId) {
        return super.getSaveAbilityModifierById(saveId);
    }

    @Override
    public Integer getSaveClassBonus() {
        return super.getSaveClassBonus();
    }

    @Override
    public Integer getSaveClassBonusById(int saveId) {
        return super.getSaveClassBonusById(saveId);
    }

    @Override
    public Integer getSaveMiscModifier() {
        return super.getSaveMiscModifier();
    }

    @Override
    public Integer getSaveMiscModifierById(int saveId) {
        return super.getSaveMiscModifierById(saveId);
    }

    @Override
    public HtmlDataTable getSaveTable() {
        return super.getSaveTable();
    }

    @Override
    public Integer getSaveTotal() {
        return super.getSaveTotal();
    }

    @Override
    public Integer getSaveTotalById(int saveId) {
        return super.getSaveTotalById(saveId);
    }

    @Override
    public Integer getSelectedAbilityEnhancementByRow() {
        return super.getSelectedAbilityEnhancementByRow();
    }

    @Override
    public Integer getSelectedClassByRow() {
        return super.getSelectedClassByRow();
    }

    @Override
    public Integer getSkillAbilityModifier() {
        return super.getSkillAbilityModifier();
    }

    @Override
    public Integer getSkillAbilityModifierById(int skill) {
        return super.getSkillAbilityModifierById(skill);
    }

    @Override
    public String getSkillCheckAcceptNoRank() {
        return super.getSkillCheckAcceptNoRank();
    }

    @Override
    public String getSkillRankCheck() {
        return super.getSkillRankCheck();
    }

    @Override
    public HtmlDataTable getSkillTable() {
        return super.getSkillTable();
    }

    @Override
    public Integer getSkillTotalCheckModifier() {
        return super.getSkillTotalCheckModifier();
    }

    @Override
    public Integer getSkillTotalPoint() {
        return super.getSkillTotalPoint();
    }

    @Override
    public Integer getSkillTotalRank() {
        return super.getSkillTotalRank();
    }

    @Override
    public Integer getSkillTotalRankById(int skillId) {
        return super.getSkillTotalRankById(skillId);
    }

    @Override
    public Integer getSpeed() {
        return super.getSpeed();
    }

    @Override
    public Integer getSpeedFeatModifier() {
        return super.getSpeedFeatModifier();
    }

    @Override
    public Integer getSpeedMiscModifier() {
        return super.getSpeedMiscModifier();
    }

    @Override
    public Integer getSpeedRaceBasse() {
        return super.getSpeedRaceBasse();
    }

    @Override
    public Integer getGrappleBonus() {
        return super.getGrappleBonus();
    }

    @Override
    public String getSkillAbilityName() {
        return super.getSkillAbilityName();
    }

    @Override
    public String getSkillAbilityNameById(int skill) {
        return super.getSkillAbilityNameById(skill);
    }

    @Override
    public String getSkillAbilityShortName() {
        return super.getSkillAbilityShortName();
    }

    @Override
    public String getSkillAbilityShortNameById(int skill) {
        return super.getSkillAbilityShortNameById(skill);
    }

    @Override
    public Integer getSkillArmorModifier() {
        return super.getSkillArmorModifier();
    }

    @Override
    public Integer getSkillArmorModifierById(int skillId) {
        return super.getSkillArmorModifierById(skillId);
    }

    @Override
    public Integer getSkillMiscModifier() {
        return super.getSkillMiscModifier();
    }

    @Override
    public Float getSkillRankByLevelAndSkill(CharacterGrowthRecord growth, SkillMaster skill) {
        return super.getSkillRankByLevelAndSkill(growth, skill);
    }

    @Override
    public Integer getSkillSynergyModifier() {
        return super.getSkillSynergyModifier();
    }

    @Override
    public Integer getSkillSynergyModifierById(int skillId) {
        return super.getSkillSynergyModifierById(skillId);
    }

    @Override
    public boolean hasSkillRankBySkill(SkillMaster skill) {
        return super.hasSkillRankBySkill(skill);
    }

    @Override
    public boolean isAbilityEnhancementDisabled() {
        return super.isAbilityEnhancementDisabled();
    }

    @Override
    public boolean isClassSkillByClassAndSkill(ClassMaster klass, SkillMaster skill) {
        return super.isClassSkillByClassAndSkill(klass, skill);
    }

    @Override
    public boolean isSkillAcceptoRankBySkillId(int skillid) {
        return super.isSkillAcceptoRankBySkillId(skillid);
    }

    @Override
    public String saveButton_action() {
        return super.saveButton_action();
    }

    @Override
    public void setAbilityBase(Integer newVal) {
        super.setAbilityBase(newVal);
    }

    @Override
    public void setAbilityBaseById(int id, Integer newVal) {
        super.setAbilityBaseById(id, newVal);
    }

    @Override
    public void setAbilityFeatModifier(Integer newVal) {
        super.setAbilityFeatModifier(newVal);
    }

    @Override
    public void setAbilityFeatModifierById(int id, Integer newVal) {
        super.setAbilityFeatModifierById(id, newVal);
    }

    @Override
    public void setAbilityMiscModifier(Integer newVal) {
        super.setAbilityMiscModifier(newVal);
    }

    @Override
    public void setAbilityMiscModifierById(int id, Integer newVal) {
        super.setAbilityMiscModifierById(id, newVal);
    }

    @Override
    public void setAbilityTable(HtmlDataTable hdt) {
        super.setAbilityTable(hdt);
    }

    @Override
    public void setInitiativeFeatModifier(Integer modifier) {
        super.setInitiativeFeatModifier(modifier);
    }

    @Override
    public void setInitiativeMiscModifier(Integer modifier) {
        super.setInitiativeMiscModifier(modifier);
    }

    @Override
    public void setSaveMiscModifier(Integer miscModifier) {
        super.setSaveMiscModifier(miscModifier);
    }

    @Override
    public void setSaveTable(HtmlDataTable hdt) {
        super.setSaveTable(hdt);
    }

    @Override
    public void setSelectedAbilityEnhancementByRow(Integer abilityId) {
        super.setSelectedAbilityEnhancementByRow(abilityId);
    }

    @Override
    public void setSelectedClassByRow(Integer classId) {
        super.setSelectedClassByRow(classId);
    }

    @Override
    public void setSkillTable(HtmlDataTable hdt) {
        super.setSkillTable(hdt);
    }

    @Override
    public void setSpeed(Integer speed) {
        super.setSpeed(speed);
    }

    @Override
    public void setSpeedFeatModifier(Integer speedFeatModifier) {
        super.setSpeedFeatModifier(speedFeatModifier);
    }

    @Override
    public void setSpeedMiscModifier(Integer speedMiscModifier) {
        super.setSpeedMiscModifier(speedMiscModifier);
    }

    @Override
    public void setSkillMiscModifier(Integer skillMiscModifier) {
        super.setSkillMiscModifier(skillMiscModifier);
    }

    @Override
    public void textField13_processValueChange(ValueChangeEvent vce) {
        super.textField13_processValueChange(vce);
    }

    @Override
    public void textField1_processValueChange(ValueChangeEvent vce) {
        super.textField1_processValueChange(vce);
    }

    public String editCharaLink_action() {
        return "EditCharacterRecordPageContents";
    }

    @Override
    public Integer getAcArmor() {
        return super.getAcArmor();
    }

    @Override
    public Integer getAcRaceModifier() {
        return super.getAcRaceModifier();
    }

    @Override
    public Integer getAcShield() {
        return super.getAcShield();
    }

    @Override
    public Integer getAcMiscMod() {
        return super.getAcMiscMod();
    }

    @Override
    public CharacterRecord getCharacterRecord() {
        return super.getCharacterRecord();
    }

    public String charalist_action() {
        return "CharacterListPageContents";
    }


}

// 以上 親からの継承