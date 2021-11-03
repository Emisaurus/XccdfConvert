package xccdfconvert;

import java.util.ArrayList;

public class STIG 
{
	private String stigid;
	private String vkey;
	private String status;
	private ArrayList<String> details;
	
	public STIG() { this(null, null, null, null); }
	public STIG(String stigid, String vkey, String status, ArrayList<String> details)
	{
			this.stigid = (stigid == null) ? "" : stigid;
			this.vkey = (vkey == null) ? "" : vkey;
			this.status = (status == null) ? "" : status;
			this.details = (details == null) ? new ArrayList<String>() : details;
	}
	
	public String getStigid() { return stigid; }
	public String getVkey() { return vkey; }
	public String getStatus() { return status; }
	public ArrayList<String> getDetails() { return details; }

	public void setStigid(String stigid) { this.stigid = stigid;}
	public void setVkey(String vkey) { this.vkey = vkey;}
	public void setStatus(String status) { this.status = status;}
	public void setDetails(ArrayList<String> details) { this.details = details;}
}
