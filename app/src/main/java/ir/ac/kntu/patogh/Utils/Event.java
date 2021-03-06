package ir.ac.kntu.patogh.Utils;

public class Event {
    private String name;
    private String desc;
    private String date;
    private String capacity;
    private String id;
    private String remainingSize;
    private String city;
    private String thumbnailId;
    private boolean isJoined;
    private boolean isPhysical;
    private String latitude;
    private String longitude;
    private boolean isFavorited;
    private String[] imagesIds;
    private String[] tags;
    private String category;

    public Event() {
    }

    public Event(String name, String desc, String date, String capacity, String id, String thumbnailId
            , boolean isJoined, boolean isFavorited, String[] imagesIds, String city, String longitude
            , String latitude, String category, String[] tags, boolean isPhysical, String remainingSize) {

        this.remainingSize = remainingSize;
        this.isPhysical = isPhysical;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.capacity = capacity;
        this.id = id;
        this.thumbnailId = thumbnailId;
        this.isFavorited = isFavorited;
        this.isJoined = isJoined;
        this.imagesIds = imagesIds;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.tags = tags;
    }

    public String getRemainingSize() {
        return remainingSize;
    }

    public void setRemainingSize(String remainingSize) {
        this.remainingSize = remainingSize;
    }

    public boolean isPhysical() {
        return isPhysical;
    }

    public void setPhysical(boolean physical) {
        isPhysical = physical;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getImagesIds() {
        return imagesIds;
    }

    public void setImagesIds(String[] imagesIds) {
        this.imagesIds = imagesIds;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
