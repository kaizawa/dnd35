/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mbean;

import java.io.Serializable;

/**
 *
 * @author ka78231
 */
public class CharacterRecordSummary implements Serializable {
    
    private String characterName;

    /**
     * Get the value of characterName
     *
     * @return the value of characterName
     */
    public String getCharacterName() {
        return characterName;
    }

    /**
     * Set the value of characterName
     *
     * @param characterName new value of characterName
     */
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    private String playerName;

    /**
     * Get the value of playerName
     *
     * @return the value of playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Set the value of playerName
     *
     * @param playerName new value of playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    private Integer characterLevel;

    /**
     * Get the value of characterLevel
     *
     * @return the value of characterLevel
     */
    public Integer getCharacterLevel() {
        return characterLevel;
    }

    /**
     * Set the value of characterLevel
     *
     * @param characterLevel new value of characterLevel
     */
    public void setCharacterLevel(Integer characterLevel) {
        this.characterLevel = characterLevel;
    }
    private String campaign;

    /**
     * Get the value of campaign
     *
     * @return the value of campaign
     */
    public String getCampaign() {
        return campaign;
    }

    /**
     * Set the value of campaign
     *
     * @param campaign new value of campaign
     */
    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    protected String classList;
    public void setClassList(String classList) {
        this.classList = classList;
    }
    public String getClassList(){
        return classList;
    }

    protected String lastChange;
    public String getLastChange() {
        return lastChange;
    }
    public void setLastChange(String lastChage) {
        this.lastChange = lastChage;
    }  
}
