package core.service;


import org.json.JSONObject;

/**
 * reserved for future use
*/
public final class PlayContext {
	
	public static final String CONTEXT_FIELD_PLAYID = "__playId__";
	
	protected JSONObject underlying;
    
    public PlayContext()
    {
    	this.underlying = new JSONObject();
    }  
	
	public PlayContext(String playContextSerialized) {
		try {
			this.underlying = new JSONObject(playContextSerialized);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PlayContext(JSONObject playContext) {
		this.underlying = playContext;
	}
	

	public void setPlayId(String playId) {
		try {
			this.underlying.put(CONTEXT_FIELD_PLAYID, playId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getPlayId() {
		String playId = null;
		try {
			playId = this.underlying.getString(CONTEXT_FIELD_PLAYID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return playId;
	}
	
	@Override public String toString() {
		return this.underlying.toString();
	}
	
}
