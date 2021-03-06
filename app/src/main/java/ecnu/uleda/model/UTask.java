package ecnu.uleda.model;
import com.tencent.mapsdk.raster.model.LatLng;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by Shensheng on 2017/1/15.
 * 任务类
 */

public class UTask implements Serializable{

    //Task status code.
    public static final int UNRECEIVE=0;
    public static final int WAITCHOOSE=1;
    public static final int TASK_START=2;
    public static final int SINGLE_FINISHED=3;
    public static final int TASK_FINISH=4;
    public static final int INVAILDATION=5;
    public static final int IN_DISPUTE=6;



    private String mTitle;
    private int mStatus;
    private int mAuthorID;
    private String mAuthorAvatar;
    private String mAuthorUserName;
    private int mAuthorCredit;
    private String mTag;
    private String mDescription;
    private long mPostDate;
    private long mActiveTime;
    private String mPath;
    private BigDecimal mPrice;
    private double mLat;
    private double mLng;
    private String mPostID;
    private int mTakersCount;
    private int mTaker;
    private String mAvatar;

    public int getTaker() {
        return mTaker;
    }

    public UTask setTaker(int taker) {
        this.mTaker = taker;
        return this;
    }

    public int getTakersCount() {
        return mTakersCount;
    }

    public UTask setTakersCount(int takersCount) {
        this.mTakersCount = takersCount;
        return this;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public UTask setAvatar(String avatar) {
        this.mAvatar = avatar;
        return this;
    }

    public String getPostID() {
        return mPostID;
    }

    public UTask setPostID(String postID) {
        mPostID = postID;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }
    public BigDecimal getPrice() {
        return mPrice;
    }

    public UTask setPrice(BigDecimal price) {
        mPrice = price;
        return this;
    }
    public UTask setTitle(String title) {
        mTitle = title;
        return this;
    }

    public int getStatus() {
        return mStatus;
    }

    public UTask setStatus(int status) {
        mStatus = status;
        return this;
    }

    public int getAuthorID() {
        return mAuthorID;
    }

    public UTask setAuthorID(int authorID) {
        mAuthorID = authorID;
        return this;
    }

    public String getAuthorAvatar() {
        return mAuthorAvatar;
    }

    public UTask setAuthorAvatar(String authorAvatar) {
        mAuthorAvatar = authorAvatar;
        return this;
    }

    public String getAuthorUserName() {
        return mAuthorUserName;
    }

    public UTask setAuthorUserName(String authorUserName) {
        mAuthorUserName = authorUserName;
        return this;
    }

    public int getAuthorCredit() {
        return mAuthorCredit;
    }

    public UTask setAuthorCredit(int authorCredit) {
        mAuthorCredit = authorCredit;
        return this;
    }

    public String getTag() {
        return mTag;
    }

    public UTask setTag(String tag) {
        mTag = tag;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public UTask setDescription(String description) {
        mDescription = description;
        return this;
    }

    public long getPostDate() {
        return mPostDate;
    }

    public UTask setPostDate(long postDate) {
        mPostDate = postDate;
        return this;
    }

    public long getActiveTime() {
        return mActiveTime;
    }

    public UTask setActiveTime(long activeTime) {
        mActiveTime = activeTime;
        return this;
    }

    public String getPath() {
        return mPath;
    }

    public UTask setPath(String path) {
        mPath = path;
        return this;
    }

    public LatLng getPosition() {
        return new LatLng(mLat,mLng);
    }

    public UTask setPosition(LatLng position) {
        mLat=position.getLatitude();
        mLng=position.getLongitude();
        return this;
    }




    public String getFromWhere(){
        String[] ret=mPath.split("\\|");
        if(ret.length>1) {
            return ret[0];
        }else {
            return "";
        }
    }

    public String getToWhere(){
        String[] ret=mPath.split("\\|");
        if(ret.length>1) {
            return ret[1];
        }
        else if(ret.length>0){
            return ret[0];
        }
        else{
            return "";
        }
    }
    public long getLeftTime(){
        return (mPostDate+mActiveTime-System.currentTimeMillis()/1000);
    }
    public String getStarString(){
        int c=mAuthorCredit/20;
        StringBuilder s=new StringBuilder();
        for(int i=0;i<5;i++){
            if(i<c){
                s.append("★");
            }else{
                s.append("☆");
            }
        }
        return s.toString();
    }
}
