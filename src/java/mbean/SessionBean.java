/*
 * SessionBean1.java
 *
 * Created on 2008/12/23, 23:35:51
 */
package mbean;

import ejb.RaceMasterFacade;
import ejb.AbilityMasterFacade;
import ejb.ReligionMasterFacade;
import ejb.BonusRankMasterFacade;
import ejb.GenderMasterFacade;
import ejb.AlignmentMasterFacade;
import ejb.CampaignMasterFacade;
import entity.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * <p>Session scope data bean for your application.  Create properties
 *  here to represent cached data that should be made available across
 *  multiple HTTP requests for an individual user.</p>
 *
 * <p>An instance of this class will be created for you automatically,
 * the first time your application evaluates a value binding expression
 * or method binding expression that references a managed bean using
 * this class.</p>
 *
 * @author ka78231
 */
@ManagedBean
@SessionScoped
public class SessionBean {
    
    private Set selectedCharas = new LinkedHashSet();


    @EJB
    private BonusRankMasterFacade bonusRankMasterFacade;
    @EJB
    private ReligionMasterFacade religionMasterFacade;
    @EJB
    private GenderMasterFacade genderMasterFacade;
    @EJB
    private AlignmentMasterFacade alignmentMasterFacade;
    @EJB
    private AbilityMasterFacade abilityMasterFacade;
    @EJB
    private RaceMasterFacade raceMasterFacade;
    @EJB
    private CampaignMasterFacade campaignMasterFacade; 

    boolean loggedIn = true;
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

    public boolean isNotLoggedIn(){
        return !loggedIn;
    }

    protected PlayerMaster playerMaster;

    /**
     * Get the value of playerMaster
     *
     * @return the value of playerMaster
     */
    public PlayerMaster getPlayerMaster() {
        return playerMaster;
    }

    /**
     * Set the value of playerMaster
     *
     * @param playerMaster new value of playerMaster
     */
    public void setPlayerMaster(PlayerMaster playerMaster) {
        this.playerMaster = playerMaster;
    }

    
    /**
     * <p>Construct a new session data bean instance.</p>
     */
    public SessionBean() {
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     *
     * @return reference to the scoped data bean
     */
    protected ApplicationBean getApplicationBean() {
        return getApplicationBean();
    }
    
    // 選択されたキャンペーン
    private Integer characterListSelectedCampaign = null;

    public Integer getCharacterListSelectedCampaign() {
        return characterListSelectedCampaign;
    }

    public void setCharacterListSelectedCampaign(Integer selectedCampaign) {
        this.characterListSelectedCampaign = selectedCampaign;
    }
    ///////////////////////////////
    // キャラクターレコードのリスト
    //////////////////////////////////
    private List<CharacterRecord> characterRecordList;

    public List<CharacterRecord> getCharacterRecordList() {
        return characterRecordList;
    }

    public void setCharacterRecordList(List<CharacterRecord> characterRecords) {
        this.characterRecordList = characterRecords;
    }   
    
    ////////////////////////////////////////////
    // キャラクターレコードのサマリーリスト
    //////////////////////////////////////////////
    private List<CharacterRecordSummary> charaRecordSummary;

    public List<CharacterRecordSummary> getCharacterRecordSummary() {
        return charaRecordSummary;
    }

    public void setCharacterRecordSummary(List<CharacterRecordSummary> charaRecordSummary) {
        this.charaRecordSummary = charaRecordSummary;
    }    
    // キャラクターレコード
    private CharacterRecord characterRecoed;

    public CharacterRecord getCharacterRecord() {
        return characterRecoed;
    }

    public void setCharacterRecord(CharacterRecord characterRecord) {
        this.characterRecoed = characterRecord;
    }    
    // キャラクターレベル
    private Integer characterLevel;

    public Integer getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(Integer characterLevel) {
        this.characterLevel = characterLevel;
    }    
    // 次の経験値
    private Integer nextExperience;

    public Integer getNextExperience() {
        return nextExperience;
    }

    public void setNextExperience(Integer nextExperience) {
        this.nextExperience = nextExperience;
    }    
    // キャラクタの削除ボタンを有効比するかどうかの判定。使ってないか？
    private boolean deleteButtonDisabled;

    public boolean isDeleteButtonDisabled() {
        if (this.characterRecoed.getId() == null) {
            return true;
        } else {
            return false;
        }
    }

    public void setDeleteButtonDisabled(boolean deleteButtonDisabled) {
        this.deleteButtonDisabled = deleteButtonDisabled;
    }    
    // キャンペーンの選択が必要？   使ってる？
    private boolean campaignSelectRequired;

    public boolean isCampaignSelectRequired() {
        return campaignSelectRequired;
    }

    public void setCampaignSelectRequired(boolean campaignSelectRequired) {
        this.campaignSelectRequired = campaignSelectRequired;
    }    
    // キャラクタの成長レコードのリスト
    private List<CharacterGrowthRecord> characterGrowthRecordList;

    public List<CharacterGrowthRecord> getCharacterGrowthRecordList() {
        return characterGrowthRecordList;
    }

    public void setCharacterGrowthRecordList(List<CharacterGrowthRecord> characterGrowthRecordList) {
        this.characterGrowthRecordList = characterGrowthRecordList;
    }   
    // キャラクタの技能成長レコードのリスト
    private List<CharacterSkillGrowthRecord> characterSkillGrowthRecordList;

    public List<CharacterSkillGrowthRecord> getCharacterSkillGrowthRecordList() {
        return characterSkillGrowthRecordList;
    }

    public void setCharacterSkillGrowthRecordList(List<CharacterSkillGrowthRecord> characterSkillGrowthRecordList) {
        this.characterSkillGrowthRecordList = characterSkillGrowthRecordList;
    }    
    //キャンペーンの選択  
    // このプロパティはページ間でやり取りされるので、セッションBeanでよい
    Integer selectedCampaign;

    public Integer getSelectedCampaign() {
        if (this.characterRecoed.getCampaignId() == null) {
            return null;
        }
        return this.characterRecoed.getCampaignId().getId();
    }

    public void setSelectedCampaign(Integer campaignId) {
        if (campaignId == null) {
            this.characterRecoed.setCampaignId(null);
            return;
        }
        CampaignMaster campaign = campaignMasterFacade.find(campaignId);
        this.characterRecoed.setCampaignId(campaign);
    }   
    
    //  選択された属性
    Integer selectedAlignment;
    public Integer getSelectedAlignment() {
        if (this.characterRecoed.getAlignmentId() == null) {
            return null;
        }
        return this.characterRecoed.getAlignmentId().getId();
    }

    public void setSelectedAlignment(Integer selectedAlignment) {
        if (selectedAlignment == null) {
            this.characterRecoed.setAlignmentId(null);
            return;
        }
        AlignmentMaster alignment = alignmentMasterFacade.find(selectedAlignment);
        this.characterRecoed.setAlignmentId(alignment);
    }
    
    //  選択された種族  .. ここにある必要はないか？ EditCharacterRecord の Bean でよいかも
    Integer selectedRace;
    public Integer getSelectedRace() {
        if (this.characterRecoed.getRaceId() == null) {
            return null;
        }
        return this.characterRecoed.getRaceId().getId();
    }
    public void setSelectedRace(Integer selectedRace) {
        if (selectedRace == null) {
            this.characterRecoed.setRaceId(null);
            return;
        }
        RaceMaster race = raceMasterFacade.find(selectedRace);
        this.characterRecoed.setRaceId(race);
    }
    
    //選択された性別
    Integer selectedGender;
    public Integer getSelectedGender() {
        if (this.characterRecoed.getGenderId() == null) {
            return null;
        }
        return this.characterRecoed.getGenderId().getId();
    }
    public void setSelectedGender(Integer selectedGender) {
        if (selectedGender == null) {
            this.characterRecoed.setGenderId(null);
            return;
        }
        GenderMaster gender = genderMasterFacade.find(selectedGender);
        this.characterRecoed.setGenderId(gender);
    }
    
    // 選択された神格
    Integer selectedReligion;
    public Integer getSelectedReligion() {
        if (this.characterRecoed.getReligionId() == null) {
            return null;
        }
        return this.characterRecoed.getReligionId().getId();
    }
    public void setSelectedReligion(Integer selectedReligion) {
        if (selectedReligion == null) {
            this.characterRecoed.setReligionId(null);
            return;
        }
        ReligionMaster religion = religionMasterFacade.find(selectedReligion);
        this.characterRecoed.setReligionId(religion);
    }
    
    // キャラクタ技能レコード のリスト
    private List<CharacterSkillRecord> characterSkillRecordList;
    public List<CharacterSkillRecord> getCharacterSkillRecordList() {
        return characterSkillRecordList;
    }
    public void setCharacterSkillRecordList(List<CharacterSkillRecord> characterSkillRecordList) {
        this.characterSkillRecordList = characterSkillRecordList;
    }    
    
    // キャラクタ編集画面で選択されたレベル値
    private Integer editCharacterPageSelectedLevel;
    public Integer getEditCharacterPageSelectedLevel() {
        return editCharacterPageSelectedLevel;
    }
    public void setEditCharacterPageSelectedLevel(Integer editCharacterSelectedLevel) {
        this.editCharacterPageSelectedLevel = editCharacterSelectedLevel;
    }
    
    // ------------- レベル別技能編集ページ用 レベル一覧 --------------------
    protected SelectItem[] levelArray;
    public SelectItem[] getLevelArray() {
        return levelArray;
    }
    public void setLevelArray(SelectItem[] levelArray) {
        this.levelArray = levelArray;
    }
    
    // キャラクタ成長レコード
    protected CharacterGrowthRecord characterGrowthRecord;
    public CharacterGrowthRecord getCharacterGrowthRecord() {
        return characterGrowthRecord;
    }
    public void setCharacterGrowthRecord(CharacterGrowthRecord characterGrowthRecord) {
        this.characterGrowthRecord = characterGrowthRecord;
    }
    

    //キャラクタ能力値レコードのリスト
    protected List<CharacterAbilityRecord> characterAbilityRecordList;
    public List<CharacterAbilityRecord> getCharacterAbilityRecordList() {
        return characterAbilityRecordList;
    }
    public void setCharacterAbilityRecordList(List<CharacterAbilityRecord> characterAbilityRecordList) {
        this.characterAbilityRecordList = characterAbilityRecordList;
    }
    
    // キャラクターセーブレコードのリスト
    protected List<CharacterSaveRecord> characterSaveRecordList;
    public List<CharacterSaveRecord> getCharacterSaveRecordList() {
        return characterSaveRecordList;
    }
    public void setCharacterSaveRecordList(List<CharacterSaveRecord> characterSaveRecordList) {
        this.characterSaveRecordList = characterSaveRecordList;
    }
    
    //クラス設定ページ用 編集しているクラス
    private ClassMaster classMaster;
    public ClassMaster getClassMaster() {
        return classMaster;
    }
    public void setClassMaster(ClassMaster classMaster) {
        this.classMaster = classMaster;
    }
    
    // ヒットポイント
    protected Integer hitPointTotal;
    public Integer getHitPointTotal() {
        return hitPointTotal;
    }
    public void setHitPointTotal(Integer totalHitPoint) {
        this.hitPointTotal = totalHitPoint;
    }
    
    // 装備データ
    protected CharacterEquipment characterEquipment;
    public CharacterEquipment getCharacterEquipment() {
        return characterEquipment;
    }
    public void setCharacterEquipment(CharacterEquipment characterEquipment) {
        this.characterEquipment = characterEquipment;
    }

    // 種族設定ページ用。
    protected RaceMaster raceMaster;

    /**
     * Get the value of raceMaster
     *
     * @return the value of raceMaster
     */
    public RaceMaster getRaceMaster() {
        return raceMaster;
    }

    /**
     * Set the value of raceMaster
     *
     * @param raceMaster new value of raceMaster
     */
    public void setRaceMaster(RaceMaster raceMaster) {
        this.raceMaster = raceMaster;
    }
    protected boolean mainArm;

    public boolean isMainArm() {
        return mainArm;
    }

    public void setMainArm(boolean mainArm) {
        this.mainArm = mainArm;
    }
    
    /*
     * この valueChangeListener は CharacterListPageContents の PostConstract 
     * の init の「後」に呼ばれるようだ。なので init では 値の変更に気がついていない。。。
     */
    public void campaign_processValueChange(ValueChangeEvent vce) {
        Integer campaign = (Integer) vce.getNewValue();
        setCharacterListSelectedCampaign(campaign);
    }     
    
    public Set getSelectedCharas()
    {
        return selectedCharas;
    }

    public void setSelectedCharas(Set selectedCharas)
    {
        this.selectedCharas = selectedCharas;
    }    
}    
