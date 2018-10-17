package com.geostar.georobox.management.module.plugmanage.model;

import javax.persistence.*;

@Table(name = "RB_PLUG_CATEGORY")
public class PlugCategoryBean {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "CATEGORY_ID")
    private String categoryId;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "CATEGORY_DES")
    private String categoryDes;

    /**
     * @return CATEGORY_ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return CATEGORY_NAME
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return CATEGORY_DES
     */
    public String getCategoryDes() {
        return categoryDes;
    }

    /**
     * @param categoryDes
     */
    public void setCategoryDes(String categoryDes) {
        this.categoryDes = categoryDes;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PlugCategoryBean [categoryId=");
		builder.append(categoryId);
		builder.append(", categoryName=");
		builder.append(categoryName);
		builder.append(", categoryDes=");
		builder.append(categoryDes);
		builder.append("]");
		return builder.toString();
	}
    
}