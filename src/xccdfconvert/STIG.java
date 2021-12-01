package xccdfconvert;

import java.util.ArrayList;

public class STIG 
{
	private String vuln_num;
	private String severity;
	private String group_title;
	private String rule_id;
	private String rule_ver;
	private String rule_title;
	private String vuln_discuss;
	private String ia_controls;
	private String fix_text;
	private boolean false_positives;
	private boolean false_negatives;
	private boolean documentable;
	private String mitigations;
	private String potential_impact;
	private String third_party_tools;
	private String mitigation_controls;
	private String responsibility;
	private String security_override;
	private String check_content_ref;
	private String weight;
	private String classification;
	private String stigref;
	private String targetkey;
	private String stig_uuid;
	private ArrayList<String> legacyid;
	private String cci_ref;
	
	private String vkey;
	private String status;
	private ArrayList<String> finding_details;
	
	public STIG() { this(null, null, null, null); }
	public STIG(String vuln_num, String group_title) { this(vuln_num, group_title, null, null); }
	public STIG(String group_title, String vkey, String status, ArrayList<String> details)
	{
			this.vuln_num = (vuln_num == null) ? "" : vuln_num;
			this.group_title = (group_title == null) ? "" : group_title;
			this.vkey = (vkey == null) ? "" : vkey;
			this.status = (status == null) ? "" : status;
			this.finding_details = (details == null) ? new ArrayList<String>() : details;
	}
	
	public String getVulnNum() { return vuln_num; }
	public String getSeverity() { return severity; }
	public String getGroupTitle() { return group_title; }
	public String getRuleId() { return rule_id; }
	public String getRuleVer() { return rule_ver; }
	public String getRuleTitle() { return rule_title; }
	public String getVulnDiscuss() { return vuln_discuss; }
	public String getIaControls() { return ia_controls; }
	public String getFixText() { return fix_text; }
	public boolean getFalsePositives() { return false_positives; }
	public boolean getFalseNegatives() { return false_negatives; }
	public boolean getDocumentable() { return documentable; }
	public String getMitigations() { return mitigations; }
	public String getPotentialImpact() { return potential_impact; }
	public String getThirdPartyTools() { return third_party_tools; }
	public String getMitigationConrols() { return mitigation_controls; }
	public String getResponsibilty() { return responsibility; }
	public String getSecurityOverride() { return security_override; }
	public String getCheckContent() { return check_content_ref; }
	public String getWeight() { return weight; }
	public String getClassification() { return classification; }
	public String getStigRef() { return stigref; }
	public String getTargetkey() { return targetkey; }
	public String getStigUuid() { return stig_uuid; }
	public ArrayList<String> getLegacyId() { return legacyid; }
	public String getCciRef() { return cci_ref; }
	public ArrayList<String> getDetails() { return finding_details; }
	
	public String getStatus() { return status; }
	
	public void setVulnNum(String vuln_num) { this.vuln_num = vuln_num;}
	public void setSeverity(String severity) { this.severity = severity;}
	public void setGroupTitle(String group_title) { this.group_title = group_title;}
	public void setRuleId(String rule_id) { this.rule_id = rule_id; }
	public void setRuleVer(String rule_ver) { this.rule_ver = rule_ver; }
	public void setRuleTitle(String rule_title) { this.rule_title = rule_title; }
	public void setVulnDiscuss(String vuln_discuss) { this.vuln_discuss = vuln_discuss; }
	public void setIaControls(String ia_controls) { this.ia_controls = ia_controls; }
	public void setFixText(String fix_text) { this.fix_text = fix_text; }
	public void setFalsePositives(boolean false_positives) { this.false_positives = false_positives; }
	public void setFalseNegatives(boolean false_negatives) { this.false_negatives = false_negatives; }
	public void setDocumentable(boolean documentable) { this.documentable = documentable; }
	public void setMitigations(String mitigations) { this.mitigations = mitigations; }
	public void setPotentialImpact(String potential_impact) { this.potential_impact = potential_impact; }
	public void setThirdPartyTools(String third_party_tools) { this.third_party_tools = third_party_tools; }
	public void setMitigationConrols(String mitigation_controls) { this.mitigation_controls = mitigation_controls; }
	public void setResponsibilty(String responsibility) { this.responsibility = responsibility; }
	public void setSecurityOverride(String security_override) { this.security_override = security_override; }
	public void setCheckContent(String check_content_ref) { this.check_content_ref = check_content_ref; }
	public void setWeight(String weight) { this.weight = weight; }
	public void setClassification(String classification) { this.classification = classification; }
	public void setStigRefString(String stigref) { this.stigref = stigref; }
	public void setTargetkey(String targetkey) { this.targetkey = targetkey; }
	public void setStigUuid(String stig_uuid) { this.stig_uuid = stig_uuid; }
	public void setLegacyId(ArrayList<String> legacyid) { this.legacyid = legacyid; }
	public void setCciRef(String cci_ref) { this.cci_ref = cci_ref; }

	
	public void setVkey(String vkey) { this.vkey = vkey;}
	public void setStatus(String status) { this.status = status;}
	public void setDetails(ArrayList<String> details) { this.finding_details = details;}
}
