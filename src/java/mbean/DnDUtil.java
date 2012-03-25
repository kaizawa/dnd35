/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import entity.*;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ka78231
 */
@ManagedBean
@RequestScoped
public class DnDUtil {
    CharacterRecord charaRecord;
    
    private DnDUtil() {};
    
    public DnDUtil(CharacterRecord charaRecord) {
        this.charaRecord = charaRecord;
    }

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

    public Integer getLevel() {
        Integer exp = charaRecord.getExperience().intValue();
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
     * 改行を<br>に変換し、半角スペースを &nbsp に変換
     */
    public static String textToHtml(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        StringCharacterIterator sci = new StringCharacterIterator(str);
        for (char c = sci.current(); c != StringCharacterIterator.DONE; c = sci.next()) {
            if (c == '\n') {
                sb.append("<br>");
            } else if(c == ' ') {
                sb.append("&nbsp;");
            } else {                
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public Integer getCharacterLevel() {
        int lv = 0;
        int val = 0;
        do {
            lv++;
            val += lv * 1000;
        } while (charaRecord.getExperience().intValue() >= val);
        return new Integer(lv);
    }

    public static Integer getExpForNextLevel() {
        int lv = 0;
        int val = 0;
        int exp = getExpForNextLevel().intValue();
        do {
            lv++;
            val += lv * 1000;
        } while (exp >= val);
        return new Integer(val);
    }

    public String getLastChange() {
        Date date = charaRecord.getSaveTime();
        if (date == null) {
            return "--\u672A\u4FDD\u5B58--";
        } else {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
            return fmt.format(date);
        }
    }

    public String getClassList() {
        List growthList = charaRecord.getCharacterGrowthRecordList();
        Map classMap = new HashMap();
        int i = 1;
        Iterator it = growthList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
            if (i > getCharacterLevel().intValue()) {
                break;
            }
            int newVal = 0;
            ClassMaster klass = growth.getClassId();
            if (classMap.get(klass) == null) {
                newVal = 1;
            } else {
                newVal = ((Integer) classMap.get(klass)).intValue() + 1;
            }
            classMap.put(klass, Integer.valueOf(newVal));
            i++;
        } while (true);
        String classList = "";
        for (Iterator iter = classMap.entrySet().iterator(); iter.hasNext();) {
            java.util.Map.Entry mapEntry = (java.util.Map.Entry) iter.next();
            ClassMaster klass = (ClassMaster) mapEntry.getKey();
            String line = (new StringBuilder()).append(klass != null ? klass.getClassName() : "\u672A\u8A2D\u5B9A").append(mapEntry.getValue()).append(" Lv").append(", ").toString();
            classList = (new StringBuilder()).append(classList).append(line).toString();
        }

        return classList;
    }

    public String getCampaignName() {
        return charaRecord.getCampaignId() != null ? charaRecord.getCampaignId().getCampaignName() : "\u672A\u8A2D\u5B9A";
    }


    public Integer getInitiative() {
        return Integer.valueOf(getInitiativeAbilityModifier().intValue() + charaRecord.getInitiativeFeatModifier().intValue() 
                + charaRecord.getInitiativeMiscModifier().intValue());
    }

    public Integer getInitiativeAbilityModifier() {
        return getAbilityModifierById(2);
    }

    public Integer getAbilityModifierById(int ability) {
        return Integer.valueOf(getAbilityTotalById(ability).intValue() / 2 - 5);
    }

    public Integer getAbilityTotalById(int id) {
        return Integer.valueOf(getAbilityBaseById(id).intValue() + getAbilityRaceModifierById(id).intValue() + getAbilityFeatModifierById(id).intValue() + getAbilityMiscModifierById(id).intValue() + getAbilityLevelModifierById(id).intValue());
    }

    public Integer getAbilityBaseById(int id) {
        List abilityList = charaRecord.getCharacterAbilityRecordList();
        CharacterAbilityRecord ability = (CharacterAbilityRecord) abilityList.get(id - 1);
        Integer result = ability.getBase();
        if (result == null) {
            return Integer.valueOf(0);
        } else {
            return result;
        }
    }

    public Integer getAbilityRaceModifierById(int id) {
        RaceMaster race = charaRecord.getRaceId();
        if (race == null) {
            return Integer.valueOf(0);
        }
        List raceAbilityList = race.getRaceAbilityMasterList();
        if (((RaceAbilityMaster) raceAbilityList.get(id - 1)).getModifier() == null) {
            return Integer.valueOf(0);
        } else {
            return ((RaceAbilityMaster) raceAbilityList.get(id - 1)).getModifier();
        }
    }

    public Integer getAbilityFeatModifierById(int id) {
        Integer result = ((CharacterAbilityRecord) charaRecord.getCharacterAbilityRecordList().get(id - 1)).getFeatModifier();
        if (result == null) {
            return Integer.valueOf(0);
        } else {
            return result;
        }
    }

    public Integer getAbilityMiscModifierById(int id) {
        Integer result = ((CharacterAbilityRecord) charaRecord.getCharacterAbilityRecordList().get(id - 1)).getMiscModifier();
        if (result == null) {
            return Integer.valueOf(0);
        } else {
            return result;
        }
    }

    public Integer getAbilityLevelModifierById(int id) {
        int modifier = 0;
        List growthList = charaRecord.getCharacterGrowthRecordList();
        Iterator it = growthList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel().intValue()) {
                break;
            }
            Integer enhancedAb = growth.getAbilityEnhancement();
            if (enhancedAb != null && id == enhancedAb.intValue()) {
                modifier++;
            }
        } while (true);
        return Integer.valueOf(modifier);
    }

    public SaveMaster getSaveMasterById(int saveId) {
        SaveMaster save = null;
        Iterator it = charaRecord.getCharacterSaveRecordList().iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterSaveRecord saveRecord = (CharacterSaveRecord) it.next();
            if (saveRecord.getSaveMaster().getId().intValue() == saveId) {
                save = saveRecord.getSaveMaster();
            }
        } while (true);
        return save;
    }

    public Integer getSaveTotalById(int saveId) {
        return Integer.valueOf(getSaveAbilityModifierById(saveId).intValue() + getSaveClassBonusById(saveId).intValue() + getSaveMiscModifierById(saveId).intValue() + getSaveRaceModifierById(saveId).intValue());
    }

    public Integer getSaveAbilityModifierById(int saveId) {
        SaveMaster save = getSaveMasterById(saveId);
        if (save == null) {
            return Integer.valueOf(0);
        } else {
            return getAbilityModifierById(save.getAbilityId().getId().intValue());
        }
    }

    public Integer getSaveClassBonusById(int saveId) {
        int bonus = 0;
        SaveMaster save = getSaveMasterById(saveId);
        List growthList = charaRecord.getCharacterGrowthRecordList();
        Map classMap = new HashMap();
        Iterator it = growthList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel().intValue()) {
                break;
            }
            if (growth.getClassId() != null) {
                ClassMaster klass = growth.getClassId();
                if (classMap.get(klass) == null) {
                    classMap.put(klass, Integer.valueOf(1));
                } else {
                    classMap.put(klass, Integer.valueOf(((Integer) classMap.get(klass)).intValue() + 1));
                }
            }
        } while (true);
        it = classMap.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            java.util.Map.Entry classEntry = (java.util.Map.Entry) it.next();
            ClassMaster klass = (ClassMaster) classEntry.getKey();
            Integer lv = (Integer) classEntry.getValue();
            BonusRankMaster rank = null;
            Iterator its = klass.getClassSaveMasterList().iterator();
            do {
                if (!its.hasNext()) {
                    break;
                }
                ClassSaveMaster classSave = (ClassSaveMaster) its.next();
                if (classSave.getSaveMaster() == save) {
                    rank = classSave.getRankId();
                }
            } while (true);
            if (rank == null) {
                return Integer.valueOf(0);
            }
            switch (rank.getId().intValue()) {
                case 1: // '\001'
                    bonus += lv.intValue() / 2 + 2;
                    break;

                case 2: // '\002'
                    bonus += lv.intValue();
                    break;

                case 3: // '\003'
                    bonus += lv.intValue() / 3;
                    break;

                default:
                    bonus = 0;
                    break;
            }
        } while (true);
        return Integer.valueOf(bonus);
    }

    public Integer getSaveMiscModifierById(int saveId) {
        CharacterSaveRecord saveRecord = (CharacterSaveRecord) charaRecord.getCharacterSaveRecordList().get(saveId - 1);
        Integer result = saveRecord.getMiscModifier();
        if (result == null) {
            return Integer.valueOf(0);
        } else {
            return result;
        }
    }

    public Integer getSaveRaceModifierById(int saveId) {
        label0:
        {
            if (charaRecord.getRaceId() == null) {
                break label0;
            }
            Collection raceSaveCollection = charaRecord.getRaceId().getRaceSaveMasterCollection();
            Iterator it = raceSaveCollection.iterator();
            RaceSaveMaster raceSave;
            do {
                if (!it.hasNext()) {
                    break label0;
                }
                raceSave = (RaceSaveMaster) it.next();
            } while (raceSave.getRaceSaveMasterPK().getSaveId() != saveId || raceSave.getModifier() == null);
            return raceSave.getModifier();
        }
        return Integer.valueOf(0);
    }

    public Integer getAcNormal() {
        return Integer.valueOf(10 + getAcAbilityModifier().intValue() + (getAcRaceModifier() != null ? getAcRaceModifier().intValue() : 0) + (charaRecord.getAcArmor() != null ? charaRecord.getAcArmor().intValue() : 0) + (charaRecord.getAcShield() != null ? charaRecord.getAcShield().intValue() : 0) + (charaRecord.getAcMiscMod() != null ? charaRecord.getAcMiscMod().intValue() : 0));
    }

    public Integer getAcTouch() {
        return Integer.valueOf(10 + getAcAbilityModifier().intValue() + (getAcRaceModifier() != null ? getAcRaceModifier().intValue() : 0) + (charaRecord.getAcMiscMod() != null ? charaRecord.getAcMiscMod().intValue() : 0));
    }

    public Integer getAcFlatFooted() {
        return Integer.valueOf(10 + (getAcRaceModifier() != null ? getAcRaceModifier().intValue() : 0) + (charaRecord.getAcArmor() != null ? charaRecord.getAcArmor().intValue() : 0) + (charaRecord.getAcShield() != null ? charaRecord.getAcShield().intValue() : 0) + (charaRecord.getAcMiscMod() != null ? charaRecord.getAcMiscMod().intValue() : 0));
    }

    public Integer getAcAbilityModifier() {
        int bonus = 0;
        CharacterEquipment equip = charaRecord.getCharacterEquipment();
        bonus = getAbilityModifierById(2).intValue();
        if (equip.getDexAcArmorLimit() != null && equip.getDexAcArmorLimit().intValue() < bonus) {
            bonus = equip.getDexAcArmorLimit().intValue();
        }
        if (equip.getDexAcShieldLimit() != null && equip.getDexAcShieldLimit().intValue() < bonus) {
            bonus = equip.getDexAcShieldLimit().intValue();
        }
        return Integer.valueOf(bonus);
    }

    public Integer getAcRaceModifier() {
        RaceMaster race = charaRecord.getRaceId();
        if (race == null) {
            return Integer.valueOf(0);
        } else {
            return race.getSizeId().getAcModifier();
        }
    }

    public Integer getHitPoint() {
        List growthList = charaRecord.getCharacterGrowthRecordList();
        int total = 0;
        int bonus = getAbilityModifierById(3).intValue();
        Iterator it = growthList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel().intValue()) {
                break;
            }
            if (growth.getHitPoint() != null) {
                total += growth.getHitPoint().intValue() + bonus;
            }
        } while (true);
        return Integer.valueOf(total);
    }

    public SkillMaster getSkillMasterById(int id) {
        List charaSkillGrowthList = charaRecord.getCharacterSkillGrowthRecordList();
        for (Iterator it = charaSkillGrowthList.iterator(); it.hasNext();) {
            CharacterSkillGrowthRecord skillGrowthRecord = (CharacterSkillGrowthRecord) it.next();
            SkillMaster skill = skillGrowthRecord.getSkillMaster();
            if (skill.getId() != null && skill.getId().intValue() == id) {
                return skillGrowthRecord.getSkillMaster();
            }
        }

        return null;
    }

    public Integer getSkillAbilityModifierById(int id) {
        SkillMaster skill = getSkillMasterById(id);
        if (skill == null) {
            return Integer.valueOf(0);
        } else {
            return getAbilityModifierById(skill.getAbilityId().getId().intValue());
        }
    }

    public String getskillAbilityNameById(int id) {
        SkillMaster skill = getSkillMasterById(id);
        return skill.getAbilityId().getAbilityName();
    }

    public String getskillAbilityShortNameById(int id) {
        SkillMaster skill = getSkillMasterById(id);
        String name = skill.getAbilityId().getAbilityName();
        return name.substring(0, 1);
    }

    public Integer getSkillTotalPointById(int id) {
        SkillMaster skill = getSkillMasterById(id);
        int point = 0;
        List skillGrowthlist = charaRecord.getCharacterSkillGrowthRecordList();
        Iterator it = skillGrowthlist.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterSkillGrowthRecord skillGrowth = (CharacterSkillGrowthRecord) it.next();
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel() > getCharacterLevel().intValue()) {
                break;
            }
            if (skillGrowth.getSkillMaster() == skill) {
                point += skillGrowth.getSkillPoint().intValue();
            }
        } while (true);
        return Integer.valueOf(point);
    }

    public Integer getSkillTotalCheckModifierById(int id) {
        return Integer.valueOf(getSkillAbilityModifierById(id).intValue() + getSkillTotalRankById(id).intValue() + getskillMiscModifierById(id).intValue() + getskillArmorModifierById(id).intValue() + getskillSynergyModifierById(id).intValue());
    }

    public Integer getskillMiscModifierById(int id) {
        List skillRecordList = charaRecord.getCharacterSkillRecordList();
        Integer result = ((CharacterSkillRecord) skillRecordList.get(id - 1)).getMiscModifier();
        if (result == null) {
            return Integer.valueOf(0);
        } else {
            return result;
        }
    }

    public Float getskillRankByLevelAndSkill(CharacterGrowthRecord growth, SkillMaster skill) {
        int point = 0;
        float rank = 0.0F;
        ClassMaster klass = growth.getClassId();
        List skillgrowthlist = charaRecord.getCharacterSkillGrowthRecordList();
        Iterator it = skillgrowthlist.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterSkillGrowthRecord skillGrowth = (CharacterSkillGrowthRecord) it.next();
            Integer level = Integer.valueOf(skillGrowth.getCharacterSkillGrowthRecordPK().getCharacterLevel());
            if (level.intValue() > getCharacterLevel().intValue()) {
                break;
            }
            if (skillGrowth.getCharacterSkillGrowthRecordPK().getSkillId() != skill.getId().intValue() || level.intValue() != growth.getCharacterGrowthRecordPK().getCharacterLevel()) {
                continue;
            }
            point = skillGrowth.getSkillPoint().intValue();
            break;
        } while (true);
        if (point == 0) {
            return Float.valueOf(0.0F);
        }
        if (isClassSkillByLevelAndSkill(growth, skill)) {
            rank = point;
        } else {
            rank = (new Float(point)).floatValue() / 2.0F;
        }
        return Float.valueOf(rank);
    }

    public Integer getSkillTotalRankById(int skillId) {
        Float totalRank = Float.valueOf(0.0F);
        SkillMaster skill = getSkillMasterById(skillId);
        List growthlist = charaRecord.getCharacterGrowthRecordList();
        Iterator it = growthlist.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
            if (growth.getCharacterGrowthRecordPK().getCharacterLevel() > getCharacterLevel().intValue()) {
                break;
            }
            totalRank = Float.valueOf(totalRank.floatValue() + getskillRankByLevelAndSkill(growth, skill).floatValue());
        } while (true);
        return Integer.valueOf(totalRank.intValue());
    }

    public Integer getskillArmorModifierById(int skillId) {
        SkillMaster skill = getSkillMasterById(skillId);
        int result = 0;
        if (skill.getArmorCheck() == 0) {
            return Integer.valueOf(0);
        }
        CharacterEquipment equip = charaRecord.getCharacterEquipment();
        if (equip.getSkillArmorMod() != null) {
            result += equip.getSkillArmorMod().intValue();
        }
        if (equip.getSkillShieldMod() != null) {
            result += equip.getSkillShieldMod().intValue();
        }
        if (skillId == 22) {
            result *= 2;
        }
        return Integer.valueOf(result);
    }

    public Integer getskillSynergyModifierById(int skillId) {
        SkillMaster skill = getSkillMasterById(skillId);
        int result = 0;
        List synergyList = skill.getSkillSynergyMasterList();
        Iterator it = synergyList.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            SkillSynergyMaster synergy = (SkillSynergyMaster) it.next();
            int affectedSkillId = synergy.getSkillSynergyMasterPK().getAffectedBy();
            int affectedSkillRank = getSkillTotalRankById(affectedSkillId).intValue();
            if (affectedSkillRank > 0) {
                result += (affectedSkillRank / 5) * 2;
            }
        } while (true);
        return Integer.valueOf(result);
    }

    public boolean isClassSkillByLevelAndSkill(CharacterGrowthRecord growthLevel, SkillMaster skill) {
        label0:
        {
            List growthList = charaRecord.getCharacterGrowthRecordList();
            Iterator it = growthList.iterator();
            label1:
            do {
                if (it.hasNext()) {
                    CharacterGrowthRecord growth = (CharacterGrowthRecord) it.next();
                    if (growth.getCharacterGrowthRecordPK().getCharacterLevel() <= growthLevel.getCharacterGrowthRecordPK().getCharacterLevel()) {
                        ClassMaster klass = growth.getClassId();
                        if (klass == null) {
                            continue;
                        }
                        List classSkillList = klass.getClassSkillMasterList();
                        Iterator it2 = classSkillList.iterator();
                        ClassSkillMaster classSkill;
                        do {
                            if (!it2.hasNext()) {
                                continue label1;
                            }
                            classSkill = (ClassSkillMaster) it2.next();
                        } while (classSkill.getClassSkillMasterPK().getSkillId() != skill.getId().intValue());
                        break;
                    }
                }
                break label0;
            } while (true);
            return true;
        }
        return false;
    }    
    
    private String attackDescriptionWithBR;    
    
    public String getAttackDescriptionWithBR()
    {
        return textToHtml(charaRecord.getAttackDescription());
    }

    public void setAttackDescriptionWithBR(String attackDescriptionWithBR) {
        this.attackDescriptionWithBR = attackDescriptionWithBR;
    }
    
    public static String getAbilityShortName(AbilityMaster ability){
        String name = ability.getAbilityName();
        return (new StringBuilder()).append("\u3010").append(name.substring(0, 1)).append("\u3011").toString();
    }    
}
