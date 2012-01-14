/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import ejb.AbilityMasterFacade;
import ejb.CampaignMasterFacade;
import ejb.CharacterAbilityRecordFacade;
import ejb.CharacterEquipmentFacade;
import ejb.CharacterRecordFacade;
import ejb.CharacterSaveRecordFacade;
import ejb.CharacterSkillRecordFacade;
import ejb.SaveMasterFacade;
import ejb.SkillMasterFacade;
import entity.AbilityMaster;
import entity.CampaignMaster;
import entity.CharacterAbilityRecord;
import entity.CharacterEquipment;
import entity.CharacterRecord;
import entity.CharacterSaveRecord;
import entity.CharacterSkillRecord;
import entity.SaveMaster;
import entity.SkillMaster;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * <p>Fragment bean that corresponds to a similarly named JSP page
 * fragment.  This class contains component definitions (and initialization
 * code) for all components that you have defined on this fragment, as well as
 * lifecycle methods and event handlers where you may add behavior
 * to respond to incoming events.</p>
 *
 * @version Header.java
 * @version Created on 2009/03/29, 14:55:38
 * @author ka78231
 */

@ManagedBean
@RequestScoped
public class Header extends BaseBean{
    @EJB
    private CharacterEquipmentFacade characterEquipmentFacade;
    @EJB
    private CharacterSaveRecordFacade characterSaveRecordFacade;
    @EJB
    private SaveMasterFacade saveMasterFacade;
    @EJB
    private CharacterAbilityRecordFacade characterAbilityRecordFacade;
    @EJB
    private AbilityMasterFacade abilityMasterFacade1;
    @EJB
    private AbilityMasterFacade abilityMasterFacade;
    @EJB
    private SkillMasterFacade skillMasterFacade;
    @EJB
    private CharacterSkillRecordFacade characterSkillRecordFacade;
    @EJB
    private CampaignMasterFacade campaignMasterFacade;
    @EJB
    private CharacterRecordFacade characterRecordFacade;


    public Header() {
    }


    public String charaListLink_action() {
        return "charalist";
    }

    public String newCharaLink_action() {
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
        if (getSessionBean().getCharacterListSelectedCampaign() != null) {
            campaign = campaignMasterFacade.find(getSessionBean().getCharacterListSelectedCampaign());
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
        //  CharacterSkillRecord の作成
        ////////////////////////////////
        //キャラクター技能毎のレコードを作成
        List<SkillMaster> skilllist = skillMasterFacade.findAll();
        for (SkillMaster skill : skilllist) {
            try {
                //あらたに CharacterSkillRecord を確保
                CharacterSkillRecord charaSkillRecord = new CharacterSkillRecord(characterRecord.getId().intValue(), skill.getId().intValue());
                charaSkillRecord.setMiscModifier(0);
                characterSkillRecordFacade.create(charaSkillRecord);
            } catch (Exception ex) {
                ex.printStackTrace();
                //error("キャラクター技能レコードの作成に失敗しました");
                return null;
            }

        }
        ////////////////////////////////////
        // CharacterAbilityRecordの作成
        //////////////////////////////////////
        List<AbilityMaster> abilityList = abilityMasterFacade.findAll();
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
                //error("キャラクター能力値レコードの作成に失敗しました");
                return null;
            }

        }

        ////////////////////////////////////
        // CharacterSaveRecordの作成
        //////////////////////////////////////
        List<SaveMaster> saveList = saveMasterFacade.findAll();
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

        //セッションBeanにキャラクターレコードをセット
        //他のリストは EditCharacterRecordPage の中で作成する
        getSessionBean().setCharacterRecord(characterRecord);

        return "editchara";
    }

    public String profileLink_action() {
        if(getSessionBean().loggedIn){
            return "profile";
        } else {
            return "login";
        }
    }

    public String adminLink_action() {
        if(getSessionBean().loggedIn){
            return "admin";
        } else {
            return "login";
        }
    }

    public String loginLink_action() {
        getSessionBean().setLoggedIn(false);
        getSessionBean().setPlayerMaster(null);
        return "login";
    }

    public String logoutLink_action() {
        getSessionBean().setLoggedIn(false);
        getSessionBean().setPlayerMaster(null);
        return "login";
    }

    public String helpLink_action() {
        // TODO:アクションを処理します。戻り値は、
        //  ナビゲーションケース名で、null の場合は同じページに戻ります。
        return null;
    }


}
