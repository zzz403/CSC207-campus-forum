package com.imperial.academia.use_case.post;

/**
 * The PostOverviewInfo class encapsulates the data for a post overview.
 * It uses the Builder pattern to create instances.
 */
public class PostOverviewInfo {
    private int postID;
    private String postTitle;
    private String summary;
    private String userName;
    private String avatarURL;
    private int likes;
    private String postImgURL;
    private boolean isLiked;

    /**
     * Constructs a new PostOverviewInfo with the specified parameters.
     * 
     * @param postID     the ID of the post.
     * @param postTitle  the title of the post.
     * @param summary    the summary of the post.
     * @param userName   the username of the post author.
     * @param avatarURL  the avatar URL of the post author.
     * @param likes      the number of likes the post has.
     * @param postImgURL the URL of the post image.
     */
    public PostOverviewInfo(int postID, String postTitle, String summary, String userName, String avatarURL, int likes,
            String postImgURL, boolean isLiked) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.summary = summary;
        this.userName = userName;
        this.avatarURL = avatarURL;
        this.likes = likes;
        this.postImgURL = postImgURL;
        this.isLiked = isLiked;
    }

    /**
     * Default constructor for PostOverviewInfo.
     */
    public PostOverviewInfo() {
        this.avatarURL = "resources\\avatar\\default_avatar.png";
    }

    /**
     * Private constructor for PostOverviewInfo.
     * Instances should be created using the PostListInfoDataBuilder.
     * 
     * @param builder the builder instance used to create the PostOverviewInfo
     *                object.
     */
    private PostOverviewInfo(PostListInfoDataBuilder builder) {
        this.postID = builder.postID;
        this.postTitle = builder.postTitle;
        this.summary = builder.summary;
        this.userName = builder.userName;
        this.avatarURL = builder.avatarURL;
        this.postImgURL = builder.postImgURL;
        this.likes = builder.likes;
        this.isLiked = builder.isLiked;
    }

    /**
     * Returns the postTitle for the PostOverviewInfo instance.
     * 
     * @return the postTitle for the PostOverviewInfo instance.
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * Sets the postTitle for the PostOverviewInfo instance.
     * 
     * @param postTitle the postTitle to set.
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    /**
     * Returns the summary for the PostOverviewInfo instance.
     * 
     * @return the summary for the PostOverviewInfo instance.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary for the PostOverviewInfo instance.
     * 
     * @param summary the summary to set.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Returns the userName for the PostOverviewInfo instance.
     * 
     * @return the userName for the PostOverviewInfo instance.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the userName for the PostOverviewInfo instance.
     * 
     * @param userName the userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the avatarURL for the PostOverviewInfo instance.
     * 
     * @return the avatarURL for the PostOverviewInfo instance.
     */
    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /**
     * Sets the avatarURL for the PostOverviewInfo instance.
     * 
     * @param avatarURL the avatarURL to set.
     */
    public void setPostImgURL(String postImgURL) {
        this.postImgURL = postImgURL;
    }

    /**
     * Returns the postImgURL for the PostOverviewInfo instance.
     * 
     * @return the postImgURL for the PostOverviewInfo instance.
     */
    public String getAvatarURL() {
        return avatarURL;
    }

    /**
     * Sets the postImgURL for the PostOverviewInfo instance.
     * 
     * @param postImgURL the postImgURL to set.
     */
    public String getPostImgURL() {
        return postImgURL;
    }

    /**
     * Returns the postID for the PostOverviewInfo instance.
     * 
     * @return the postID for the PostOverviewInfo instance.
     */
    public static PostListInfoDataBuilder builder() {
        return new PostListInfoDataBuilder();
    }

    /**
     * The PostListInfoDataBuilder class is a builder class for the PostOverviewInfo
     * class.
     */
    public static class PostListInfoDataBuilder {
        private int postID;
        private String postTitle;
        private String summary;
        private String userName;
        private String avatarURL;
        private String postImgURL;
        private int likes;
        private boolean isLiked;

        /**
         * Constructs a new PostListInfoDataBuilder.
         */
        public PostListInfoDataBuilder() {
            this.avatarURL = "resources\\avatar\\default_avatar.png";
        }

        /**
         * Sets the postID for the PostListInfoDataBuilder instance.
         * 
         * @param postID the postID to set.
         * @return the PostListInfoDataBuilder instance.
         */
        public PostListInfoDataBuilder setPostID(int postID) {
            this.postID = postID;
            return this;
        }

        /**
         * Sets the likes for the PostListInfoDataBuilder instance.
         * 
         * @param likes the likes to set.
         * @return the PostListInfoDataBuilder instance.
         */
        public PostListInfoDataBuilder setLikes(int likes) {
            this.likes = likes;
            return this;
        }

        /**
         * Sets the postTitle for the PostListInfoDataBuilder instance.
         * 
         * @param postTitle the postTitle to set.
         * @return the PostListInfoDataBuilder instance.
         */
        public PostListInfoDataBuilder setPostTitle(String postTitle) {
            this.postTitle = postTitle;
            return this;
        }

        /**
         * Sets the summary for the PostListInfoDataBuilder instance.
         * 
         * @param summary the summary to set.
         * @return the PostListInfoDataBuilder instance.
         */
        public PostListInfoDataBuilder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        /**
         * Sets the userName for the PostListInfoDataBuilder instance.
         * 
         * @param userName the userName to set.
         * @return the PostListInfoDataBuilder instance.
         */
        public PostListInfoDataBuilder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the avatarURL for the PostListInfoDataBuilder instance.
         * 
         * @param avatarURL the avatarURL to set.
         * @return the PostListInfoDataBuilder instance.
         */
        public PostListInfoDataBuilder setAvatarURL(String aventerURL) {
            this.avatarURL = aventerURL;
            return this;
        }

        /**
         * Sets the postImgURL for the PostListInfoDataBuilder instance.
         * 
         * @param postImgURL the postImgURL to set.
         * @return the PostListInfoDataBuilder instance.
         */
        public PostListInfoDataBuilder setPostImgURL(String postImgURL) {
            this.postImgURL = postImgURL;
            return this;
        }

        /**
         * Builds a new PostOverviewInfo instance with the specified parameters.
         * 
         * @return a new PostOverviewInfo instance with the specified parameters.
         */
        public PostOverviewInfo build() {
            return new PostOverviewInfo(this);
        }

        public PostListInfoDataBuilder setIsLiked(boolean isLiked) {
            this.isLiked = isLiked;
            return this;
        }
    }

    /**
     * Returns the postID for the PostOverviewInfo instance.
     * 
     * @return the postID for the PostOverviewInfo instance.
     */
    public int getPostID() {
        return postID;
    }

    /**
     * Sets the postID for the PostOverviewInfo instance.
     * 
     * @param postID the postID to set.
     */
    public void setPostID(int postID) {
        this.postID = postID;
    }

    /**
     * Returns the likes for the PostOverviewInfo instance.
     * 
     * @return the likes for the PostOverviewInfo instance.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Sets the likes for the PostOverviewInfo instance.
     * 
     * @param likes the likes to set.
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Returns the isLiked for the PostOverviewInfo instance.
     * 
     * @return the isLiked for the PostOverviewInfo instance.
     */
    public boolean isLiked() {
        return isLiked;
    }

    /**
     * Sets the isLiked for the PostOverviewInfo instance.
     * 
     * @param isLiked the isLiked to set.
     */
    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
}
