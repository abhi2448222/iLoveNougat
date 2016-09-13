package lenovo.example.com.ilovenougat.model;

/**
 * Created by Lenovo on 9/11/2016.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result implements Parcelable {

    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("thumbnailImageUrl")
    @Expose
    private String thumbnailImageUrl;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("originalPrice")
    @Expose
    private String originalPrice;
    @SerializedName("styleId")
    @Expose
    private String styleId;
    @SerializedName("colorId")
    @Expose
    private String colorId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("percentOff")
    @Expose
    private String percentOff;
    @SerializedName("productUrl")
    @Expose
    private String productUrl;
    @SerializedName("productName")
    @Expose
    private String productName;

    /**
     * @return The brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName The brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return The thumbnailImageUrl
     */
    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    /**
     * @param thumbnailImageUrl The thumbnailImageUrl
     */
    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    /**
     * @return The productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId The productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return The originalPrice
     */
    public String getOriginalPrice() {
        return originalPrice;
    }

    /**
     * @param originalPrice The originalPrice
     */
    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * @return The styleId
     */
    public String getStyleId() {
        return styleId;
    }

    /**
     * @param styleId The styleId
     */
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    /**
     * @return The colorId
     */
    public String getColorId() {
        return colorId;
    }

    /**
     * @param colorId The colorId
     */
    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    /**
     * @return The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return The percentOff
     */
    public String getPercentOff() {
        return percentOff;
    }

    /**
     * @param percentOff The percentOff
     */
    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    /**
     * @return The productUrl
     */
    public String getProductUrl() {
        return productUrl;
    }

    /**
     * @param productUrl The productUrl
     */
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    /**
     * @return The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName The productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }



    protected Result(Parcel in) {
        brandName = in.readString();
        thumbnailImageUrl = in.readString();
        productId = in.readString();
        originalPrice = in.readString();
        styleId = in.readString();
        colorId = in.readString();
        price = in.readString();
        percentOff = in.readString();
        productUrl = in.readString();
        productName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brandName);
        dest.writeString(thumbnailImageUrl);
        dest.writeString(productId);
        dest.writeString(originalPrice);
        dest.writeString(styleId);
        dest.writeString(colorId);
        dest.writeString(price);
        dest.writeString(percentOff);
        dest.writeString(productUrl);
        dest.writeString(productName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
