/*
 * EditClassPageContents.java
 *
 * Created on 2009/01/06, 0:04:02
 */
package mbean;

import ejb.BonusRankMasterFacade;
import ejb.ClassMasterFacade;
import ejb.ClassSaveMasterFacade;
import ejb.ClassSkillMasterFacade;
import ejb.DiceMasterFacade;
import ejb.SaveMasterFacade;
import ejb.SkillMasterFacade;
import entity.BonusRankMaster;
import entity.ClassMaster;
import entity.ClassSaveMaster;
import entity.ClassSkillMaster;
import entity.ClassSkillMasterPK;
import entity.SkillMaster;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
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
public class EditClassPageContents extends BaseBean {
    @EJB
    private DiceMasterFacade diceMasterFacade;

    @EJB
    private SaveMasterFacade saveMasterFacade;
    @EJB
    private ClassSaveMasterFacade classSaveMasterFacade;
    @EJB
    private ClassSkillMasterFacade classSkillMasterFacade;
    @EJB
    private SkillMasterFacade skillMasterFacade;
    @EJB
    private ClassMasterFacade classMasterFacade;
    @EJB
    private BonusRankMasterFacade bonusRankMasterFacade;

    private HtmlDataTable classSkillTable = new HtmlDataTable();

    public HtmlDataTable getClassSkillTable() {
        return classSkillTable;
    }

    public void setClassSkillTable(HtmlDataTable hdt) {
        this.classSkillTable = hdt;
    }
    private HtmlDataTable classSkillTable1 = new HtmlDataTable();

    public HtmlDataTable getClassSkillTable1() {
        return classSkillTable1;
    }

    public void setClassSkillTable1(HtmlDataTable hdt) {
        this.classSkillTable1 = hdt;
    }

    /**
     * <p>Construct a new Page bean instance.</p>
     */
    public EditClassPageContents() {
    }

    @PostConstruct
    public void prerender() {

        ClassMaster classMaster =  getSessionBean().getClassMaster();

        if (classMaster.getId() != null) {
            // すでに存在するクラスの場合

            /*
             * このクラスのクラス技能一覧の取得
             */
            List<ClassSkillMaster> tempClassSkillList = classSkillMasterFacade.findByClass(classMaster);
            setClassSkillList(tempClassSkillList);
            /*
             * このクラスのセーブ一覧の取得
             */
            List<ClassSaveMaster> tempClassSaveMasterList = classSaveMasterFacade.findByClass(classMaster);
            setClassSaveMasterList(tempClassSaveMasterList);
            /*
             * このクラスの基本攻撃ボーナスの取得
             */
            if (classMaster.getBaseAttackRankId() != null) {
                setClassEditSelectedBabRank(classMaster.getBaseAttackRankId().getId());
            } else {
                setClassEditSelectedBabRank(null);
            }
            /*
             * セーヴランクの取得
             */
            setClassSaveFortitute(classSaveMasterFacade.findByClassAndSave(classMaster, saveMasterFacade.find(DnDUtil.FORTITUTE)));
            setClassSaveReflex(classSaveMasterFacade.findByClassAndSave(classMaster, saveMasterFacade.find(DnDUtil.REFLEX)));
            setClassSaveWill(classSaveMasterFacade.findByClassAndSave(classMaster, saveMasterFacade.find(DnDUtil.WILL)));
            setClassEditSelectedFortituteRank(getClassSaveFortitute().getRankId().getId());
            setClassEditSelectedReflexRank(getClassSaveReflex().getRankId().getId());
            setClassEditSelectedWillRank(getClassSaveWill().getRankId().getId());
        } else {
            setClassSkillList(new ArrayList<ClassSkillMaster>());
            setClassSaveMasterList(new ArrayList<ClassSaveMaster>());
            setClassEditSelectedBabRank(null);
            setClassEditSelectedFortituteRank(null);
            setClassEditSelectedReflexRank(null);
            setClassEditSelectedWillRank(null);
        }
        /*
         * このクラスのヒットダイスの種類の取得
         */
        if (classMaster.getHitDiceType() != null) {
            setClassEditSelectedHitDiceType(classMaster.getHitDiceType().getId());
        } else {
            setClassEditSelectedHitDiceType(null);
        }
    }

    public String classListButton_action() {
        return "classlist";
    }

    public String saveButton_action() {
        ClassMaster classMaster =  getSessionBean().getClassMaster();

        BonusRankMaster rank = bonusRankMasterFacade.find(getClassEditSelectedBabRank());
        classMaster.setBaseAttackRankId(rank);

        classMaster.setHitDiceType(diceMasterFacade.find(getClassEditSelectedHitDiceType()));


        try {
            if (classMaster.getId() == null) {
                //新規 クラス・マスターを作成
                classMasterFacade.create(classMaster);
                //クラスのセーヴマスター作成
                ClassSaveMaster fortitute = new ClassSaveMaster(classMaster.getId(), DnDUtil.FORTITUTE);
                ClassSaveMaster refrex = new ClassSaveMaster(classMaster.getId(), DnDUtil.REFLEX);
                ClassSaveMaster will = new ClassSaveMaster(classMaster.getId(), DnDUtil.WILL);
                fortitute.setRankId(bonusRankMasterFacade.find(getClassEditSelectedFortituteRank()));
                refrex.setRankId(bonusRankMasterFacade.find(getClassEditSelectedReflexRank()));
                will.setRankId(bonusRankMasterFacade.find(getClassEditSelectedWillRank()));
                classSaveMasterFacade.create(fortitute);
                classSaveMasterFacade.create(refrex);
                classSaveMasterFacade.create(will);
                //クラスの技能マスター作成
                for(ClassSkillMaster classSkill : getClassSkillList()){
                    // クラス技能リストにクラスがあったとしても、まだクラスIDはセットされていないはずなので、ここでセット
                    classSkill.getClassSkillMasterPK().setClassId(classMaster.getId());
                    classSkillMasterFacade.create(classSkill);
                }
                //error("作成しました");
            } else {
                if (getClassSaveFortitute() != null) {
                    classSaveMasterFacade.edit(getClassSaveFortitute());
                }
                if (getClassSaveReflex() != null) {
                    classSaveMasterFacade.edit(classSaveReflex);
                }
                if (getClassSaveReflex() != null) {
                    classSaveMasterFacade.edit(classSaveWill);
                }
                for(ClassSkillMaster classSkillOld : classSkillMasterFacade.findByClass(classMaster)){
                    for (ClassSkillMaster classSkillNew : getClassSkillList()) {
                        if(classSkillNew.getClassSkillMasterPK().getSkillId() == classSkillOld.getClassSkillMasterPK().getSkillId()){
                            continue;
                        }
                    }
                    classSkillMasterFacade.remove(classSkillOld);
                }
                for(ClassSkillMaster classSkillNew : getClassSkillList()){
                    for(ClassSkillMaster classSkillOld : classSkillMasterFacade.findByClass(classMaster)){
                        if(classSkillNew.getClassSkillMasterPK().getSkillId() == classSkillOld.getClassSkillMasterPK().getSkillId()){
                            continue;
                        }                        
                    } 
                    classSkillMasterFacade.create(classSkillNew);
                }
                //更新
                classMasterFacade.edit(classMaster);
                //error("保存しました");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            //error("クラスの保存に失敗しました");
            return null;
        }

        /*
         * 選択メニュー用クラスの配列とリストを再作成し、ApplicationBean のプロパティにセット
         */
        List<ClassMaster> classFindAll = classMasterFacade.findAll();
        List<SelectItem> classList = new ArrayList<SelectItem>();
        //未選択状態
        classList.add(new SelectItem(null, "未選択"));
        for (ClassMaster klass : classFindAll) {
            SelectItem selectItem = new SelectItem();
            selectItem.setValue(klass.getId());
            selectItem.setLabel(klass.getClassName());
            classList.add(selectItem);
        }
        //リストから配列への変換
        SelectItem[] tempClassArray = classList.toArray(new SelectItem[0]);
        //セッションBEANへのセット
        getApplicationBean().setClassArray(tempClassArray);
        getApplicationBean().setClassMasterList(classFindAll);

        return null;
    }
    /*
     * 基本攻撃ボーナス
     */
    private Integer classEditselectedBabRank;

    public Integer getClassEditSelectedBabRank() {
        return classEditselectedBabRank;
    }

    public void setClassEditSelectedBabRank(Integer classEditselectedBabRank) {
        this.classEditselectedBabRank = classEditselectedBabRank;
    }

    //   頑健
    private Integer classEditselectedFortituteRank;

    public Integer getClassEditSelectedFortituteRank() {
        return classEditselectedFortituteRank;
    }

    public void setClassEditSelectedFortituteRank(Integer classEditselectedFortituteRank) {
        this.classEditselectedFortituteRank = classEditselectedFortituteRank;
    }
    //   反応
    private Integer classEditselectedReflexRank;

    public Integer getClassEditSelectedReflexRank() {
        return classEditselectedReflexRank;
    }

    public void setClassEditSelectedReflexRank(Integer classEditselectedReflexRank) {
        this.classEditselectedReflexRank = classEditselectedReflexRank;
    }
    //   意思
    private Integer classEditselectedWillRank;

    public Integer getClassEditSelectedWillRank() {
        return classEditselectedWillRank;
    }

    public void setClassEditSelectedWillRank(Integer classEditselectedWillRank) {
        this.classEditselectedWillRank = classEditselectedWillRank;
    }

    /*
     * クラス技能一覧
     */
    private List<ClassSkillMaster> classSkillList;

    public List<ClassSkillMaster> getClassSkillList() {
        return classSkillList;
    }

    public void setClassSkillList(List<ClassSkillMaster> classSkillList) {
        this.classSkillList = classSkillList;
    }

    /*
     * 表からの技能名
     */
    private String skillNameBySkillId;

    public String getSkillNameBySkillId() {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        if (skill == null) {
            return null;
        }
        return skill.getSkillName();
    }
    /*
     * 表からの技能対応能力値
     */
    private String abilityNameBySkillId;

    public String getAbilityNameBySkillId() {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        if (skill == null) {
            return "技能取得不可。バグ?";
        }
        return skill.getAbilityId().getAbilityName();
    }

    /*
     * クラス
     */
    public boolean isSelectedClassSkill() {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        List<ClassSkillMaster> classSkillListTemp = getClassSkillList();
        if (classSkillListTemp == null) {
            return false;
        }
        for (ClassSkillMaster classSkill : classSkillListTemp) {
            if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId()) {
                return true;
            }
        }
        return false;
    }

    public void setSelectedClassSkill(boolean set) {
        SkillMaster skill = (SkillMaster) classSkillTable.getRowData();
        List<ClassSkillMaster> classSkillListTemp = getClassSkillList();

        //既存のクラス技能のリストを巡る
        for (ClassSkillMaster classSkill : classSkillListTemp) {
                if (classSkill.getClassSkillMasterPK().getSkillId() == skill.getId()) {
                    //リストされている
                    if (set) {
                        //変更無し
                    } else {
                        //クラス技能から除外された
                        classSkillListTemp.remove(classSkill);
                        setClassSkillList(classSkillListTemp);
                    }
                    return;
                }
        }

        //リストになく、且つ今回も未設定
        if (!set) {
            return;
        }

        //ここにくるのはリストに載ってなくて、新たに追加されるとき
        ClassMaster classMaster =  getSessionBean().getClassMaster();
        ClassSkillMaster newClassSkill =  new ClassSkillMaster(new ClassSkillMasterPK());
        newClassSkill.getClassSkillMasterPK().setSkillId(skill.getId());
        if(classMaster.getId() != null )
            newClassSkill.getClassSkillMasterPK().setClassId(classMaster.getId().intValue());
        classSkillListTemp.add(newClassSkill);
        setClassSkillList(classSkillListTemp);
        return;
    }
    
    protected List<ClassSaveMaster> classSaveMasterList;

    public List<ClassSaveMaster> getClassSaveMasterList() {
        return classSaveMasterList;
    }

    public void setClassSaveMasterList(List<ClassSaveMaster> classSaveMasterList) {
        this.classSaveMasterList = classSaveMasterList;
    }
    ClassSaveMaster classSaveFortitute;

    public ClassSaveMaster getClassSaveFortitute() {
        return classSaveFortitute;
    }

    public void setClassSaveFortitute(ClassSaveMaster classSaveFortitute) {
        this.classSaveFortitute = classSaveFortitute;
    }
    ClassSaveMaster classSaveReflex;

    public ClassSaveMaster getClassSaveReflex() {
        return classSaveReflex;
    }

    public void setClassSaveReflex(ClassSaveMaster classSaveReflex) {
        this.classSaveReflex = classSaveReflex;
    }
    ClassSaveMaster classSaveWill;

    public ClassSaveMaster getClassSaveWill() {
        return classSaveWill;
    }

    public void setClassSaveWill(ClassSaveMaster classSaveWill) {
        this.classSaveWill = classSaveWill;
    }

    public void dropdown2_processValueChange(ValueChangeEvent vce) {
    }

    /*
     * ヒットダイス
     */
    private Integer classEditselectedHitDiceType;

    public Integer getClassEditSelectedHitDiceType() {
        return classEditselectedHitDiceType;
    }

    public void setClassEditSelectedHitDiceType(Integer classEditselectedHitDiceType) {
        this.classEditselectedHitDiceType = classEditselectedHitDiceType;
    }

    public String deleteButton_action() {
        ClassMaster classMaster =  getSessionBean().getClassMaster();

        try {
            classMasterFacade.remove(classMaster);
        } catch (Exception ex) {
            ex.printStackTrace();
            //error("削除に失敗しました");
            return null;
        }
        return "classlist";
    }

    // 削除ボタンの表示
    public boolean isDeleteButtonDisabled() {
        return ( getSessionBean().getClassMaster().getId() == null);
    }

    public String cancelButton_action() {
        return "classlist";
    }


}

