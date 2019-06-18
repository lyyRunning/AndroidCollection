package com.function.luo.collection.bean;
import com.function.luo.collection.base.DbBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

/**
 * @author LYY
 */
@Entity(nameInDb ="M_PRODUCT_ALIAS")
public class SkuBean extends DbBean {
	/**
	 * 主键id
	 */
	@Id(autoincrement = false)
	@Property(nameInDb = "ID")
	private Long id;


	/**
	 * 国际码
	 */
	@Property(nameInDb = "intscode")
	@Index(unique = true)
	private String intscode;

	/**
	 * 条码
	 */
	@Property(nameInDb = "no")
	@Index(unique = true)
	private String no;

	/**
	 * 外部条码
	 */
	@Property(nameInDb = "forcode")
	@Index(unique = true)
	private String forcode;

	@Generated(hash = 1266914036)
	public SkuBean(Long id, String intscode, String no, String forcode) {
		this.id = id;
		this.intscode = intscode;
		this.no = no;
		this.forcode = forcode;
	}

	@Generated(hash = 1857614146)
	public SkuBean() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntscode() {
		return this.intscode;
	}

	public void setIntscode(String intscode) {
		this.intscode = intscode;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getForcode() {
		return this.forcode;
	}

	public void setForcode(String forcode) {
		this.forcode = forcode;
	}

	
}
