/*
 * EditCharacterPerLevelPage.java
 *
 * Created on 2009/01/10, 13:26:57
 */
package mbean;


import entity.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @author ka78231
 */
@ManagedBean
@RequestScoped
public class EditCharacterPerLevelPage extends EditCharacterRecordPage {

    @Override
    public HtmlDataTable getSkillTable() {
        return skillTable;
    }

    @Override
    public void setSkillTable(HtmlDataTable hdt) {
        this.skillTable = hdt;
    }

    private HtmlSelectOneMenu levelDropdown = new HtmlSelectOneMenu();

    public HtmlSelectOneMenu getLevelDropdown() {
        return levelDropdown;
    }

    public void setLevelDropdown(HtmlSelectOneMenu hsom) {
        this.levelDropdown = hsom;
    }

    // </editor-fold>
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public EditCharacterPerLevelPage() {
    }

    @PostConstruct
    @Override
    public void init() {
        
        setCharacterRecord( getSessionBean().getCharacterRecord());
        

        //----選択可能なキャラクターレベルのリスト---------------------------
        List<SelectItem> levelList = new ArrayList<SelectItem>();
        int currentLv =  getSessionBean().getCharacterLevel().intValue();

        for (int lv = 1; lv < currentLv + 1; lv++) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(lv);
            selectItem.setLabel("レベル " + lv);
            levelList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] levelArray = levelList.toArray(new SelectItem[0]);
        //セッションBEANへのセット
         getSessionBean().setLevelArray(levelArray);

        // ------ 現在のキャラクターレベルでのクラスの調査  ------------------------
        int charaLv =  getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel();
        CharacterGrowthRecord growth =  getSessionBean().getCharacterGrowthRecord();
        ClassMaster klass = growth.getClassId();
        setClassMaster(klass);

        // ------ クラスレベルの調査 -----------------------------------------------
        int classLv = 0;
        List<CharacterGrowthRecord> growthList;
        if (klass != null) {
            growthList = getCharacterRecord().getCharacterGrowthRecordList();
            for (int i = 0; i < charaLv; i++) {
                CharacterGrowthRecord gr = growthList.get(i);
                if (gr.getClassId() != null) {
                    if (klass.getId() == gr.getClassId().getId()) {
                        classLv++;
                    }
                }
            }
        }
        setClassLevel(classLv);

        // ------ 合計 HP を再計算 --------------------
        growthList =  getSessionBean().getCharacterRecord().getCharacterGrowthRecordList();
        int totalHP = 0;
         getSessionBean().getCharacterRecord();
        int bonus = getAbilityModifierById(DnDUtil.CON);

        for (CharacterGrowthRecord tempGrowth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if(growth.getCharacterGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) break;
            if (tempGrowth.getHitPoint() != null) {
                totalHP += tempGrowth.getHitPoint() + bonus;
            }
        }
         getSessionBean().setHitPointTotal(totalHP);
    }

    public Integer getskillPoint(){
        int index = skillTable.getRowIndex();
        int skill = index + 1;
        return getskillPointById(skill);
    }
    
    public String getSkillTotalPointColor(){
        if(getSkillTotalPoint() == 0){
            return "text-align: right; ";
        } else {
            return "text-align: right; font-weight: bold;  font-size: 15px; ";
        }
    }

    //特定レベルのスキルポイント
    public Integer getskillPointById(int skill) {
        int point = 0;
        int lv =  getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel();

        List<CharacterSkillGrowthRecord> growthlist = getCharacterRecord().getCharacterSkillGrowthRecordList();
        for (CharacterSkillGrowthRecord skillGrowth : growthlist) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if(skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) break;
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill &&
                    skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() == lv) {
                point = skillGrowth.getSkillPoint().intValue();
                break;
            }
        }
        return point;
    }

    public void setskillPoint(Integer point) {            
        int index = skillTable.getRowIndex();        
        int skill = index + 1;        
        setskillPointById(point, skill); 
    }

    public void setskillPointById(Integer point, int skill) {
        List<CharacterSkillGrowthRecord> skillGrowthlist = getCharacterRecord().getCharacterSkillGrowthRecordList();

        if (point == null) {
            point = new Integer(0);
        }
        for (CharacterSkillGrowthRecord skillGrowth : skillGrowthlist) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if(skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) break;
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill &&
                    skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() ==  getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel()) {
                skillGrowth.setSkillPoint(point);
                break;
            }
        }
    }

    // 特定のレベルのランク値 (計算値)
    public Integer getskillRank() {
        int skillId = skillTable.getRowIndex() + 1;
        SkillMaster skill = skillMasterFacade.find(skillId);

        return getSkillRankByLevelAndSkill( getSessionBean().getCharacterGrowthRecord(), skill).intValue();
    }

    @Override
    public String saveButton_action() {
        doSave();
        return null;
    }

    public void doSave() {
        // 全レベルのスキルレコードと、成長レコードも保存する
        List<CharacterSkillGrowthRecord> skillGrowthList = getCharacterRecord().getCharacterSkillGrowthRecordList();
        List<CharacterGrowthRecord> growthList = getCharacterRecord().getCharacterGrowthRecordList();
        // 更新時刻記録用にキャラクターレコードも保存する。
        // ここで保存するのは、本当は好ましくは無いが。

        //更新時間を記録
        Date date = new Date();
        getCharacterRecord().setSaveTime(date);

        try {
            characterRecordFacade.edit(getCharacterRecord());

            for (CharacterSkillGrowthRecord skillGrowth : skillGrowthList) {
                characterSkillGrowthRecordFacade.edit(skillGrowth);
            }
            for (CharacterGrowthRecord growth : growthList) {
                characterGrowthRecordFacade.edit(growth);
            }
            context.addMessage("contents:contentGrid:label1", new FacesMessage(("保存されました")));

        } catch (Exception ex) {
            ex.printStackTrace();
            context.addMessage("contents:contentGrid:label1", new FacesMessage(("成長記録の保存に失敗しました")));
        }
    }

    public String returnCharacterRecordPageButton_action() {
        return "EditCharacterRecordPage";
    }

    public String previousLevelButton_action() {
        //まずはセーブ
        doSave();

        //その上で、表示するレベルを変更
        // Lv は 1 からだが、List の Index は 0 からなので1引く
        int index =  getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel() - 1;
         getSessionBean().setCharacterGrowthRecord(getCharacterRecord().getCharacterGrowthRecordList().get(index - 1));
        //仮想フォーム "save" の値を破棄することを指示。これをしないとドロップダウンのレベル値が保持されてしまう。
        //form1 は デザイン画面から form1 の「バインド属性を追加」でアクセスできるようになる。
        //form1.discardSubmittedValues("selectitem");
        return null;
    }

    public String nextLevelButton_action() {
        //まずはセーブ
        //doSave();
        
        //その上で、表示するレベルを変更
        // Lv は 1 からだが、List の Index は 0 からなので1引く
        int index =  getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel() - 1;
         getSessionBean().setCharacterGrowthRecord(getCharacterRecord().getCharacterGrowthRecordList().get(index + 1));
        //仮想フォーム "save" の値を破棄することを指示。これをしないとドロップダウンのレベル値が保持されてしまう。
        //form1 は デザイン画面から form1 の「バインド属性を追加」でアクセスできるようになる。
        //form1.discardSubmittedValues("selectitem");
        return null;
    }    
    
    // ボタンを非表示にするかどうかを決める
    protected boolean nextButtonDisabled;

    public boolean isNextButtonDisabled() {
        return ( getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel() >=  getSessionBean().getCharacterLevel());
    }
    // ボタンを非表示にするかどうかを決める
    protected boolean nextPreviousDisabled;

    public boolean isPreviousButtonDisabled() {
        return ( getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel() <= 1);
    }    // 現在表示しているレベルのクラス
    protected ClassMaster classMaster;

    public ClassMaster getClassMaster() {
        return classMaster;
    }

    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }
    //現在表示しているレベルのクラスレベル
    protected Integer classLevel;

    public Integer getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(Integer classLevel) {
        this.classLevel = classLevel;
    }

    public void levelDropdown_processValueChange(ValueChangeEvent vce) {
        //まずはセーブ
        doSave();

         getSessionBean().setCharacterGrowthRecord(
                getCharacterRecord().getCharacterGrowthRecordList().get((Integer) vce.getNewValue() - 1));
    //仮想フォーム "save" の値を破棄することを指示。これをしないとドロップダウンのレベル値が保持されてしまう。
    //form1 は デザイン画面から form1 の「バインド属性を追加」でアクセスできるようになる。
    //form1.discardSubmittedValues("save");
    }
    // プルダウンメニューで選択されているレベルのインデックス番号（つまり-1したもの）
    protected Integer selectedLevel; // not used

    public Integer getSelectedLevel() {
        return  getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel();
    }

    public void setSelectedLevel(Integer selectedLevel) {
        // not used... though might be called.
        this.selectedLevel = selectedLevel;
    }
    protected Integer selectedClass;

    public Integer getSelectedClass() {
        CharacterGrowthRecord growth =  getSessionBean().getCharacterGrowthRecord();
        if (growth.getClassId() == null) {
            return null;
        } else {
            return growth.getClassId().getId();
        }

    }

    public void setSelectedClass(Integer classId) {
        CharacterGrowthRecord growth =  getSessionBean().getCharacterGrowthRecord();
        ClassMaster klass;

        if (classId == null) {
            growth.setClassId(null);
            return;

        }
        klass = classMasterFacade.find(classId);
        growth.setClassId(klass);
    }
    protected String hitDiceType;

    public String getHitDiceType() {
        ClassMaster klass =  getSessionBean().getCharacterGrowthRecord().getClassId();
        if (klass == null) {
            return null;
        }
        DiceMaster dice = klass.getHitDiceType();
        if (dice == null) {
            return null;
        }
        return dice.getName();
    }
    protected String classSkill;

    public String getClassSkill() {
        int skillId = skillTable.getRowIndex() + 1;
        SkillMaster skill = skillMasterFacade.find(skillId);
        CharacterGrowthRecord growth =  getSessionBean().getCharacterGrowthRecord();

        if (isClassSkillByLevelAndSkill(growth, skill)) {
            return "■";
        } else {
            return "□";
        }
    }

    public Integer getTotalHpThisLevel() {
        int result = 0;
        Integer Hp =  getSessionBean().getCharacterGrowthRecord().getHitPoint();
        result += (Hp != null ? Hp.intValue() : 0);
        result += (getHitPointAbilityModifier() != null ? getHitPointAbilityModifier() : 0);
        return result;
    }

    
    @Override
    public Integer getHitPointAbilityModifier() {
        return super.getHitPointAbilityModifier();
    }

    @Override
    public CharacterRecord getCharacterRecord() {
        return this.characterRecord;
    }

    @Override
    public void setCharacterRecord(CharacterRecord characterRecord) {
        this.characterRecord = characterRecord;
    }


    /**
     * <p>すべてのレベルを通じて使用したスキルポイントの合計値</p>
     * @return
     */
    public Integer getSkillPointUsedTotal(){
        List<SkillMaster> skills = getApplicationBean().getSkillList();
        Integer points = 0;

        for(SkillMaster skill: skills){
            points += getSkillTotalPointById(skill.getId());
        }
        return points;
    }

    /** <p>選択したクラスで獲得できる技能ポイント（計算値）</p>
     * <p>各クラスの技能ポイント値+知能のボーナス値+（人間ポーナス）</p>
     * @return 技能ポイント
     */
    public Integer getSkillPointsAvailable() {
        Integer points = 0;

        // クラスの技能ポイント
        points += getSkillPointPerLevel();

        // 知力ボーナス
        points += getSkillPointAbilityModifier();

        //人間なら+1
        points += getSkillPointRaceBonus();

        // 最低でも1 は得られる
        if(points < 1)
            points = 1;

        return points;
    }

    /**
     * <p>このレベルで利用可能でまだ未使用の技能ポイント</p>
     * @return
     */
    public Integer getSkillPointUnused(){
        return getSkillPointsAvailable() - getSkillPointUsed();
    }
    
    public boolean isSkillPointRemaind(){
        if (getSkillPointUnused() == 0){
            return false; 
        } else {
            return true;
        }
    }

    /**
     * <p>このレベルで利用可能で使用済みの技能ポイント. つまりテキストフィールドに入力済みの数値の合計</p>
     * @return
     */
    public Integer getSkillPointUsed(){
        List<SkillMaster> skills = getApplicationBean().getSkillList();
        Integer points = 0;

        for(SkillMaster skill: skills){
            points += getskillPointById(skill.getId());
        }
        return points;
    }

    public Integer getSkillPointAbilityModifier(){
        int point = getAbilityModifierById(DnDUtil.INT);

        // レベル1の時は4倍
        if ( getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel() == 1)
            return point * 4;
        else
            return point;
    }

    /**
     * 選択したクラスでのレベル上昇毎の技能ポイント
     * @return
     */
    public Integer getSkillPointPerLevel(){
        ClassMaster klass =  getSessionBean().getCharacterGrowthRecord().getClassId();
        if (klass == null) {
            return 0;
        }
        // レベル 1のときは4倍
        Integer point = klass.getSkillPoint();
        if(point == null)    {
            return 0;
        }
        if ( getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel() == 1)
            return point * 4;//未実装
        else
            return point; //未実装
    }

    /**
     * <p>選択した種族でのレベル上昇毎の技能ポイントボーナス。実質人間だけ</p>
     * <p>レベル1の時だけ4ポイント</p>
     * @return
     */
    public Integer getSkillPointRaceBonus(){
        RaceMaster race = getCharacterRecord().getRaceId();
        if(race != null && "人間".equals(race.getRaceName())){
            if( getSessionBean().getCharacterGrowthRecord().getCharacterGrowthRecordPK().getCharacterLevel() == 1)
                return 4;
            else
                return 1;
        }
        return 0;
    }
}

