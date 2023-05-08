package cn.ac.big.bigd.webservice.model.gsa;
import java.util.Date;

public class ReleaseWeb {
	private int rel_id;
	private int  prj_id;
	private int  sample_id;
	private int  status;
	private String  webserver;
	private Date  option_date;
	private int user_id;

	public int getRel_id() {
		return rel_id;
	}

	public void setRel_id(int rel_id) {
		this.rel_id = rel_id;
	}

	public int getPrj_id() {
		return prj_id;
	}

	public void setPrj_id(int prj_id) {
		this.prj_id = prj_id;
	}

	public int getSample_id() {
		return sample_id;
	}

	public void setSample_id(int sample_id) {
		this.sample_id = sample_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getWebserver() {
		return webserver;
	}

	public void setWebserver(String webserver) {
		this.webserver = webserver;
	}

	public Date getOption_date() {
		return option_date;
	}

	public void setOption_date(Date option_date) {
		this.option_date = option_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}
