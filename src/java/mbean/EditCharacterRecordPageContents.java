/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import ejb.AbilityMasterFacade;
import ejb.AlignmentMasterFacade;
import ejb.CharacterAbilityRecordFacade;
import ejb.CharacterEquipmentFacade;
import ejb.CharacterGrowthRecordFacade;
import ejb.CharacterRecordFacade;
import ejb.CharacterSaveRecordFacade;
import ejb.CharacterSkillGrowthRecordFacade;
import ejb.CharacterSkillRecordFacade;
import ejb.ClassMasterFacade;
import ejb.ClassSaveMasterFacade;
import ejb.ClassSkillMasterFacade;
import ejb.GenderMasterFacade;
import ejb.RaceAbilityMasterFacade;
import ejb.RaceMasterFacade;
import ejb.ReligionMasterFacade;
import ejb.SaveMasterFacade;
import ejb.SkillMasterFacade;
import ejb.SkillSynergyMasterFacade;
import entity.AbilityMaster;
import entity.BonusRankMaster;
import entity.CharacterAbilityRecord;
import entity.CharacterEquipment;
import entity.CharacterGrowthRecord;
import entity.CharacterRecord;
import entity.CharacterSaveRecord;
import entity.CharacterSkillGrowthRecord;
import entity.CharacterSkillRecord;
import entity.SkillMaster;
import entity.ClassMaster;
import entity.ClassSkillMaster;
import entity.RaceAbilityMaster;
import entity.RaceMaster;
import entity.RaceSaveMaster;
import entity.SaveMaster;
import entity.SkillSynergyMaster;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;

/**
 * <p>Page bean that corresponds to a similarly named JSP page.  This
 * class contains component definitions (and initialization code) for
 * all components that you have defined on this page, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version CharacterEditPageContents.java
 * @version Created on 2009/03/29, 22:47:51
 * @author ka78231
 */
@ManagedBean
@RequestScoped
public class EditCharacterRecordPageContents  extends BaseBean {

    @EJB
    private SkillSynergyMasterFacade skillSynergyMasterFacade;
    @EJB
    private CharacterEquipmentFacade characterEquipmentFacade;
    @EJB
    private CharacterSaveRecordFacade characterSaveRecordFacade;
    @EJB
    private ClassSaveMasterFacade classSaveMasterFacade;
    @EJB
    private RaceAbilityMasterFacade raceAbilityMasterFacade;
    @EJB
    private CharacterSkillRecordFacade characterSkillRecordFacade;
    @EJB
    private CharacterAbilityRecordFacade characterAbilityRecordFacade;
    @EJB
    private SaveMasterFacade saveMasterFacade;
    @EJB
    private SkillMasterFacade skillMasterFacade1;
    @EJB
    private ClassSkillMasterFacade classSkillMasterFacade;
    @EJB
    protected ClassMasterFacade classMasterFacade;
    @EJB
    protected CharacterSkillGrowthRecordFacade characterSkillGrowthRecordFacade;
    @EJB
    protected SkillMasterFacade skillMasterFacade;
    @EJB
    protected ReligionMasterFacade religionMasterFacade;
    @EJB
    protected AlignmentMasterFacade alignmentMasterFacade;
    @EJB
    protected AbilityMasterFacade abilityMasterFacade;
    @EJB
    protected GenderMasterFacade genderMasterFacade;
    @EJB
    protected RaceMasterFacade raceMasterFacade;
    @EJB
    protected CharacterGrowthRecordFacade characterGrowthRecordFacade;
    @EJB
    protected CharacterRecordFacade characterRecordFacade;


    protected HtmlDataTable abilityTable = new HtmlDataTable();

    public HtmlDataTable getAbilityTable() {
        return abilityTable;
    }

    public void setAbilityTable(HtmlDataTable hdt) {
        this.abilityTable = hdt;
    }

    protected HtmlDataTable skillTable = new HtmlDataTable();

    public HtmlDataTable getSkillTable() {
        return skillTable;
    }

    public void setSkillTable(HtmlDataTable hdt) {
        this.skillTable = hdt;
    }
    protected HtmlSelectOneMenu classDropDown = new HtmlSelectOneMenu();

    public HtmlSelectOneMenu getClassDropDown() {
        return classDropDown;
    }

    public void setClassDropDown(HtmlSelectOneMenu hsom) {
        this.classDropDown = hsom;
    }
    protected HtmlDataTable growthTable = new HtmlDataTable();

    public HtmlDataTable getGrowthTable() {
        return growthTable;
    }

    public void setGrowthTable(HtmlDataTable hdt) {
        this.growthTable = hdt;
    }

    private HtmlDataTable saveTable = new HtmlDataTable();

    public HtmlDataTable getSaveTable() {
        return saveTable;
    }

    public void setSaveTable(HtmlDataTable hdt) {
        this.saveTable = hdt;
    }

    // </editor-fold>
    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public EditCharacterRecordPageContents() {
    }

    @PostConstruct
    public void prerender() {
        int exp = 0;
        int nextLv = 0;
        
        setCharacterRecord( getSessionBean().getCharacterRecord());
        

        //////////////////////////////////////////////////////////////
        // 経験値を得る
        ///////////////////////////////////////////////////////////////
        if (getCharacterRecord().getExperience() != null) {
            exp = getCharacterRecord().getExperience().intValue();
        }

        ///////////////////////////////////////////////////////////
        //経験値からキャラクタレベルを計算
        ///////////////////////////////////////////////////////////
        int lv = DnDUtil.getLevel(exp).intValue();

         getSessionBean().setCharacterLevel(lv);

        /////////////////////////////////////////////////////////////
        //キャラクター能力値レコードのリストの作成
        /////////////////////////////////////////////////////////////
//        List<CharacterAbilityRecord> charaAbilityList =
//                characterAbilityRecordFacade.findByCharacter(getCharacterRecord());
//         getSessionBean().setCharacterAbilityRecordList(charaAbilityList);
//        List<CharacterAbilityRecord> tempCharaAbilityList = getCharacterRecord().getCharacterAbilityRecordList();
//         getSessionBean().setCharacterAbilityRecordList(tempCharaAbilityList);


        /////////////////////////////////////////////////////////////
        //キャラクター成長レコードのリストの作成
        /////////////////////////////////////////////////////////////
        // まずは現在のリストを入手
        List<CharacterGrowthRecord> searchedCharaGrowthList =
                characterGrowthRecordFacade.findByCharacter(getCharacterRecord());
        List<CharacterGrowthRecord> characterGrowthList = new ArrayList<CharacterGrowthRecord>();
        //足りないレベルのレコードを探す。もし無ければ作成。リストは必ずレベルごとにソートされている
        OUTER:
        for (int i = 1; i < lv + 1; i++) {
            for (CharacterGrowthRecord growth : searchedCharaGrowthList) {
                if (growth.getCharacterGrowthRecordPK().getCharacterLevel() == i) {
                    //作成したリストに追加
                    characterGrowthList.add(growth);
                    continue OUTER;
                }
            }            //無かったようだ。作成する .
            CharacterGrowthRecord newRecord =
                    new CharacterGrowthRecord(getCharacterRecord().getId().intValue(), i);
            try {
                characterGrowthRecordFacade.create(newRecord);
            } catch (Exception e) {
                e.printStackTrace();
                //error("キャラクターの成長レコードの作成に失敗しました");
                return;
            }
            //作成したリストにあらためて追加
            characterGrowthList.add(newRecord);
        }
        //キャラクターレコードにセット
        getCharacterRecord().setCharacterGrowthRecordList(characterGrowthList);

        /////////////////////////////////////////////////////////////
        //スキル成長レコードのリストの作成
        /////////////////////////////////////////////////////////////
        // まずは現在のリストを入手
        List<CharacterSkillGrowthRecord> searchedCharaSkillGrowthList =
                characterSkillGrowthRecordFacade.findByCharacter(getCharacterRecord());
        List<CharacterSkillGrowthRecord> characterSkillGrowthList = new ArrayList<CharacterSkillGrowthRecord>();
        //足りないレベルのレコードを探す。もし無ければ作成
        for (int i = 1; i < lv + 1; i++) {
            List<SkillMaster> skilllist = skillMasterFacade.findAll();
            OUTER:
            for (SkillMaster skill : skilllist) {
                for (CharacterSkillGrowthRecord skillgrowth : searchedCharaSkillGrowthList) {
                    if (skillgrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() == i && skillgrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill.getId().intValue()) {
                        //作成したリストに追加
                        characterSkillGrowthList.add(skillgrowth);
                        continue OUTER;
                    }
                }
                //無かったようだ。作成する .
                CharacterSkillGrowthRecord newRecord =
                        new CharacterSkillGrowthRecord(getCharacterRecord().getId().intValue(), i, skill.getId().intValue());

                //スキルポイントを0に初期化
                newRecord.setSkillPoint(0);
                try {
                    characterSkillGrowthRecordFacade.create(newRecord);
                } catch (Exception e) {
                    e.printStackTrace();
                    //error("キャラクターの技能成長レコードの作製に失敗しました");
                    return;
                }

                //作成したリストにあらためて追加
                characterSkillGrowthList.add(newRecord);
            }
        }
        //キャラクターレコードにセット
        getCharacterRecord().setCharacterSkillGrowthRecordList(characterSkillGrowthList);

        /////////////////////////////////////////////////////////////
        //キャラクター技能レコードのリストの作成
        /////////////////////////////////////////////////////////////
        //List<CharacterSkillRecord> skillRecord = characterSkillRecordFacade.findByCharacter(getCharacterRecord());
        // getSessionBean().setCharacterSkillRecordList(skillRecord);

        /////////////////////////////////////////////////////////////
        //キャラクターセーヴレコードのリストの作成
        /////////////////////////////////////////////////////////////
        //List<CharacterSaveRecord> saveRecord = characterSaveRecordFacade.findByCharacter(getCharacterRecord());
        // getSessionBean().setCharacterSaveRecordList(saveRecord);

        ///////////////////////////////////////////////////////////////////
        // キャラクターの装備データオブジェクトを取得
        //////////////////////////////////////////////////////////////////
        //CharacterEquipment equip = characterEquipmentFacade.find(getCharacterRecord().getId());
        // getSessionBean().setCharacterEquipment(equip);

        //////////////////////////////////////////////////////////////////////
        // 計算値
        //////////////////////////////////////////////////////////////////////

        // ------- 次のレベルの経験値 -------------------
        nextLv = DnDUtil.getExpForNextLevel(exp);
         getSessionBean().setNextExperience(nextLv);

        // ------ 合計 HP を計算 --------------------
        List<CharacterGrowthRecord> growthList = getCharacterRecord().getCharacterGrowthRecordList();
        int totalHP = 0;
        int bonus = getAbilityModifierById(DnDUtil.CON);
        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > lv) {
                break;
            }
            if (growth.getHitPoint() != null) {
                totalHP += growth.getHitPoint() + bonus;
            }
        }
         getSessionBean().setHitPointTotal(totalHP);
    }

    public void textField1_processValueChange(ValueChangeEvent vce) {
    }

    public String charaListButton_action() {
        return "list";
    }

    public String saveButton_action() {

        CharacterRecord charaRecord = getCharacterRecord();
        CharacterEquipment equip = getCharacterRecord().getCharacterEquipment();
        List<CharacterSkillRecord> skillRecordList = getCharacterRecord().getCharacterSkillRecordList();
        List<CharacterSkillGrowthRecord> skillGrowthList = getCharacterRecord().getCharacterSkillGrowthRecordList();
        List<CharacterGrowthRecord> growthList = getCharacterRecord().getCharacterGrowthRecordList();
        List<CharacterAbilityRecord> abilityList = getCharacterRecord().getCharacterAbilityRecordList();
        List<CharacterSaveRecord> saveList = getCharacterRecord().getCharacterSaveRecordList();

        //更新時間を記録
        Date date = new Date();
        charaRecord.setSaveTime(date);

        try {
            //更新
            characterRecordFacade.edit(charaRecord);
            characterEquipmentFacade.edit(equip);
            for (CharacterGrowthRecord growth : growthList) {
                characterGrowthRecordFacade.edit(growth);
            }
            for (CharacterSkillRecord skillRecord : skillRecordList) {
                characterSkillRecordFacade.edit(skillRecord);
            }
            for (CharacterSkillGrowthRecord skillGrowth : skillGrowthList) {
                characterSkillGrowthRecordFacade.edit(skillGrowth);
            }
            for (CharacterAbilityRecord ability : abilityList) {
                characterAbilityRecordFacade.edit(ability);
            }
            for (CharacterSaveRecord save : saveList) {
                characterSaveRecordFacade.edit(save);
            }
            //error("保存されました");
        } catch (Exception ex) {
            ex.printStackTrace();
            //error("キャラクターの保存に失敗しました");
            return null;
        }

        return null;
    }

    public String deleteButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        CharacterRecord charaRecord = getCharacterRecord();

        try {
            characterRecordFacade.remove(charaRecord);
        } catch (Exception ex) {
            ex.printStackTrace();
            //error("削除に失敗しました");
            return null;
        }
        return "charalist";
    }
    public String arm1Select_action() {
         getSessionBean().setMainArm(true);
        return "selectarm";
    }
    public String arm2Select_action() {
         getSessionBean().setMainArm(false);
        return "selectarm";
    }

    /*
     *------------------ ベース能力値 ---------------------------------
     */
    protected Integer abilityBase;
    //能力値の表(dataTable2)の行番号から該当する能力の値を得る

    public Integer getAbilityBase() {
        int abid = abilityTable.getRowIndex() + 1;
        return getAbilityBaseById(abid);
    }

    //能力値のID(1～6）を指定してから能力値を得る
    public Integer getAbilityBaseById(int id) {
        // 能力値 ID は 1-6 だが、List の ID は 0 から並んでいるので -1 する
        Integer result;
        List<CharacterAbilityRecord> abilityList = getCharacterRecord().getCharacterAbilityRecordList();
        CharacterAbilityRecord ability = abilityList.get(id - 1);
        result = ability.getBase();
//        result = getCharacterRecord().getCharacterAbilityRecordList().get(id - 1).getBase();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setAbilityBase(Integer newVal) {
        int ability = abilityTable.getRowIndex() + 1;
        setAbilityBaseById(ability, newVal);
        return;
    }

    public void setAbilityBaseById(int id, Integer newVal) {
        getCharacterRecord().getCharacterAbilityRecordList().get(id - 1).setBase(newVal);
        return;
    }
    /*
     * ------------------ 能力値 その他修正 ---------------------------------
     */
    protected Integer abilityMiscModifier;
    //能力値の表(dataTable2)の行番号から該当する能力の値を得る

    public Integer getAbilityMiscModifier() {
        int abid = abilityTable.getRowIndex() + 1;
        return getAbilityMiscModifierById(abid);
    }

    //能力値のID(1～6）を指定してから能力値を得る
    public Integer getAbilityMiscModifierById(int id) {
        // 能力値 ID は 1-6 だが、List の ID は 0 から並んでいるので -1 する
        Integer result;
        result = getCharacterRecord().getCharacterAbilityRecordList().get(id - 1).getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setAbilityMiscModifier(Integer newVal) {
        int ability = abilityTable.getRowIndex() + 1;
        setAbilityMiscModifierById(ability, newVal);
        return;
    }

    public void setAbilityMiscModifierById(int id, Integer newVal) {
        getCharacterRecord().getCharacterAbilityRecordList().get(id - 1).setMiscModifier(newVal);
        return;
    }
    /*
     *------------------ 能力値 特技修正値 ---------------------------------
     */
    protected Integer abilityFeatModifier;
    //能力値の表(dataTable2)の行番号から該当する能力の値を得る

    public Integer getAbilityFeatModifier() {
        int abid = abilityTable.getRowIndex() + 1;
        return getAbilityFeatModifierById(abid);
    }

    //能力値のID(1～6）を指定してから能力値を得る
    public Integer getAbilityFeatModifierById(int id) {
        // 能力値 ID は 1-6 だが、List の ID は 0 から並んでいるので -1 する
        Integer result;
        result = getCharacterRecord().getCharacterAbilityRecordList().get(id - 1).getFeatModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setAbilityFeatModifier(Integer newVal) {
        int ability = abilityTable.getRowIndex() + 1;
        setAbilityFeatModifierById(ability, newVal);
        return;
    }

    public void setAbilityFeatModifierById(int id, Integer newVal) {
        getCharacterRecord().getCharacterAbilityRecordList().get(id - 1).setFeatModifier(newVal);
        return;
    }
    /*
     *------------------ 能力値 種族 修正値  ---------------------------------
     */
    protected Integer abilityRaceModifier;

    public Integer getAbilityRaceModifier() {
        int abid = abilityTable.getRowIndex() + 1;

        return getAbilityRaceModifierById(abid);
    }

    public Integer getAbilityRaceModifierById(int id) {
        AbilityMaster ability = abilityMasterFacade.find(id);
        if (ability == null) {
            // abilityTable の RowIndex が不正？
            return 0;
        }
        //まだ種族が選択されてなければ 0 を返す。
        if (getCharacterRecord().getRaceId() == null) {
            return 0;
        }
        RaceMaster race = getCharacterRecord().getRaceId();
        RaceAbilityMaster raceAbility = raceAbilityMasterFacade.findByRaceAndAbility(race, ability);
        if (raceAbility == null) {
            return 0;
        }
        if(raceAbility.getModifier() == null){
            return 0;
        }
        return raceAbility.getModifier();
    }
    /*
     * ------------------ 能力値 レベル修正値 (計算値）  ---------------------------------
     */
    protected Integer abilityLevelModifier;

    public Integer getAbilityLevelModifier() {
        int abid = abilityTable.getRowIndex() + 1;
        return getAbilityLevelModifierById(abid);
    }

    public Integer getAbilityLevelModifierById(int id) {
        int modifier = 0;

        List<CharacterGrowthRecord> growthList =
                characterGrowthRecordFacade.findByCharacter(getCharacterRecord());

        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) {
                break;
            }
            Integer enhancedAb = growth.getAbilityEnhancement();
            //セットされていないレベルは飛ばす
            if (enhancedAb == null) {
                continue;
            }
            if (id == enhancedAb.intValue()) {
                modifier++;
            }
        }
        return modifier;
    }
    /*
     * ------------------ 能力値 合計  ---------------------------------
     */
    protected Integer abilityTotal;

    public Integer getAbilityTotal() {
        int abid = abilityTable.getRowIndex() + 1;
        return getAbilityTotalById(abid);
    }
    protected Integer abilityTotalById;

    public Integer getAbilityTotalById(int id) {
        return getAbilityBaseById(id) +
                getAbilityRaceModifierById(id) +
                getAbilityFeatModifierById(id) +
                getAbilityMiscModifierById(id) +
                getAbilityLevelModifierById(id);
    }
    /*
     * ------------------ 能力値 修正値  ---------------------------------
     */
    protected Integer abilityModifier;

    public Integer getAbilityModifier() {
        int ability = abilityTable.getRowIndex() + 1;
        return getAbilityModifierById(ability);
    }

    public Integer getAbilityModifierById(int ability) {

        return (getAbilityTotalById(ability) / 2) - 5;
    }
    /*
     * ------------------ 技能 対応能力修正値  ---------------------------------
     */
    protected Integer skillAbilityModifier;

    public Integer getSkillAbilityModifier() {
        int skill = skillTable.getRowIndex() + 1;
        return getSkillAbilityModifierById(skill);
    }

    public Integer getSkillAbilityModifierById(int skill) {

        return getAbilityModifierById(skillMasterFacade.find(skill).getAbilityId().getId());
    }
    /*
     * ------------------ 技能 対応能力値名 ---------------------------------
     */
    protected String skillAbilityName;

    public String getskillAbilityName() {
        int skill = skillTable.getRowIndex() + 1;
        return getskillAbilityNameById(skill);
    }

    public String getskillAbilityNameById(int skill) {

        return skillMasterFacade.find(skill).getAbilityId().getAbilityName();
    }
    /*
     * ------------------ 技能 対応能力値名 省略名---------------------------------
     */
    protected String skillAbilityShortName;

    public String getskillAbilityShortName() {
        int skill = skillTable.getRowIndex() + 1;
        return getskillAbilityShortNameById(skill);
    }

    public String getskillAbilityShortNameById(int skill) {

        String name;
        name = skillMasterFacade.find(skill).getAbilityId().getAbilityName();
        return name.substring(0, 1);
    }
    /*
     * ------------------ 技能 ポイント ---------------------------------
     */
    protected Integer skillTotalPoint;

    public Integer getSkillTotalPoint(){
        int skill = skillTable.getRowIndex() + 1;
        return getSkillTotalPointById(skill);
    }

    public Integer getSkillTotalPointById(int skill) {
        int point = 0;

        List<CharacterSkillGrowthRecord> skillGrowthlist = getCharacterRecord().getCharacterSkillGrowthRecordList();
        for (CharacterSkillGrowthRecord skillGrowth : skillGrowthlist) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill) {
                point += skillGrowth.getSkillPoint().intValue();
            }
        }
        return point;
    }
    /*
     * ------------------ 技能 判定値---------------------------------
     */
    protected Integer skillTotalCheckModifier;

    public Integer getSkillTotalCheckModifier() {
        return getSkillAbilityModifier() +
                getSkillTotalRank() +
                getskillMiscModifier() +
                getskillArmorModifier() +
                getskillSynergyModifier();
    }
    /*
     * ------------------ 技能 その他修正 ---------------------------------
     */
    protected Integer skillMiscModifier;

    public Integer getskillMiscModifier() {
        Integer result;
        int index = skillTable.getRowIndex();
        List<CharacterSkillRecord> skillRecordList = getCharacterRecord().getCharacterSkillRecordList();
        // skillTable の RowIndex と List の index は同一の特技をさしているはず
        result = skillRecordList.get(index).getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setskillMiscModifier(Integer skillMiscModifier) {
        int index = skillTable.getRowIndex();
        List<CharacterSkillRecord> skillRecord = getCharacterRecord().getCharacterSkillRecordList();
        // skillTable の RowIndex と List の index は同一の特技をさしているはず
        skillRecord.get(index).setMiscModifier(skillMiscModifier);
    }
    /*
     * ------------------ 技能 ランク（計算値）---------------------------------
     */
    //  ポイントとクラスから計算する
    protected Integer skillRank;
    protected Integer skillRankByLevelAndSkill;

    /** 技能ランク。
     * <p>クラス外技能の場合 1/2 になるの float を返す。受け取り側で切り捨てする必要がある</p>
     * @param growth キャラクタ成長レコード
     * @param skill スキルマスター
     * @return スキルランク
     */
    public Float getskillRankByLevelAndSkill(CharacterGrowthRecord growth, SkillMaster skill) {
        int point = 0;
        float rank = 0;
        ClassMaster klass = growth.getClassId();

        //まずは技能成長レコードからこのレベルでのスキルポイントを得る
        List<CharacterSkillGrowthRecord> skillgrowthlist = getCharacterRecord().getCharacterSkillGrowthRecordList();
        for (CharacterSkillGrowthRecord skillGrowth : skillgrowthlist) {
            Integer level = skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel();
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (level >  getSessionBean().getCharacterLevel()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill.getId().intValue() &&
                    level == growth.getCharacterGrowthRecordPK().getCharacterLevel()) {
                point = skillGrowth.getSkillPoint().intValue();
                break;
            }
        }
        if (point == 0) {
            //ポイントが0ならクラス技能かどうか確認する必要はない
            return 0f;
        }
        // つづいてクラス技能かどうか確認する
        if (isClassSkillByLevelAndSkill(growth, skill)) {
            //クラス技能
            rank = point;
        } else {
            //クラス外技能
            rank = (new Float(point)) / 2f;
        }
        return rank;
    }

    public Integer getSkillTotalRank() {
        int result = 0;
        int skillId = skillTable.getRowIndex() + 1;

        result = getSkillTotalRankById(skillId);
        return result;
    }

    public Integer getSkillTotalRankById(int skillId) {
        Float totalRank = 0f;

        SkillMaster skill = skillMasterFacade.find(skillId);
        // レベル毎のループ
        List<CharacterGrowthRecord> growthlist = getCharacterRecord().getCharacterGrowthRecordList();
        for (CharacterGrowthRecord growth : growthlist) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) {
                break;
            }
            totalRank += getskillRankByLevelAndSkill(growth, skill);
        }
        return (totalRank.intValue());
    }

    /*
     *  -------------------- 技能がランク無しでも実施可能か ---------------------
     */
    protected boolean skillAcceptNoRankBySkillId;

    public boolean isSkillAcceptoRankBySkillId(int skillid) {
        SkillMaster skill = skillMasterFacade.find(skillid);

        if (skill == null) {
            return false;
        }

        return (skill.getAcceptNoRank().intValue() == 1);
    }
    protected String abilityCheckAcceptNoRank;

    public String getSkillCheckAcceptNoRank() {
        int skill = skillTable.getRowIndex() + 1;
        if (isSkillAcceptoRankBySkillId(skill)) {
            return "■";
        } else {
            return "□";
        }
    }
    /*
     *  -------------------- クラス技能かどうか ---------------------
     */

    public boolean isClassSkillByClassAndSkill(ClassMaster klass, SkillMaster skill) {
        List<ClassSkillMaster> classSkillList = classSkillMasterFacade.findByClass(klass);
        for (ClassSkillMaster classSkill : classSkillList) {
            if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId().intValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     *  あるスキルがこのキャラにとってクラス技能かどうかをチェックする。
     * 習得しているクラスの中でひとつでも該当技能がクラス技能であればクラス技能になる
     * @param skill
     * @return boolean
     */
    public boolean isClassSkillByLevelAndSkill(CharacterGrowthRecord growthLevel, SkillMaster skill) {
        List<CharacterGrowthRecord> growthList = getCharacterRecord().getCharacterGrowthRecordList();
        for (CharacterGrowthRecord growth : growthList) {
            // チェックしようとしているレベル以下のクラスしか確認しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > growthLevel.getCharacterGrowthRecordPK().getCharacterLevel()) {
                break;
            }
            ClassMaster klass = growth.getClassId();
            if (klass == null) {
                continue;
            }
            List<ClassSkillMaster> classSkillList = classSkillMasterFacade.findByClass(klass);
            for (ClassSkillMaster classSkill : classSkillList) {
                if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId().intValue()) {
                    return true;
                }
            }
        }
        return false;
    }
    /*
     *  -------------------- 技能ランクを持っているかどうか ---------------------
     */
    public boolean hasSkillRankBySkill(SkillMaster skill) {
        List<CharacterSkillGrowthRecord> skillGrowthList = getCharacterRecord().getCharacterSkillGrowthRecordList();

        for (CharacterSkillGrowthRecord skillGrowth : skillGrowthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() == skill.getId().intValue() &&
                    skillGrowth.getSkillPoint() != null &&
                    skillGrowth.getSkillPoint().intValue() > 0) {
                return true;
            }
        }
        return false;
    }

    /*
     * ------------------ 技能 鎧、盾ペナルティ ---------------------------------
     */
    public Integer getskillArmorModifier() {
        Integer result;
        int skillId = skillTable.getRowIndex() + 1;
        result = getskillArmorModifierById(skillId);
        return result;
    }

    public Integer getskillArmorModifierById(int skillId) {
        int result = 0;
        SkillMaster skill = skillMasterFacade.find(skillId);

        if (skill.getArmorCheck() == 0) {
            //この技能に防具ペナルティはない
            return 0;
        }

        CharacterEquipment equip = getCharacterRecord().getCharacterEquipment();
        if (equip.getSkillArmorMod() != null) {
            result += equip.getSkillArmorMod();
        }
        if (equip.getSkillShieldMod() != null) {
            result += equip.getSkillShieldMod();
        }
        if (skillId == 22) {
            // 水泳は2倍！！
            result *= 2;
        }
        return result;
    }

    /*
     * ------------------ 技能 相乗効果 ---------------------------------
     */
    public Integer getskillSynergyModifier() {
        Integer result;
        int skillId = skillTable.getRowIndex() + 1;
        result = getskillSynergyModifierById(skillId);
        return result;
    }

    public Integer getskillSynergyModifierById(int skillId) {
        int result = 0;
        SkillMaster skill = skillMasterFacade.find(skillId);

        List<SkillSynergyMaster> synergyList = skillSynergyMasterFacade.findBySkill(skill);
        for (SkillSynergyMaster synergy : synergyList) {
            int affectedSkillId = synergy.getSkillSynergyMasterPK().getAffectedBy();
            int affectedSkillRank = getSkillTotalRankById(affectedSkillId);
            if (affectedSkillRank > 0) {
                //ランク 5 で対象技能が + 2
                result += (affectedSkillRank / 5) * 2;
            }
        }
        return result;
    }

    /*
     * ----- 技能  ランクもっているか？ --------------------------
     */
    public String getSkillRankCheck() {
        int skillId = skillTable.getRowIndex() + 1;
        SkillMaster skill = skillMasterFacade.find(skillId);
        ClassMaster klass;

        List<CharacterGrowthRecord> growthList = getCharacterRecord().getCharacterGrowthRecordList();

        if (hasSkillRankBySkill(skill)) {
            return "■";
        }
        return "□";
        /*
        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) {
                break;
            }
            if ((klass = growth.getClassId()) != null) {
                if (hasSkillRankBySkill(skill)) {
                    return "■";
                }
            }
        }
        return "□";
         */
    }
    /*
     * ----- キャラクタレベル毎のクラスの選択 ----------------------------------------------
     */
    protected Integer selectedClassByRow;

    public Integer getSelectedClassByRow() {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterRecord().getCharacterGrowthRecordList().get(index);
        if (growth.getClassId() == null) {
            return null;
        } else {
            return growth.getClassId().getId();
        }

    }

    public void setSelectedClassByRow(Integer classId) {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterRecord().getCharacterGrowthRecordList().get(index);
        ClassMaster klass;

        if (classId == null) {
            growth.setClassId(null);
            return;

        }
        klass = classMasterFacade.find(classId);
        growth.setClassId(klass);
        return;

    }

    /*
     * ----- キャラクタレベル 4 レベル毎の上昇能力値の設定------
     */
    public Integer getSelectedAbilityEnhancementByRow() {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterRecord().getCharacterGrowthRecordList().get(index);
        if (growth.getAbilityEnhancement() == null) {
            return null;
        } else {
            return growth.getAbilityEnhancement();
        }
    }

    public void setSelectedAbilityEnhancementByRow(Integer abilityId) {
        int index = growthTable.getRowIndex();
        CharacterGrowthRecord growth = getCharacterRecord().getCharacterGrowthRecordList().get(index);
        ClassMaster klass;

        if (abilityId == null) {
            growth.setAbilityEnhancement(null);
            return;
        }
        growth.setAbilityEnhancement(abilityId);
        return;
    }

    /*
     *  ---- キャラクタレベル毎のスキルの変更ボタン ----------------------------------
     */
    public String editSkillButton_action() {
        int index = growthTable.getRowIndex();
        // Lv とキャラクターレコードを元に、キャラクター成長レコードを得、セッションBeanにセットする
         getSessionBean().setCharacterGrowthRecord(getCharacterRecord().getCharacterGrowthRecordList().get(index));

        return "editskill";
    }
    /*
     * ------------------ セーヴ 対応能力修正値  ---------------------------------
     */
    protected Integer saveAbilityModifier;

    public Integer getSaveAbilityModifier() {
        int saveId = saveTable.getRowIndex() + 1;
        return getSaveAbilityModifierById(saveId);
    }

    public Integer getSaveAbilityModifierById(int saveId) {

        return getAbilityModifierById(saveMasterFacade.find(saveId).getAbilityId().getId());
    }
    /*
     * ------------------ セーヴボーナス クラス合計  ---------------------------------
     */
    protected Integer saveClassBonus;

    public Integer getSaveClassBonus() {
        int saveId = saveTable.getRowIndex() + 1;
        return getSaveClassBonusById(saveId);
    }

    public Integer getSaveClassBonusById(int saveId) {
        int bonus = 0;

        SaveMaster save = saveMasterFacade.find(saveId);

        List<CharacterGrowthRecord> growthList = getCharacterRecord().getCharacterGrowthRecordList();

        //習得しているクラスごとに最大レベルを求める
        Map<Integer, Integer> classMap = new HashMap<Integer, Integer>();
        for (CharacterGrowthRecord growth : growthList) {
            // 経験値から見たキャラクタレベル以上は考慮しない
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) {
                break;
            }
            if (growth.getClassId() == null) {
                //まだクラスが設定されていないもよう
                continue;
            }
            Integer classId = growth.getClassId().getId();
            if (classMap.get(classId) == null) {
                classMap.put(classId, 1);
                continue;
            } else {
                classMap.put(classId, classMap.get(classId) + 1);
            }
        }

        //クラスごとに最大レベルからクラスボーナスを得る
        for (Map.Entry<Integer, Integer> classEntry : classMap.entrySet()) {
            Integer classId = classEntry.getKey();
            Integer lv = classEntry.getValue();

            BonusRankMaster rank = classSaveMasterFacade.findByClassAndSave(classMasterFacade.find(classId), save).getRankId();
            if (rank == null) {
                //まだクラスのセーブボーナスランクが設定されていないもよう
                return 0;
            }
            switch (rank.getId().intValue()) {
                case 1:
                    bonus += lv / 2 + 2;
                    break;
                case 2:
                    bonus += lv;
                    break;
                case 3:
                    bonus += lv / 3;
                    break;
                default:
                    bonus = 0;
            }
        }
        return bonus;
    }
    /*
     * ------------------ セーヴ その他修正値  ---------------------------------
     */
    protected Integer saveMiscModifier;

    public Integer getSaveMiscModifier() {
        int saveId = saveTable.getRowIndex() + 1;
        return getSaveMiscModifierById(saveId);
    }

    public Integer getSaveMiscModifierById(int saveId) {
        Integer result;
        CharacterSaveRecord saveRecord = getCharacterRecord().getCharacterSaveRecordList().get(saveId - 1);
        result = saveRecord.getMiscModifier();
        if (result == null) {
            return 0;
        } else {
            return result;
        }
    }

    public void setSaveMiscModifier(Integer miscModifier) {
        int saveId = saveTable.getRowIndex() + 1;
        CharacterSaveRecord saveRecord = getCharacterRecord().getCharacterSaveRecordList().get(saveId - 1);
        saveRecord.setMiscModifier(miscModifier);
    }
    /*
     * ------------------ セーヴ 種族修正値  ---------------------------------
     */
    public Integer getSaveRaceModifierById(int saveId) {
        if(getCharacterRecord().getRaceId() != null){
            List<RaceSaveMaster> raceSaveList = (List<RaceSaveMaster>) getCharacterRecord().getRaceId().getRaceSaveMasterCollection();
            for(RaceSaveMaster raceSave : raceSaveList){
                if (raceSave.getRaceSaveMasterPK().getSaveId() == saveId){
                    if (raceSave.getModifier() != null) {
                        return raceSave.getModifier();
                    }
                }
            }
        }
        return 0;
    }
    public Integer getSaveRaceModifier() {
        int saveId = saveTable.getRowIndex() + 1;
        return getSaveRaceModifierById(saveId);
    }

    /*
     * ------------------ セーヴボーナス トータル計  ---------------------------------
     */
    protected Integer saveToal;

    public Integer getSaveTotal() {
        int saveId = saveTable.getRowIndex() + 1;
        return getSaveTotalById(saveId);
    }

    public Integer getSaveTotalById(int saveId) {
        return getSaveAbilityModifierById(saveId)
                + getSaveClassBonusById(saveId)
                + getSaveMiscModifierById(saveId)
                + getSaveRaceModifierById(saveId);
    }
    /*
     * ------------------ イニシアチブ その修正 -----------------------------------------
     */
    protected Integer initiativeMiscModifier;

    public Integer getInitiativeMiscModifier() {
        Integer mod = getCharacterRecord().getInitiativeMiscModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setInitiativeMiscModifier(Integer modifier) {
        getCharacterRecord().setInitiativeMiscModifier(modifier);
    }
    /*
     * ------------------- イニシアチブ 技能修正 ---------------------------------------------
     */
    protected Integer initiativeFeatModifier;

    public Integer getInitiativeFeatModifier() {
        Integer mod = getCharacterRecord().getInitiativeFeatModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setInitiativeFeatModifier(Integer modifier) {
        getCharacterRecord().setInitiativeFeatModifier(modifier);
    }

    /*
     * ------------------ イニシアチブ 能力値修正 -----------------------------------------
     */
    public Integer getInitiativeAbilityModifier() {
        // 敏捷力の ID は 2
        return getAbilityModifierById(DnDUtil.DEX);
    }
    /*
     * ------------------ イニシアチブ 合計 （計算値）-----------------------------------------
     */
    protected Integer initiativeTotal;

    public Integer getInitiativeTotal() {
        return getInitiativeAbilityModifier() + getInitiativeFeatModifier() + getInitiativeMiscModifier();
    }
    /*
     * ------------------ 移動速度 合計 （計算値）-----------------------------------------
     */
    protected Integer speed;

    public Integer getSpeed() {
        return getSpeedRaceBasse() + getSpeedFeatModifier() + getSpeedMiscModifier();
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    /*
     * ------------------ 移動速度 種族基本速度 -----------------------------------------
     */
    protected Integer speedRaceBasse;

    public Integer getSpeedRaceBasse() {
        RaceMaster race = getCharacterRecord().getRaceId();
        if (race == null) {
            //まだ種族が決まってない。 デフォルト
            return new Integer(0);
        }
        return race.getSpeed();
    }
    /*
     * ------------------ 移動速度 特技修正値 -----------------------------------------
     */
    protected Integer speedFeatModifier;

    public Integer getSpeedFeatModifier() {
        Integer mod = getCharacterRecord().getSpeedFeatModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setSpeedFeatModifier(Integer speedFeatModifier) {
        getCharacterRecord().setSpeedFeatModifier(speedFeatModifier);
    }
    /*
     * ------------------ 移動 その他修正値 -----------------------------------------
     */
    protected Integer speedMiscModifier;

    public Integer getSpeedMiscModifier() {
        Integer mod = getCharacterRecord().getSpeedMiscModifier();
        if (mod == null) {
            return new Integer(0);
        }
        return mod;
    }

    public void setSpeedMiscModifier(Integer speedMiscModifier) {
        getCharacterRecord().setSpeedMiscModifier(speedMiscModifier);
    }

    public void textField13_processValueChange(ValueChangeEvent vce) {
    }

    /**
     * ----------------AC アーマークラス(計算値) ----------------------------------
     */
    public Integer getAcNormal() {
        CharacterRecord chara = getCharacterRecord();
        return 10 +
                getAcAbilityModifier() +
                (getAcRaceModifier() == null ? 0 : getAcRaceModifier()) +
                (chara.getAcArmor() == null ? 0 : chara.getAcArmor()) +
                (chara.getAcShield() == null ? 0 : chara.getAcShield()) +
                (chara.getAcMiscMod() == null ? 0 : chara.getAcMiscMod());
    }

    /**
     * 接触AC<p>
     * 種族ボーナスとその他ボーナス。本当はボーナスの種類で決めるべきか。<p>
     * 例えば､反発ボーナスは組み込めるが盾ボーナスはだめとか。
     * @return
     */
    public Integer getAcTouch() {
        CharacterRecord chara = getCharacterRecord();
        return 10 +
                getAcAbilityModifier() +
                (getAcRaceModifier() == null ? 0 : getAcRaceModifier()) +
                (chara.getAcMiscMod() == null ? 0 : chara.getAcMiscMod());
    }

    public Integer getAcFlatFooted() {
        CharacterRecord chara = getCharacterRecord();
        return 10 +
                (getAcRaceModifier() == null ? 0 : getAcRaceModifier()) +
                (chara.getAcArmor() == null ? 0 : chara.getAcArmor()) +
                (chara.getAcShield() == null ? 0 : chara.getAcShield()) +
                (chara.getAcMiscMod() == null ? 0 : chara.getAcMiscMod());
    }
    /**
     * ------------------ AC 能力値修正 -----------------------------------------
     */
    public Integer getAcAbilityModifier() {
        // 敏捷力の ID は 2. 防具によって、敏捷力ボーナスがどれだけ適用できるかがきまる。
        int bonus = 0;
        CharacterEquipment equip = getCharacterRecord().getCharacterEquipment();
        bonus = getAbilityModifierById(DnDUtil.DEX).intValue();
        if (equip.getDexAcArmorLimit() != null && equip.getDexAcArmorLimit() < bonus) {
            bonus = equip.getDexAcArmorLimit().intValue();
        }
        if (equip.getDexAcShieldLimit() != null && equip.getDexAcShieldLimit() < bonus) {
            bonus = equip.getDexAcShieldLimit().intValue();
        }
        return bonus;
    }
    /*
     * ------------ AC 盾ボーナス -------------------
     */
    protected Integer acShield;

    /**
     * Get the value of acShield
     *
     * @return the value of acShield
     */
    public Integer getAcShield() {
        return getCharacterRecord().getAcShield();
    }

    /*
     * ---------- AC 鎧ボーナス-------------
     */
    protected Integer acArmor;

    /**
     * Get the value of acArmor
     *
     * @return the value of acArmor
     */
    public Integer getAcArmor() {
        return getCharacterRecord().getAcArmor();
    }
    /*
     * ---------- AC 種族修正-------------
     */
    protected Integer acRace;

    public Integer getAcRaceModifier() {
        RaceMaster race = getCharacterRecord().getRaceId();
        // 種族未設定
        if(race == null){
            return 0;
        }

        return race.getSizeId().getAcModifier();
    }
    /*
     * ---------- AC その他-------------
     */
    protected Integer acMiscMod;

    public Integer getAcMiscMod() {
        return getCharacterRecord().getAcMiscMod();
    }
    /*
     * -------------技能編集ボタン（レベル無し) --------------------------------
     */
    public String editSkillNomalButton_action() {
        // キャラクターレコードを元に、キャラクター成長レコードを得、セッションBeanにセットする。Lv は 1 固定
         getSessionBean().setCharacterGrowthRecord(getCharacterRecord().getCharacterGrowthRecordList().get(0));
        return "editskill";
    }
    /*
     * ------------ 能力値上昇の選択リストを非表示にするかどうかを決める -------------
     */
    protected boolean nextButtonDisabled;

    public boolean isAbilityEnhancementDisabled() {
        int level = growthTable.getRowIndex() + 1;
        // 4 レベルの倍数のときは表示
        return (level % 4 != 0);
    }

    /*
     * ------------------ ヒットポイント用能力値修正 -----------------------------------------
     */
    public Integer getHitPointAbilityModifier() {
        // 敏捷力の ID は 2
        return getAbilityModifierById(DnDUtil.CON);
    }

    /*
     * ---------  最終更新日 --------------------------------------
     */
    public String getLastChange() {
        return DnDUtil.getLastChange(getCharacterRecord());
    }

    /*
     * -------- 攻撃ボーナス 基本攻撃ボーナス ---------------------------
     */
    public Integer getBaseAttackTotal() {
        int baseAttack = 0;
        //クラスのリストをつくり、各クラスのレベルを計算する
        List<CharacterGrowthRecord> growthList = getCharacterRecord().getCharacterGrowthRecordList();
        Map<ClassMaster, Integer> classMap = new HashMap<ClassMaster, Integer>();
        for (CharacterGrowthRecord growth : growthList) {
            // 現在のキャラクタレベル分だけしか見ないでよい。
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() >  getSessionBean().getCharacterLevel()) {
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
        }
        for (Map.Entry<ClassMaster, Integer> mapEntry : classMap.entrySet()) {
            ClassMaster klass = mapEntry.getKey();
            baseAttack += getBaseAttackByClassAndLevel(klass, mapEntry.getValue());
        }
        return baseAttack;
    }

    public Integer getBaseAttackByClassAndLevel(ClassMaster klass, Integer lv) {
        if (klass == null) {
            return 0;
        }
        Integer rank = klass.getBaseAttackRankId().getId();
        if (rank == null) {
            //クラスにBABが未設定の時は劣悪と判断
            return (1 / 2) * lv;
        }
        Float result; // 端数の計算のため
        switch (rank) {
            case 1: // 良好
                return lv;
            case 2: //平均
                result = lv * (3F / 4F);
                return result.intValue();
            case 3: // 劣悪
                result = lv * (1F / 2F);
                return result.intValue();
            default: // 無し？
                return 0;
        }
    }
    /*
     * -------- 攻撃ボーナス 遠隔攻撃ボーナス ---------------------------
     */

    public Integer getRangeAttackBonus() {
        return getBaseAttackTotal() + getAbilityModifierById(DnDUtil.DEX);
    }
    /*
     * -------- 攻撃ボーナス 近接攻撃ボーナス ---------------------------
     */

    public Integer getMeleeAttackBonus() {
        return getBaseAttackTotal() + getAbilityModifierById(DnDUtil.STR);
    }
    /*
     * -------- 攻撃ボーナス 組み付きボーナス ---------------------------
     */

    public Integer getgrappleBonus() {
        return getMeleeAttackBonus();
    }
    /*
     * --------- 攻撃ボーナス 近接筋力ボーナス----------------
     */

    public Integer getAttackBonusStrengthBonus() {
        return getAbilityModifierById(DnDUtil.STR);
    }
    /*
     * --------- 攻撃ボーナス 遠隔筋力ボーナス----------------
     */

    public Integer getAttackBonusDexBonus() {
        return getAbilityModifierById(DnDUtil.DEX);
    }

    public void dropdown2_processValueChange(ValueChangeEvent vce) {
    }

    public String cancelButton_action() {
        return "charalist";
    }

    public String viewLink_action() {
        return "view";
    }

    protected CharacterRecord characterRecord;

    public CharacterRecord getCharacterRecord() {
        return characterRecord;
    }

    public void setCharacterRecord(CharacterRecord characterRecord) {
        this.characterRecord = characterRecord;
    }

    /*
     * ------------ アイテム 武器 -----------------------------------
     * 未実装

    public String getArm1 (){
        ArmMaster arm1 = getCharacterRecord().getCharacterEquipment().getArm1();
        if(arm1 != null) {
            return arm1.getName();
        }
        return "未装備";
    }
    public String getArm2 (){
        ArmMaster arm2 = getCharacterRecord().getCharacterEquipment().getArm2();
        if(arm2 != null) {
            return arm2.getName();
        }
        return "未装備";
    }
     */

}

