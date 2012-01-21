<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Document   : CharacterListPageContents
    Created on : 2009/03/29, 16:26:30
    Author     : ka78231
-->
<jsp:root version="2.1" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:webuijsf="http://www.sun.com/webui/webuijsf">
    <jsp:directive.page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"/>
    <f:view>
        <webuijsf:page id="page1">
            <webuijsf:html id="html1">
                <webuijsf:head id="head1">
                    <webuijsf:link id="link2" url="/resources/graystyle.css"/>
                </webuijsf:head>
                <webuijsf:body id="body1" style="-rave-layout: grid">
                    <webuijsf:form id="form1">
                        <div>
                            <jsp:directive.include file="Header.jspf"/>
                            <h:panelGrid id="contents" style="width: 800px" styleClass="contents">
                                <h:panelGrid id="mainGrid" style="width: 100%">
                                    <h:panelGrid columns="3" id="campFilterGridPanel" style="width: 100%; height: 100%;">
                                        <h:outputText id="outputText1" value="キャンペーンで絞る"/>
                                        <h:selectOneMenu id="dropdown1" onchange="webui.suntheme.common.timeoutSubmitForm(this.form, 'dropdown1');"
                                            style="width: 240px" value="#{SessionBean1.characterListSelectedCampaign}">
                                            <f:selectItems id="dropdown1SelectItems" value="#{ApplicationBean1.campaignArray}"/>
                                        </h:selectOneMenu>
                                        <h:commandButton action="#{CharacterListPageContents.newCharaButton_action}" id="newCharaButton" style="font-size: 12px" value="新キャラクター作成"/>
                                    </h:panelGrid>
                                    <h:panelGrid id="charaListGridPanel" style="width: 100%; height: 100%;" styleClass="tableGrid">
                                        <h:dataTable binding="#{CharacterListPageContents.dataTable1}" columnClasses="" id="dataTable1" style="" styleClass="w"
                                            value="#{SessionBean1.characterRecordList}" var="currentRow">
                                            <h:column id="check">
                                                <h:selectBooleanCheckbox id="checkbox1" value="#{CharacterListPageContents.charaSelected}"/>
                                            </h:column>
                                            <h:column id="column1">
                                                <h:outputText id="outputText2" style="font-size: 14px; font-weight: bold; " value="#{currentRow['characterName']}"/>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText3" value="キャラクター名"/>
                                                </f:facet>
                                            </h:column>
                                            <h:column id="column3">
                                                <h:outputText id="outputText6" style="font-size: 14px; font-weight: bold; " value="#{currentRow['playerName']}"/>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText7" value="プレイヤー"/>
                                                </f:facet>
                                            </h:column>
                                            <h:column id="column5">
                                                <h:commandLink action="#{CharacterListPageContents.charaEditLink_action}" id="charaEditLink"
                                                    style="font-size: 12px; " target="_blank">
                                                    <h:outputText id="charaEditLinkText" value="修正"/>
                                                </h:commandLink>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText9"/>
                                                </f:facet>
                                            </h:column>
                                            <h:column id="column4">
                                                <h:outputText id="outputText8" style="font-size: 14px" value="#{currentRow['characterLevel']}"/>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText10" style="" value="Lv"/>
                                                </f:facet>
                                            </h:column>
                                            <h:column id="column7">
                                                <h:panelGrid id="gridPanel2" style="width: 230px" styleClass="qPrint">
                                                    <h:outputText id="outputText13" value="#{currentRow['classList']}"/>
                                                </h:panelGrid>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText14" value="クラスリスト"/>
                                                </f:facet>
                                            </h:column>
                                            <h:column id="column2">
                                                <h:outputText id="outputText4" style="" value="#{currentRow['campaignName']}"/>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText5" value="キャンペーン"/>
                                                </f:facet>
                                            </h:column>
                                            <h:column id="column8">
                                                <h:outputText id="outputText15" style="" value="#{currentRow['lastChange']}"/>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText16" value="更新日"/>
                                                </f:facet>
                                            </h:column>
                                            <h:column id="column6">
                                                <h:commandLink action="#{CharacterListPageContents.charaViewButton_action}" id="linkAction1"
                                                    style="font-size: 12px;" target="_blank">
                                                    <h:outputText id="linkAction1Text" value="印刷"/>
                                                </h:commandLink>
                                                <f:facet name="header">
                                                    <h:outputText id="outputText11" style="border: 1px solid #000000; "/>
                                                </f:facet>
                                            </h:column>
                                        </h:dataTable>
                                    </h:panelGrid>
                                    <h:panelGrid columns="3" id="gridPanel1" style="height: 100%; width: 100%">
                                        <h:commandButton action="#{CharacterListPageContents.listSummaryButton_action}" id="listSummaryButton" value="選択したキャラを一覧表示"/>
                                        <h:commandButton action="#{CharacterListPageContents.selectAllButton_action}" id="selectAllButton" value="全て選択"/>
                                        <h:commandButton action="#{CharacterListPageContents.releaseAllButton_action}" id="releaseAllButton" value="全て解除"/>
                                    </h:panelGrid>
                                    <h:outputLink id="hyperlink1" value="http://www.whiteboard.ne.jp/~admin2/cgi-bin/dnd/charaDB35.cgi">
                                        <h:outputText id="hyperlink1Text" value="以前のキャラクターシートページ"/>
                                    </h:outputLink>
                                </h:panelGrid>
                            </h:panelGrid>
                            <jsp:directive.include file="Footer.jspf"/>
                        </div>
                    </webuijsf:form>
                </webuijsf:body>
            </webuijsf:html>
        </webuijsf:page>
    </f:view>
</jsp:root>
