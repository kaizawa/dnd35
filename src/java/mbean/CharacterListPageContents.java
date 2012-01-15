/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;



import ejb.CharacterEquipmentFacade;
import ejb.CharacterSkillRecordFacade;
import ejb.AbilityMasterFacade;
import ejb.CharacterGrowthRecordFacade;
import ejb.SaveMasterFacade;
import ejb.CharacterAbilityRecordFacade;
import ejb.SkillMasterFacade;
import ejb.CharacterSaveRecordFacade;
import ejb.CharacterRecordFacade;
import ejb.CharacterSkillGrowthRecordFacade;
import ejb.CampaignMasterFacade;
import entity.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

@ManagedBean
@RequestScoped
public class CharacterListPageContents  extends BaseBean {
    @EJB
    private CharacterSkillGrowthRecordFacade characterSkillGrowthRecordFacade;
    @EJB
    private CharacterGrowthRecordFacade characterGrowthRecordFacade;
    @EJB
    private CharacterEquipmentFacade characterEquipmentFacade;
    @EJB
    private CharacterSaveRecordFacade characterSaveRecordFacade;
    @EJB
    private SaveMasterFacade saveMasterFacade;
    @EJB
    private AbilityMasterFacade abilityMasterFacade;
    @EJB
    private CharacterAbilityRecordFacade characterAbilityRecordFacade;
    @EJB
    private SkillMasterFacade skillMasterFacade;
    @EJB
    private CharacterSkillRecordFacade characterSkillRecordFacade;
    @EJB
    private CharacterRecordFacade characterRecordFacade;
    @EJB
    private CampaignMasterFacade campaignMasterFacade;

    private HtmlDataTable dataTable1 = new HtmlDataTable();

    public HtmlDataTable getDataTable1() {
        return dataTable1;
    }

    public void setDataTable1(HtmlDataTable hdt) {
        this.dataTable1 = hdt;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public CharacterListPageContents() {
    }
    

    @PostConstruct
    public void init() {
        
        List<CharacterRecord> charaFindAll;
        if ( getSessionBean().getCharacterListSelectedCampaign() == null) {
            charaFindAll = characterRecordFacade.findAll();
        } else {
            //選択されたキャンペーンキャラクターレコードのリストを得る
            charaFindAll = characterRecordFacade.findByCampaignId( getSessionBean().getCharacterListSelectedCampaign());
        }

         getSessionBean().setCharacterRecordList(charaFindAll);


        //サマリー用のクラス CharacterRecordSummary のリストを作る
        //その中にプレーヤー名とキャラクター名、そして計算したキャラクタレベルを入れておく
        List<CharacterRecordSummary> charaRecordSummary = new ArrayList<CharacterRecordSummary>();
        for (CharacterRecord charaRecord : charaFindAll) {
            Integer lv = null;
            int exp = 0; // 経験値 が登録されていなかったら Exp を 0 とする

            if (charaRecord.getExperience() != null) {
                exp = charaRecord.getExperience().intValue();
            }
            CharacterRecordSummary charaSummary = new CharacterRecordSummary();
            charaSummary.setPlayerName(charaRecord.getPlayerName());
            charaSummary.setCharacterName(charaRecord.getCharacterName());
            if (charaRecord.getCampaignId() == null) {
                charaSummary.setCampaign("未設定");
            } else {
                charaSummary.setCampaign(charaRecord.getCampaignId().getCampaignName());
            }

            //経験値からキャラクタレベルを計算
            lv = DnDUtil.getLevel(exp);
            charaSummary.setCharacterLevel(lv);

            //クラスのリストをつくり、各クラスのレベルを計算する
            List<CharacterGrowthRecord> growthList = characterGrowthRecordFacade.findByCharacter(charaRecord);
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
            String classList = "";
            for (Map.Entry<ClassMaster, Integer> mapEntry : classMap.entrySet()) {
                ClassMaster klass = mapEntry.getKey();
                String line =
                        (klass == null ? "未設定" : klass.getClassName()) +
                        mapEntry.getValue() + " Lv" + ", ";
                classList = classList + line;
            }
            charaSummary.setClassList(classList);

            // 最終更新日
            charaSummary.setLastChange(DnDUtil.getLastChange(charaRecord));

            charaRecordSummary.add(charaSummary);
        }

         getSessionBean().setCharacterRecordSummary(charaRecordSummary);
    }

    public String charaEditLink_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        int index = dataTable1.getRowIndex();

        //この index は charaRecordSummary のリストのインデックスだが、
        // 基本 同じなので characterRecordList のインデックスとして使っている
        CharacterRecord characterRecord =  getSessionBean().getCharacterRecordList().get(index);

        //管理Beanへ反映
         getSessionBean().setCharacterRecord(characterRecord);

        return "EditCharacterRecordPageContents";
    }

    public String charaViewButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        int index = dataTable1.getRowIndex();

        //この index は charaRecordSummary のリストのインデックスだが、
        // 基本 同じなので characterRecordList のインデックスとして使っている
        CharacterRecord characterRecord =  getSessionBean().getCharacterRecordList().get(index);

        //管理Beanへ反映
         getSessionBean().setCharacterRecord(characterRecord);

        return "PrintableCharacterRecordPage";
    }

    public String button1_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return "EditClassPageContents";
    }

    public String saveButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String classListButton_action() {
        // TODO: ボタンクリックアクションを処理します。戻り値は、
        // ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }

    public String newCharaButton_action() {
        CampaignMaster campaign;

        ////////////////////////////////
        //  CharacterRecord の作成
        ////////////////////////////////
        CharacterRecord characterRecord = new CharacterRecord();
        //経験値を0にセット
        characterRecord.setExperience(0);
        characterRecord.setAcArmor(0);
        characterRecord.setAcMiscMod(0);
        characterRecord.setAcShield(0);
        // もし選択されていれば現在選択しているキャンペーンをデフォルト値としてセット
        if ( getSessionBean().getCharacterListSelectedCampaign() != null) {
            campaign = campaignMasterFacade.find( getSessionBean().getCharacterListSelectedCampaign());
            characterRecord.setCampaignId(campaign);
        }

        try {
            characterRecordFacade.create(characterRecord);
        } catch (Exception ex) {
            ex.printStackTrace();
            //error("キャラクターの作成に失敗しました");
            return null;
        }

        ////////////////////////////////
        //  CharacterSkillRecord と CharacterSkillGrowthRecord の作成
        ////////////////////////////////
        //キャラクター技能毎のレコードを作成
        List<SkillMaster> skilllist = skillMasterFacade.findAll();
        List<CharacterSkillRecord> charaSkillList = new ArrayList<CharacterSkillRecord>();
        List<CharacterSkillGrowthRecord> charaSkillGrowthList = new ArrayList<CharacterSkillGrowthRecord>();

        for (SkillMaster skill : skilllist) {
            //あらたに CharacterSkillRecord を確保
            CharacterSkillRecord charaSkillRecord
                    = new CharacterSkillRecord(characterRecord.getId().intValue(), skill.getId().intValue());
            charaSkillRecord.setMiscModifier(0);
            try {
                characterSkillRecordFacade.create(charaSkillRecord);
            } catch (Exception ex) {
                ex.printStackTrace();
                //error("キャラクター技能レコードの作成に失敗しました");
                return null;
            }
            charaSkillList.add(charaSkillRecord);

            //あらたに 1Lv用の CharacterSkillGrowthRecord を作成
            CharacterSkillGrowthRecord skillGrowthRecord =
                    new CharacterSkillGrowthRecord(characterRecord.getId().intValue(), 1, skill.getId().intValue());
            //スキルポイントを0に初期化
            skillGrowthRecord.setSkillPoint(0);
            try {
                characterSkillGrowthRecordFacade.create(skillGrowthRecord);
            } catch (Exception e) {
                e.printStackTrace();
                //error("キャラクターの技能成長レコードの作製に失敗しました");
                return null;
            }
            charaSkillGrowthList.add(skillGrowthRecord);
        }
        ////////////////////////////////////
        // CharacterAbilityRecordの作成
        //////////////////////////////////////
        List<AbilityMaster> abilityList = abilityMasterFacade.findAll();
        List<CharacterAbilityRecord> charaAbilityList = new ArrayList<CharacterAbilityRecord>();
        for (AbilityMaster ability : abilityList) {
            CharacterAbilityRecord abilityRecord = new CharacterAbilityRecord(characterRecord.getId().intValue(), ability.getId().intValue());
            //各能力値のデフォルトを10にセット
            abilityRecord.setBase(10);
            abilityRecord.setFeatModifier(0);
            abilityRecord.setMiscModifier(0);
            //キャラクター能力レコードを作成
            try {
                characterAbilityRecordFacade.create(abilityRecord);
            } catch (Exception ex) {
                ex.printStackTrace();
                //error("キャラクター能力値(" + ability.getAbilityName() + ")レコードの作成に失敗しました");
                return null;
            }
            charaAbilityList.add(abilityRecord);
        }

        ////////////////////////////////////
        // CharacterSaveRecordの作成
        //////////////////////////////////////
        List<SaveMaster> saveList = saveMasterFacade.findAll();
        List<CharacterSaveRecord> charaSaveList = new ArrayList<CharacterSaveRecord>();
        for (SaveMaster save : saveList) {
            CharacterSaveRecord saveRecord = new CharacterSaveRecord(characterRecord.getId().intValue(), save.getId().intValue());
            //各能力値のデフォルトを10にセット
            saveRecord.setMiscModifier(0);
            //キャラクターセーヴレコードを作成
            try {
                characterSaveRecordFacade.create(saveRecord);
            } catch (Exception ex) {
                ex.printStackTrace();
                //error("キャラクターセーヴレコードの作成に失敗しました");
                return null;
            }
            charaSaveList.add(saveRecord);
        }
        ////////////////////////////////////////////
        // CharacterEquipment の作成
        /////////////////////////////////////////////
        CharacterEquipment equip = new CharacterEquipment(characterRecord.getId().intValue());

        try {
            characterEquipmentFacade.create(equip);
        } catch (Exception ex) {
            ex.printStackTrace();
            //error("キャラクター装備データの作成に失敗しました");
            return null;
        }

        /////////////////////////////////////////////////
        // CharacterGrowthRecord
        ////////////////////////////////////////////////
        List<CharacterGrowthRecord> charaGrowthList = new ArrayList<CharacterGrowthRecord>();
        // 1 レベルの成長レコードを作製
        CharacterGrowthRecord charaGrowth = new CharacterGrowthRecord(characterRecord.getId().intValue(), 1);
        try {
            characterGrowthRecordFacade.create(charaGrowth);
        } catch (Exception e) {
            e.printStackTrace();
            //error("キャラクターの成長レコードの作成に失敗しました");
            return null;
        }
        charaGrowthList.add(charaGrowth);

        // 能力値リスト、セーヴリスト、スキルリスト（その他のみ）、装備、成長リスト、スキル成長リスト
        //をキャラクターレコードにセット。
        characterRecord.setCharacterAbilityRecordList(charaAbilityList);
        characterRecord.setCharacterSaveRecordList(charaSaveList);
        characterRecord.setCharacterSkillRecordList(charaSkillList);
        characterRecord.setCharacterEquipment(equip);
        characterRecord.setCharacterGrowthRecordList(charaGrowthList);
        characterRecord.setCharacterSkillGrowthRecordList(charaSkillGrowthList);

        //反映させる。（実際に character_record テーブルの行が変更されるわけじゃないとおもうのだが・・
        characterRecordFacade.edit(characterRecord);

        //セッションBeanにキャラクターレコードをセット
         getSessionBean().setCharacterRecord(characterRecord);
        return "EditCharacterRecordPageContents";
    }   
    
   /*
     * Check Box をつけようとしていることこ。
     * DataProvider の扱いがうまくいってない。というか、使い方がいまいち。
     *
     */
    public String listSummaryButton_action() {
        return "charasummary";

    }
    private boolean charaSelected;
    /**
     * @return the charaSelected
     */
    public boolean isCharaSelected() {
        CharacterRecord chara = (CharacterRecord)dataTable1.getRowData();
        Set selectedCharas = getSessionBean().getSelectedCharas();
        return selectedCharas != null && selectedCharas.contains(chara);
    }

    /**
     * @param charaSelected the charaSelected to set
     */
    public void setCharaSelected(boolean charaSelected) {
        CharacterRecord chara = (CharacterRecord) dataTable1.getRowData();
        if (chara != null) {
            if (charaSelected) {
                getSessionBean().getSelectedCharas().add(chara);
            } else {
                getSessionBean().getSelectedCharas().remove(chara);
            }
        }
    }

    public String selectAllButton_action() {
        // TODO: Process the button click action. Return value is a navigation
        // case name where null will return to the same page.
        Set<CharacterRecord> charaSet = getSessionBean().getSelectedCharas();
        //Clear first, just in case.
        charaSet.clear();
        for(CharacterRecord chara : getSessionBean().getCharacterRecordList()){
            charaSet.add(chara);
        }
        return null;
    }

    public String releaseAllButton_action() {
        Set charaSet = getSessionBean().getSelectedCharas();
        charaSet.clear();
        return null;
    }
}

