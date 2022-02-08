package org.microdegree.com.app.exp.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@Entity(primaryKeys = {"categoryId"})
@IgnoreExtraProperties
public  class CategoryModel {
    @NonNull
    String categoryId;
    String categoryImgUrl;
    String categoryName;
    String createdBy;
    String createdTimestamp;
    String isExperienced;
    String isPopular;
    String lastModifiedBy;
    String lastModifiedTimestamp;
    public CategoryModel() { }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }

    public void setCategoryImgUrl(String categoryImgUrl) {
        this.categoryImgUrl = categoryImgUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getIsExperienced() {
        return isExperienced;
    }

    public void setIsExperienced(String isExperienced) {
        this.isExperienced = isExperienced;
    }


    public String getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(String isPopular) {
        this.isPopular = isPopular;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedTimestamp() {
        return lastModifiedTimestamp;
    }

    public void setLastModifiedTimestamp(String lastModifiedTimestamp) {
        this.lastModifiedTimestamp = lastModifiedTimestamp;
    }
}

// public  class CategoryModel {
//    @NonNull
//    private String categoryId;
//    private String categoryName;
//    private String categoryImgUrl;
//    private String categoryFilterType;
//    private String categoryFilterTypeValue;
//    private String createdTimestamp;
//    private String lastModifiedBy;
//    private String lastModifiedTimestamp;
//     public CategoryModel() { }
//
//     public String getCategoryId() {
//         return categoryId;
//     }
//
//     public void setCategoryId(String categoryId) {
//         this.categoryId = categoryId;
//     }
//
//     public String getCategoryName() {
//         return categoryName;
//     }
//
//     public void setCategoryName(String categoryName) {
//         this.categoryName = categoryName;
//     }
//
//     public String getCategoryImgUrl() {
//         return categoryImgUrl;
//     }
//
//     public void setCategoryImgUrl(String categoryImgUrl) {
//         this.categoryImgUrl = categoryImgUrl;
//     }
//
//     public String getCategoryFilterType() {
//         return categoryFilterType;
//     }
//
//     public void setCategoryFilterType(String categoryFilterType) {
//         this.categoryFilterType = categoryFilterType;
//     }
//
//     public String getCategoryFilterTypeValue() {
//         return categoryFilterTypeValue;
//     }
//
//     public void setCategoryFilterTypeValue(String categoryFilterTypeValue) {
//         this.categoryFilterTypeValue = categoryFilterTypeValue;
//     }
//
//     public String getCreatedTimestamp() {
//         return createdTimestamp;
//     }
//
//     public void setCreatedTimestamp(String createdTimestamp) {
//         this.createdTimestamp = createdTimestamp;
//     }
//
//     public String getLastModifiedBy() {
//         return lastModifiedBy;
//     }
//
//     public void setLastModifiedBy(String lastModifiedBy) {
//         this.lastModifiedBy = lastModifiedBy;
//     }
//
//     public String getLastModifiedTimestamp() {
//         return lastModifiedTimestamp;
//     }
//
//     public void setLastModifiedTimestamp(String lastModifiedTimestamp) {
//         this.lastModifiedTimestamp = lastModifiedTimestamp;
//     }
// }
