/*
 * EditClassPage.java
 *
 * Created on 2009/01/06, 0:04:02
 */
package mbean;


import ejb.RaceMasterFacade;
import ejb.SizeMasterFacade;
import ejb.SaveMasterFacade;
import ejb.RaceAbilityMasterFacade;
import ejb.RaceSaveMasterFacade;
import entity.RaceAbilityMaster;
import entity.RaceMaster;
import entity.RaceSaveMaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class EditRacePage extends BaseBean {

    @EJB
    private SizeMasterFacade sizeMasterFacade;
    @EJB
    private RaceSaveMasterFacade raceSaveMasterFacade;
    @EJB
    private RaceAbilityMasterFacade raceAbilityMasterFacade;
    @EJB
    private RaceMasterFacade raceMasterFacade;
    @EJB
    private SaveMasterFacade saveMasterFacade;


    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public EditRacePage() {
    }
  
    @PostConstruct
    public void init() {
        RaceMaster race =  getSessionBean().getRaceMaster();

        setAbilityCollection(race.getRaceAbilityMasterList());
        setSaveCollection(race.getRaceSaveMasterCollection());

        if (race.getId() != null) {
            // すでに存在するクラスの場合
            /*
             * この種族のセーブ調整値一覧の取得
             */
            List<RaceSaveMaster> tempRaceSaveList = raceSaveMasterFacade.findByRace(race);
            setRaceSaveList(tempRaceSaveList);

            /*
             * この種族の能力値調整値の取得
             */
            List<RaceAbilityMaster> tempRaceAbilityList = raceAbilityMasterFacade.findByRace(race);
            setRaceAbilityList(tempRaceAbilityList);
        } else {
            // 新しいクラス
            setRaceSaveList(new ArrayList<RaceSaveMaster>());
            setRaceAbilityList(new ArrayList<RaceAbilityMaster>());
        }

        /*
         * この種族のサイズの取得
         */
        if (race.getSizeId() != null) {
            Integer sizeid = race.getSizeId().getId();
            setSelectedSize(sizeid);
        } else {
            setSelectedSize(null);
        }
        /*
         * 能力値をセット
         */
        if (getAbilityCollection() != null) {
            for (RaceAbilityMaster ability : getAbilityCollection()) {
                int abId = ability.getAbilityMaster().getId();
                switch (abId) {
                    case DnDUtil.STR:
                        setStrengthModifier(ability.getModifier());
                        break;
                    case DnDUtil.DEX:
                        setDexterityModifier(ability.getModifier());
                        break;
                    case DnDUtil.CON:
                        setConstitutionModifier(ability.getModifier());
                        break;
                    case DnDUtil.WIS:
                        setWisdomModifier(ability.getModifier());
                        break;
                    case DnDUtil.INT:
                        setIntelligenceModifier(ability.getModifier());
                        break;
                    case DnDUtil.CHA:
                        setCharismaModifier(ability.getModifier());
                        break;
                }
            }
        }
        /*
         * セーブをセット
         */
        if (getSaveCollection() != null) {
            for (RaceSaveMaster save : getSaveCollection()) {
                int saveId = save.getSaveMaster().getId();
                switch (saveId) {
                    case DnDUtil.FORTITUTE:
                        setFortitudeModifier(save.getModifier());
                        break;
                    case DnDUtil.REFLEX:
                        setReflexModifier(save.getModifier());
                        break;
                    case DnDUtil.WILL:
                        setWillModifier(save.getModifier());
                        break;
                }
            }
        }

        /*
         * 移動速度をセット
         */
        setSpeed(race.getSpeed());
    }

    public String saveButton_action() {
        RaceMaster race =  getSessionBean().getRaceMaster();
        Integer sizeid = getSelectedSize();

        try {
            if (race.getId() == null) {
                //新規 種族マスターを作成
                race.setSizeId(sizeMasterFacade.find(sizeid));
                race.setSpeed(getSpeed());
                raceMasterFacade.create(race);

                //種族のセーヴマスター作成
                RaceSaveMaster fortitute = new RaceSaveMaster(race.getId(), DnDUtil.FORTITUTE);
                RaceSaveMaster refrex = new RaceSaveMaster(race.getId(), DnDUtil.REFLEX);
                RaceSaveMaster will = new RaceSaveMaster(race.getId(), DnDUtil.WILL);
                fortitute.setModifier(getFortitudeModifier());
                refrex.setModifier(getReflexModifier());
                will.setModifier(getWillModifier());
                raceSaveMasterFacade.create(fortitute);
                raceSaveMasterFacade.create(refrex);
                raceSaveMasterFacade.create(will);

                //種族の能力値マスター作成
                RaceAbilityMaster str = new RaceAbilityMaster(race.getId(), DnDUtil.STR);
                RaceAbilityMaster dex = new RaceAbilityMaster(race.getId(), DnDUtil.DEX);
                RaceAbilityMaster con = new RaceAbilityMaster(race.getId(), DnDUtil.CON);
                RaceAbilityMaster inte = new RaceAbilityMaster(race.getId(), DnDUtil.INT);
                RaceAbilityMaster wis = new RaceAbilityMaster(race.getId(), DnDUtil.WIS);
                RaceAbilityMaster charisma = new RaceAbilityMaster(race.getId(), DnDUtil.CHA);
                str.setModifier(getStrengthModifier());
                dex.setModifier(getDexterityModifier());
                con.setModifier(getConstitutionModifier());
                inte.setModifier(getIntelligenceModifier());
                wis.setModifier(getWisdomModifier());
                charisma.setModifier(getCharismaModifier());
                raceAbilityMasterFacade.create(str);
                raceAbilityMasterFacade.create(dex);
                raceAbilityMasterFacade.create(con);
                raceAbilityMasterFacade.create(inte);
                raceAbilityMasterFacade.create(wis);
                raceAbilityMasterFacade.create(charisma);

                context.addMessage("contents:contentGrid:label1", new FacesMessage(("作成しました")));
            } else {
                //更新
                for (RaceSaveMaster saveMaster : getSaveCollection()) {
                    int saveId = saveMaster.getRaceSaveMasterPK().getSaveId();
                    switch (saveId) {
                        case DnDUtil.FORTITUTE:
                            saveMaster.setModifier(getFortitudeModifier());
                            raceSaveMasterFacade.edit(saveMaster);
                            break;
                        case DnDUtil.REFLEX:
                            saveMaster.setModifier(getReflexModifier());
                            raceSaveMasterFacade.edit(saveMaster);
                            break;
                        case DnDUtil.WILL:
                            saveMaster.setModifier(getWillModifier());
                            raceSaveMasterFacade.edit(saveMaster);
                            break;
                    }
                }
                for (RaceAbilityMaster abilityMaster : getAbilityCollection()) {
                    int abId = abilityMaster.getRaceAbilityMasterPK().getAbilityId();
                    switch (abId) {
                        case DnDUtil.STR:
                            abilityMaster.setModifier(getStrengthModifier());
                            raceAbilityMasterFacade.edit(abilityMaster);
                            break;
                        case DnDUtil.DEX:
                            abilityMaster.setModifier(getDexterityModifier());
                            raceAbilityMasterFacade.edit(abilityMaster);
                            break;
                        case DnDUtil.CON:
                            abilityMaster.setModifier(getConstitutionModifier());
                            raceAbilityMasterFacade.edit(abilityMaster);
                            break;
                        case DnDUtil.INT:
                            abilityMaster.setModifier(getIntelligenceModifier());
                            raceAbilityMasterFacade.edit(abilityMaster);
                            break;
                        case DnDUtil.WIS:
                            abilityMaster.setModifier(getWisdomModifier());
                            raceAbilityMasterFacade.edit(abilityMaster);
                            break;
                        case DnDUtil.CHA:
                            abilityMaster.setModifier(getCharismaModifier());
                            raceAbilityMasterFacade.edit(abilityMaster);
                            break;
                    }
                }
                race.setSizeId(sizeMasterFacade.find(sizeid));
                race.setSpeed(getSpeed());
                raceMasterFacade.edit(race);
                context.addMessage("contents:contentGrid:label1", new FacesMessage(("保存しました")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            context.addMessage("contents:contentGrid:label1", new FacesMessage(("種族の保存に失敗しました")));
            return null;
        }

        /*
         * 選択メニュー用クラスの配列とリストを再作成し、ApplicationBean のプロパティにセット
         */
        List<RaceMaster> raceFindAll = raceMasterFacade.findAll();
        List<SelectItem> raceList = new ArrayList<SelectItem>();
        //未選択状態
        raceList.add(new SelectItem(null, "未選択"));
        for (RaceMaster tempRace : raceFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(tempRace.getId());
            selectItem.setLabel(tempRace.getRaceName());
            raceList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempRaceArray = raceList.toArray(new SelectItem[0]);
        //セッションBEANへのセット
        getApplicationBean().setRaceArray(tempRaceArray);
        getApplicationBean().setRaceMasterList(raceFindAll);

        return null;
    }

    public void dropdown2_processValueChange(ValueChangeEvent vce) {
    }

    public String deleteButton_action() {
        RaceMaster raceMaster =  getSessionBean().getRaceMaster();

        try {
            raceMasterFacade.remove(raceMaster);
        } catch (Exception ex) {
            ex.printStackTrace();
            context.addMessage("contents:contentGrid:label1", new FacesMessage(("種族の削除に失敗しました。利用中の可能性があります。")));
            return null;
        }
        return "RaceListPage";
    }

    // 削除ボタンの表示
    public boolean isDeleteButtonDisabled() {
        return ( getSessionBean().getRaceMaster().getId() == null);
    }

    public String cancelButton_action() {
        return "RaceListPage";
    }

    public Integer getCharismaModifier() {
        return CharismaModifier;
    }

    public void setCharismaModifier(Integer CharismaModifier) {
        this.CharismaModifier = CharismaModifier;
    }

    public Integer getConstitutionModifier() {
        return ConstitutionModifier;
    }

    public void setConstitutionModifier(Integer ConstitutionModifier) {
        this.ConstitutionModifier = ConstitutionModifier;
    }

    public Integer getIntelligenceModifier() {
        return IntelligenceModifier;
    }

    public void setIntelligenceModifier(Integer IntelligenceModifier) {
        this.IntelligenceModifier = IntelligenceModifier;
    }

    public Integer getStrengthModifier() {
        return StrengthModifier;
    }

    public void setStrengthModifier(Integer StrengthModifier) {
        this.StrengthModifier = StrengthModifier;
    }

    public Integer getWisdomModifier() {
        return WisdomModifier;
    }

    public void setWisdomModifier(Integer WisdomModifier) {
        this.WisdomModifier = WisdomModifier;
    }

    public Integer getReflexModifier() {
        return reflexModifier;
    }

    public void setReflexModifier(Integer reflexModifier) {
        this.reflexModifier = reflexModifier;
    }

    public Integer getWillModifier() {
        return willModifier;
    }

    public void setWillModifier(Integer willModifier) {
        this.willModifier = willModifier;
    }

    public Integer getDexterityModifier() {
        return DexterityModifier;
    }

    public void setDexterityModifier(Integer DexterityModifier) {
        this.DexterityModifier = DexterityModifier;
    }

    public Integer getFortitudeModifier() {
        return fortitudeModifier;
    }

    public void setFortitudeModifier(Integer fortitudeModifier) {
        this.fortitudeModifier = fortitudeModifier;
    }
    protected Integer fortitudeModifier;
    protected Integer reflexModifier;
    protected Integer willModifier;
    protected Integer StrengthModifier;
    protected Integer DexterityModifier;
    protected Integer ConstitutionModifier;
    protected Integer IntelligenceModifier;
    protected Integer WisdomModifier;
    protected Integer CharismaModifier;
    protected List<RaceSaveMaster> raceSaveList;

    public List<RaceSaveMaster> getRaceSaveList() {
        return raceSaveList;
    }

    public void setRaceSaveList(List<RaceSaveMaster> raceSaveList) {
        this.raceSaveList = raceSaveList;
    }
    protected List<RaceAbilityMaster> raceAbilityList;

    public List<RaceAbilityMaster> getRaceAbilityList() {
        return raceAbilityList;
    }

    public void setRaceAbilityList(List<RaceAbilityMaster> raceAbilityList) {
        this.raceAbilityList = raceAbilityList;
    }
    protected Integer selectedSize;

    public Integer getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(Integer selectedSize) {
        this.selectedSize = selectedSize;
    }
    protected Integer speed;

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    Collection<RaceAbilityMaster> abilityCollection;

    public Collection<RaceAbilityMaster> getAbilityCollection() {
        return abilityCollection;
    }

    public void setAbilityCollection(Collection<RaceAbilityMaster> abilityCollection) {
        this.abilityCollection = abilityCollection;
    }

    public Collection<RaceSaveMaster> getSaveCollection() {
        return saveCollection;
    }

    public void setSaveCollection(Collection<RaceSaveMaster> saveCollection) {
        this.saveCollection = saveCollection;
    }
    Collection<RaceSaveMaster> saveCollection;
}

