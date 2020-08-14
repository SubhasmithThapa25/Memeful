package com.subhasmith.assignment.infrrd.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageItem {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("datetime")
    @Expose
    Integer datetime;
    @SerializedName("type")
    @Expose
    String type;
    @SerializedName("animated")
    @Expose
    Boolean animated;
    @SerializedName("width")
    @Expose
    Integer width;
    @SerializedName("height")
    @Expose
    Integer height;
    @SerializedName("size")
    @Expose
    Integer size;
    @SerializedName("views")
    @Expose
    Integer views;
    @SerializedName("bandwidth")
    @Expose
    Long bandwidth;
    @SerializedName("vote")
    @Expose
    String vote;
    @SerializedName("favorite")
    @Expose
    Boolean favorite;
    @SerializedName("nsfw")
    @Expose
    Boolean nsfw;
    @SerializedName("section")
    @Expose
    String section;
    @SerializedName("account_url")
    @Expose
    String account_url;
    @SerializedName("account_id")
    @Expose
    String account_id;
    @SerializedName("is_ad")
    @Expose
    Boolean is_ad;
    @SerializedName("in_most_viral")
    @Expose
    Boolean in_most_viral;
    @SerializedName("has_sound")
    @Expose
    Boolean has_sound;
    @SerializedName("tags")
    @Expose
    List<TagItem> tags;
    @SerializedName("ad_type")
    @Expose
    Integer ad_type;
    @SerializedName("ad_url")
    @Expose
    String ad_url;
    @SerializedName("edited")
    @Expose
    String edited;
    @SerializedName("in_gallery")
    @Expose
    Boolean in_gallery;
    @SerializedName("link")
    @Expose
    String link;
    @SerializedName("comment_count")
    @Expose
    Integer comment_count;
    @SerializedName("favorite_count")
    @Expose
    Integer favorite_count;
    @SerializedName("ups")
    @Expose
    Integer ups;
    @SerializedName("downs")
    @Expose
    Integer downs;
    @SerializedName("points")
    @Expose
    Integer points;
    @SerializedName("score")
    @Expose
    Integer score;
    @SerializedName("gifv")
    @Expose
    String gifv;

    public String getGifv() {
        return gifv;
    }

    public void setGifv(String gifv) {
        this.gifv = gifv;
    }

    public List<TagItem> getTags() {
        return tags;
    }

    public void setTags(List<TagItem> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public void setAnimated(Boolean animated) {
        this.animated = animated;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Long getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Long bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAccount_url() {
        return account_url;
    }

    public void setAccount_url(String account_url) {
        this.account_url = account_url;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Boolean getIs_ad() {
        return is_ad;
    }

    public void setIs_ad(Boolean is_ad) {
        this.is_ad = is_ad;
    }

    public Boolean getIn_most_viral() {
        return in_most_viral;
    }

    public void setIn_most_viral(Boolean in_most_viral) {
        this.in_most_viral = in_most_viral;
    }

    public Boolean getHas_sound() {
        return has_sound;
    }

    public void setHas_sound(Boolean has_sound) {
        this.has_sound = has_sound;
    }


    public Integer getAd_type() {
        return ad_type;
    }

    public void setAd_type(Integer ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_url() {
        return ad_url;
    }

    public void setAd_url(String ad_url) {
        this.ad_url = ad_url;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public Boolean getIn_gallery() {
        return in_gallery;
    }

    public void setIn_gallery(Boolean in_gallery) {
        this.in_gallery = in_gallery;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(Integer favorite_count) {
        this.favorite_count = favorite_count;
    }

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Integer getDowns() {
        return downs;
    }

    public void setDowns(Integer downs) {
        this.downs = downs;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
