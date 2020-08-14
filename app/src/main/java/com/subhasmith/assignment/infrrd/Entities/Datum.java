package com.subhasmith.assignment.infrrd.Entities;



import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    @Expose
    String description;

    //TODO DATE time
    @SerializedName("datetime")
    @Expose
    Integer datetime;
    @SerializedName("cover")
    @Expose
    String cover;

    @SerializedName("cover_width")
    @Expose
    Integer cover_width;
    @SerializedName("cover_height")
    @Expose
    Integer cover_height;
    @SerializedName("account_url")
    @Expose
    String account_url;
    @SerializedName("account_id")
    @Expose
    Integer account_id;

    @SerializedName("privacy")
    @Expose
    String privacy;
    @SerializedName("layout")
    @Expose
    String layout;
    @SerializedName("views")
    @Expose
    Integer views;
    @SerializedName("link")
    @Expose
    String link;

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

    @SerializedName("is_album")
    @Expose
    Boolean is_album;

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

    @SerializedName("comment_count")
    @Expose
    Integer comment_count;
    @SerializedName("favorite_count")
    @Expose
    Integer favorite_count;

    @SerializedName("topic")
    @Expose
    String topic;
    @SerializedName("topic_id")
    @Expose
    Integer topic_id;
    @SerializedName("images_count")
    @Expose
    Integer images_count;
    @SerializedName("in_gallery")
    @Expose
    Boolean in_gallery;
    @SerializedName("is_ad")
    @Expose
    Boolean is_ad;
    @SerializedName("ad_type")
    @Expose
    Integer ad_type;
    @SerializedName("ad_url")
    @Expose
    String ad_url;
    @SerializedName("in_most_viral")
    @Expose
    Boolean in_most_viral;
    @SerializedName("include_album_ads")
    @Expose
    Boolean include_album_ads;
    @SerializedName("tags")
    @Expose
    List<TagItem> tags;
    @SerializedName("images")
    @Expose
    List<ImageItem> images;

    public Datum(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public static final DiffUtil.ItemCallback<Datum> DIFF_CALL = new DiffUtil.ItemCallback<Datum>() {
        @Override
        public boolean areItemsTheSame(Datum oldItem, Datum newItem) {
            return  oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Datum oldItem, Datum newItem) {
            return  oldItem.getId() == newItem.getId();
        }
    };




    public List<TagItem> getTags() {
        return tags;
    }

    public void setTags(List<TagItem> tags) {
        this.tags = tags;
    }

    public List<ImageItem> getImages() {
        return images;
    }

    public void setImages(List<ImageItem> images) {
        this.images = images;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getCover_width() {
        return cover_width;
    }

    public void setCover_width(Integer cover_width) {
        this.cover_width = cover_width;
    }

    public Integer getCover_height() {
        return cover_height;
    }

    public void setCover_height(Integer cover_height) {
        this.cover_height = cover_height;
    }

    public String getAccount_url() {
        return account_url;
    }

    public void setAccount_url(String account_url) {
        this.account_url = account_url;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Boolean getIs_album() {
        return is_album;
    }

    public void setIs_album(Boolean is_album) {
        this.is_album = is_album;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Integer topic_id) {
        this.topic_id = topic_id;
    }

    public Integer getImages_count() {
        return images_count;
    }

    public void setImages_count(Integer images_count) {
        this.images_count = images_count;
    }

    public Boolean getIn_gallery() {
        return in_gallery;
    }

    public void setIn_gallery(Boolean in_gallery) {
        this.in_gallery = in_gallery;
    }

    public Boolean getIs_ad() {
        return is_ad;
    }

    public void setIs_ad(Boolean is_ad) {
        this.is_ad = is_ad;
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

    public Boolean getIn_most_viral() {
        return in_most_viral;
    }

    public void setIn_most_viral(Boolean in_most_viral) {
        this.in_most_viral = in_most_viral;
    }

    public Boolean getInclude_album_ads() {
        return include_album_ads;
    }

    public void setInclude_album_ads(Boolean include_album_ads) {
        this.include_album_ads = include_album_ads;
    }


}
