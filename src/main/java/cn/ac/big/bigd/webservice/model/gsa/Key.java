/**
 * CRA class
 */
package cn.ac.big.bigd.webservice.model.gsa;

/**
 * @author chenx
 * @CreateDate: 2018-07-20
 * @version 1.0
 */
public class Key {
	private Integer key_id;
    private String database;
	private String key;

	public Integer getKey_id() {
		return key_id;
	}

	public void setKey_id(Integer key_id) {
		this.key_id = key_id;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
    

}
